package com.example.cloudinary.android;

import android.app.Application;

import com.cloudinary.android.MediaManager;
import com.example.cloudinary.android.di.component.AppComponent;
import com.example.cloudinary.android.di.component.DaggerAppComponent;
import com.example.cloudinary.android.di.module.ApiModule;
import com.example.cloudinary.android.di.module.AppModule;
import com.example.cloudinary.android.di.module.DataModule;

import java.util.HashMap;

public class NyApp extends Application {

    private AppComponent appComponent;
    private String BASE_URL = "http://res.cloudinary.com/";//"http://api.nytimes.com/svc/mostpopular/v2/";
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().apiModule(new ApiModule(BASE_URL))
                .appModule(new AppModule(this)).dataModule(new DataModule()).build();

        HashMap config = new HashMap();
        config.put("cloud_name", "cld-demo-shared-app");
        config.put("api_key", "966113631233593");
        config.put("api_secret", "_dIcJWw6QTdQM4AxnTdYb468UHA");
        MediaManager.init(this,config);
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }

}
