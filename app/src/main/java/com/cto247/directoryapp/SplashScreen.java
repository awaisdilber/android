package com.cto247.directoryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cto247.directoryapp.callbacks.IAPICallBack;
import com.cto247.directoryapp.models.Employee;
import com.cto247.directoryapp.network.DirectoryAPIClient;

import java.util.List;

/**
 * Created by adilber on 10/19/2017.
 */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        Context context = getApplicationContext();
        //DirectoryAPIClient.getEmployeeData(context, callBack);
        DirectoryAPIClient.getData(callBack);
    }

    private IAPICallBack callBack = new IAPICallBack() {
        @Override
        public void onSuccess() {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }

        @Override
        public void onFailure(Object obj) {
            Toast.makeText(SplashScreen.this, "Failure", Toast.LENGTH_SHORT).show();
        }
    };
}
