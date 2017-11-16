package com.cto247.directoryapp.app;

import android.app.Application;
import android.content.Context;

import com.cto247.directoryapp.manager.DataManager;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class CTOApp extends Application{
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        DataManager.getDataManager();
    }

    public static Context getAppContext(){
        return appContext;
    }
}