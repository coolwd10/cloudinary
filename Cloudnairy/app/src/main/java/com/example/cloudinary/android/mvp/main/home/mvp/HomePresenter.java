package com.example.cloudinary.android.mvp.main.home.mvp;

import com.example.cloudinary.android.mvp.core.base.BasePresenter;
import com.example.cloudinary.data.source.IFeedDataSouce;
import com.example.cloudinary.domain.HomeUseCaseConttroller;
import com.example.cloudinary.domain.IView.IHomeUseCase;

import javax.inject.Inject;


public class HomePresenter extends BasePresenter {

    private IHomeUseCase mHomeViewUseCase;
    private IHomeView mIHomeView;

    @Inject
    HomePresenter(IFeedDataSouce feedDataSouce) {
        mHomeViewUseCase = new HomeUseCaseConttroller(feedDataSouce);
    }

    public void attacheScreen(IHomeView view) {
        super.attacheScreen(view);
        mIHomeView = view;
    }

    public void uploadImage(String imageUri, String fileName, int uploadType) {
        if(fileName==null || fileName.length()==0){
            mIHomeView.showError("Please enter the file name");
            return;
        }
        mHomeViewUseCase.uploadImage(imageUri , fileName, uploadType,new IFeedDataSouce.AddProductEvents() {
            @Override
            public void onStart() {

            }

            @Override
            public void onError() {
                mIHomeView.showError("Please try after some time");
            }

            @Override
            public void onFinished() {
                mIHomeView.onFinish();
            }

            @Override
            public void onProgress(float progress) {
                mIHomeView.presentProgress( progress);
            }
        });
    }

}
