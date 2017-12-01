package com.cto247.directoryapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cto247.directoryapp.callbacks.IAPICallBack;
import com.cto247.directoryapp.network.DirectoryAPIClient;

/**
 * Created by adilber on 10/19/2017.
 */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        Context context = getApplicationContext();

        if (isInternetAvailable(context)) {
            DirectoryAPIClient.getData(callBack);
        }else{
            Toast.makeText(SplashScreen.this, "Couldn't connect to internet. Please check your connection and try again.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private IAPICallBack callBack = new IAPICallBack() {
        @Override
        public void onSuccess() {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }

        @Override
        public void onFailure(Object obj) {
            Toast.makeText(SplashScreen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    };

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
