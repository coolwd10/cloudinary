package com.example.cloudinary.data.source.remote;

import android.net.Uri;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.UploadRequest;
import com.cloudinary.android.policy.TimeWindow;
import com.cloudinary.android.policy.UploadPolicy;
import com.example.cloudinary.android.mvp.main.home.HomeActivity;
import com.example.cloudinary.android.mvp.main.home.model.CloudinaryJsonResponse;
import com.example.cloudinary.data.source.IFeedDataSouce;

import io.reactivex.Observable;


public class FeedRemoteDataSource implements IFeedDataSouce {

    private FeedAPIService mFeedAPIService;

    public FeedRemoteDataSource(FeedAPIService feedAPIService) {
        mFeedAPIService = feedAPIService;
    }

    @Override
    public void addProduct(String imageUris, String name,int uploadType,
                           IFeedDataSouce.AddProductEvents callback) {
        CallbackAggregator callbackAggregator = new CallbackAggregator(callback, imageUris, null);

        MediaManager mediaManager =  MediaManager.get();
        UploadRequest uploadRequest =  null;
        if(uploadType == HomeActivity.GALLERY){
            uploadRequest = mediaManager.upload(Uri.parse(imageUris));
        }else {
            uploadRequest = mediaManager.upload(imageUris);
        }
        uploadRequest
                .unsigned("sample_preset1").
                 option("upload_preset", "lr1jq0jl")
                .option("public_id", name)
                .callback(callbackAggregator)
                .constrain(TimeWindow.immediate())
                // this should be a short operation when the app is active, no need for retries
                .policy(new UploadPolicy.Builder().maxRetries(0).build())
                .dispatch();
    }

    @Override
    public Observable<CloudinaryJsonResponse> getProducts(String cloudName, String tag, long version) {
        return mFeedAPIService.getProducts(cloudName,tag,version);
    }
}
