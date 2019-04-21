package com.example.cloudinary.data.source;

import com.example.cloudinary.android.mvp.main.home.model.CloudinaryJsonResponse;
import com.example.cloudinary.data.source.remote.FeedRemoteDataSource;

import io.reactivex.Observable;


public class FeedDataSourceHelper implements IFeedDataSouce {

    FeedRemoteDataSource mFeedRemoteDataSource;

    public FeedDataSourceHelper(FeedRemoteDataSource feedRemoteDataSource) {
        mFeedRemoteDataSource = feedRemoteDataSource;
    }


    @Override
    public void addProduct(String imageUris, String name,int uploadType,
                           final IFeedDataSouce.AddProductEvents callback) {
        mFeedRemoteDataSource.addProduct(imageUris, name,uploadType, new IFeedDataSouce.AddProductEvents() {
            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onError() {
                callback.onError();
            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }

            @Override
            public void onProgress(float progress) {
                callback.onProgress(progress);
            }
        });
    }

    @Override
    public Observable<CloudinaryJsonResponse> getProducts(String cloudName, String tag, long version) {
        return mFeedRemoteDataSource.getProducts(cloudName,tag,version);
    }
}
