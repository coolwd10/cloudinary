package com.example.cloudinary.android.mvp.main.home.mvp;

import com.example.cloudinary.android.mvp.core.base.BaseScreen;

public interface IHomeView extends BaseScreen {
    void presentProgress(float progress);
    void onFinish();
}
