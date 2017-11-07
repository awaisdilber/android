package com.cto247.directoryapp.app;

import android.app.Application;

import com.cto247.directoryapp.manager.DataManager;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class CTOApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        DataManager.getDataManager();
    }
}