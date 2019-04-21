package com.example.cloudinary.android.mvp.main.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cloudinary.R;
import com.example.cloudinary.android.NyApp;
import com.example.cloudinary.android.mvp.core.base.BaseActivity;
import com.example.cloudinary.android.mvp.main.home.mvp.HomePresenter;
import com.example.cloudinary.android.mvp.main.home.mvp.IHomeView;
import com.example.cloudinary.android.util.DialogManager;
import com.example.cloudinary.android.util.Permissions;
import com.example.cloudinary.android.util.PermissionsActivity;
import com.example.cloudinary.android.util.PermissionsChecker;
import com.example.cloudinary.android.util.RationaleDialogModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements IHomeView, DialogManager.NoticeDialogListener {


    private static final String[] MESSAGE_PERMISSIONS = new String[]{Permissions.CAMERA,
            Permissions.READ_EXTERNAL_STORAGE, Permissions.WRITE_EXTERNAL_STORAGE};

    @BindView(R.id.iv)
    ImageView mImageView;

    @BindView(R.id.etv_file_name)
    EditText mEtvFileName;

    @BindView(R.id.upload_progress)
    ProgressBar mUploadProgressView;

    private static final String IMAGE_DIRECTORY = "/demonuts";

    public static int GALLERY = 1, CAMERA = 2;

    private DialogManager mDialogManager;

    private PermissionsChecker mPermissionChecker;

    private int mUploadType = -1;

    private String mImagePath;
    @Inject
    HomePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
        mPresenter.attacheScreen(this);
        mDialogManager = new DialogManager(this);
        mPermissionChecker = new PermissionsChecker(this);
        ButterKnife.bind(this);
    }

    private void initDI() {
        ((NyApp) getApplicationContext()).getAppComponent().inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.upload)
    public void onUploadButtonCliced() {
        mPresenter.uploadImage(mImagePath, mEtvFileName.getText().toString(), mUploadType);
    }

    @OnClick(R.id.btn)
    public void showPictureDialog() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Select Action");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    checkPermission();
                } else if (options[item].equals("Choose from Gallery")) {
                    choosePhotoFromGallary();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    private void takePhotoFromCamera() {
        try {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mEtvFileName.setText("");
        mUploadProgressView.setProgress(0);
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
                permissionDenied();
            } else {
                permissionAccepted();
            }
        } else if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    mImageView.setImageBitmap(bitmap);
                    mImagePath = contentURI.toString();
                    mUploadType = GALLERY;
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(thumbnail);
            mImagePath = saveImage(thumbnail);
            mUploadType = CAMERA;
            Toast.makeText(HomeActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }


    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    @Override
    public void showError(String msg) {
        resetView();
        mDialogManager.showAlertDialog(HomeActivity.this, msg,
                DialogManager.DIALOGTYPE.DIALOG, 102, DialogManager.MSGTYPE.INFO, "Warning",
                getResources().getString(R.string.global_OK_label), null, null, true);
    }

    @Override
    public void onDialogPositiveClick(int dialogId) {

    }

    @Override
    public void onDialogNegativeClick(int dialogId) {

    }

    @Override
    public void onDialogNeutralClick(int dialogId) {

    }

    @Override
    public void presentProgress(float progress) {
        float progressPercentage = progress * 100;
        if (progress > 90) {
            progress = 90;
        }
        mUploadProgressView.setProgress(Math.round(progress));
    }

    @Override
    public void onFinish() {
        presentProgress(100);
        resetView();
    }

    public void resetView() {
        mEtvFileName.setText("");
        mUploadProgressView.setProgress(0);
    }

    private boolean checkPermission() {
        if (mPermissionChecker.lacksPermissions(MESSAGE_PERMISSIONS)) {
            startPermissionsActivity();
            return false;
        }
        takePhotoFromCamera();
        return true;
    }

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 321;

    private void startPermissionsActivity() {
        RationaleDialogModel permissionParams = new RationaleDialogModel();
        permissionParams.title = "Error";
        permissionParams.message = "Need These permission to read the sms and verify mobile";
        permissionParams.action = "Not Now";
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE_ASK_PERMISSIONS, permissionParams, MESSAGE_PERMISSIONS);
    }

    private void permissionDenied() {
        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
    }

    private void permissionAccepted() {
        takePhotoFromCamera();
    }


}
