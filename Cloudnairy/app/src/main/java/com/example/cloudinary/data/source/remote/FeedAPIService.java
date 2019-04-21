package com.example.cloudinary.data.source.remote;

import com.example.cloudinary.android.mvp.main.home.model.CloudinaryJsonResponse;

import io.reactivex.Observable;

public interface FeedAPIService {

    Observable<CloudinaryJsonResponse> getProducts(String cloudName, String tag, long version);
}
