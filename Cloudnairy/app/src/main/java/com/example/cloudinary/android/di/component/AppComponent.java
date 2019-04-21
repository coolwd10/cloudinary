package com.example.cloudinary.android.di.component;

import com.example.cloudinary.android.di.module.ApiModule;
import com.example.cloudinary.android.di.module.AppModule;
import com.example.cloudinary.android.di.module.DataModule;
import com.example.cloudinary.android.mvp.main.home.HomeActivity;
import com.example.cloudinary.android.mvp.main.home.mvp.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(HomeActivity homeActivity);
    void inject(HomePresenter homePresenter);
}
