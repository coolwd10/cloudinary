package com.example.cloudinary.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

public class Prefs {
    private final SharedPreferences sharedPrefs;

    @Inject
    public Prefs(Context context) {
        this.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
