package com.example.cloudinary.android.mvp.core.base;

import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseScreen>  {

    private WeakReference<V> viewRef;

    @UiThread
    public void attacheScreen(V view) {
        viewRef = new WeakReference<>(view);
    }

    public boolean isAttacheScreen() {
        return  viewRef!=null && viewRef.get()!=null;
    }
}
