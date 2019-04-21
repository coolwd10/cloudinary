package com.example.cloudinary.data.source.remote;

import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.cloudinary.data.source.IFeedDataSouce;

import java.util.List;
import java.util.Map;

public class CallbackAggregator implements UploadCallback {

    private IFeedDataSouce.AddProductEvents callback;
    private  String images;
    private  List<String> resultPublicIds;
    private  onResultListener resultProcessor;


    public CallbackAggregator(IFeedDataSouce.AddProductEvents addProductEvents, String imageUris,
                              onResultListener resultProcessor) {
        callback =  addProductEvents;
        this.images = images;
        this.resultProcessor = resultProcessor;
    }

    interface onResultListener {
        void onResult(List<String> publicIds);
    }

    @Override
    public void onStart(String requestId) {

    }

    @Override
    public void onProgress(String requestId, long bytes, long totalBytes) {
        callback.onProgress(bytes*100/totalBytes);
    }

    @Override
    public void onSuccess(String requestId, Map resultData) {
        callback.onFinished();
    }

    @Override
    public void onError(String requestId, ErrorInfo error) {
        callback.onError();
    }

    @Override
    public void onReschedule(String requestId, ErrorInfo error) {

    }
}
