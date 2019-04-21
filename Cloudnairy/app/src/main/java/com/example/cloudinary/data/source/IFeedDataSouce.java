package com.example.cloudinary.data.source;

import com.example.cloudinary.android.mvp.main.home.model.CloudinaryJsonResponse;

import io.reactivex.Observable;
import retrofit2.http.Path;


public interface IFeedDataSouce {

    void addProduct(String imageUris, String name, int uploadType,
                    AddProductEvents callback);

    Observable<CloudinaryJsonResponse> getProducts(@Path("cloud") String cloudName, @Path("tag") String tag, @Path("version") long version);

    interface AddProductEvents {
        /**
         * Product uploaded started
         */
        void onStart();

        /**
         * Error in product upload
         */
        void onError();

        /**
         * Product upload finished
         */
        void onFinished();

        /**
         * Product upload progress (percent value)
         *
         * @param progress
         */
        void onProgress(float progress);
    }


}
