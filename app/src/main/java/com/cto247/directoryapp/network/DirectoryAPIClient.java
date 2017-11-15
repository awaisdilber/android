package com.cto247.directoryapp.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cto247.directoryapp.app.CTOApp;
import com.cto247.directoryapp.callbacks.IAPICallBack;
import com.cto247.directoryapp.manager.DataManager;
import com.cto247.directoryapp.models.ContactsData;
import com.cto247.directoryapp.models.DirectoryDatabase;
import com.cto247.directoryapp.models.EmployeeInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sam on 10/7/2017.
 */

public class DirectoryAPIClient {

    private static String prefFileName = "com.cto247.directoryApp.settings";
    private static String syncDateFieldName = "LastUpdated";

    private static IAPIService getApiServiceClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://webapi-employeedirectory.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IAPIService.class);
    }

    public static Boolean getEmployeeData(Context context, final IAPICallBack callBack) {
        IAPIService apiServices = getApiServiceClient();
        Call<List<EmployeeInfo>> call = apiServices.getContactInfoCall();
        final Context ctx = context;
        call.enqueue(new Callback<List<EmployeeInfo>>() {
            @Override
            public void onResponse(Call<List<EmployeeInfo>> call, Response<List<EmployeeInfo>> response) {
                if(response.code() == 200 && response.body() != null) {
                    String text = response.body().toString();

                    DataManager.getDataManager().setEmployeeInfoList(response.body());
                    insertRecords(DataManager.getDataManager().getEmployeeInfoList());

                    if(callBack != null) {
                        callBack.onSuccess();
                    }
                    //Toast.makeText(ctx, text,  Toast.LENGTH_LONG).show();
                } else {
                    callBack.onFailure(response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeInfo>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });

        return false;
    }

    public static Boolean getData(IAPICallBack callBack){
        SharedPreferences preferences = CTOApp.getAppContext().getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        Long lastUpdated = preferences.getLong(syncDateFieldName, 0);

        if (lastUpdated > 0){
            Date updatedOn = new Date(lastUpdated);
            if(new Date().after(updatedOn)){
                getEmployeeData(CTOApp.getAppContext(), callBack);
            }

            return true;
        }

        getRecords(callBack);
        return  true;
    }

    public static void updateSyncDate()
    {
        SharedPreferences preferences = CTOApp.getAppContext().getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Date curDate = new Date();
        editor.putLong(syncDateFieldName, curDate.getTime());
        editor.commit();
    }

    public static void insertRecords(final List<EmployeeInfo> employees) {
        new AsyncTask<EmployeeInfo, Void, List<Long>>() {
            @Override
            protected List<Long> doInBackground(EmployeeInfo... params) {
                Integer count = params.length;
                return DirectoryDatabase.getInstance().getEmployeeInfoDAO().insertAll(employees);
            }

            @Override
            protected void onPostExecute(List<Long> longs) {
                //getRecords();
                //DataManager.getDataManager().setEmployeeInfoList(employees);
                updateSyncDate();
            }
        }.execute();
    }

    public static void getRecords(final IAPICallBack callBack)
    {
        new AsyncTask<Void, Void, List<EmployeeInfo>>(){
            @Override
            protected List<EmployeeInfo> doInBackground(Void... voids) {
                return DirectoryDatabase.getInstance().getEmployeeInfoDAO().getAllEmployees();
            }

            @Override
            protected void onPostExecute(List<EmployeeInfo> employees) {
                if (employees.size() > 0){
                    DataManager.getDataManager().setEmployeeInfoList(employees);
                    if(callBack != null) {
                        callBack.onSuccess();
                    }
                }
                else{
                    getEmployeeData(CTOApp.getAppContext(), callBack);
                }
            }
        }.execute();
    }

    public static void deleteRecords(final IAPICallBack callBack)
    {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                DirectoryDatabase.getInstance().getEmployeeInfoDAO().deleteAll();
                return  null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                getEmployeeData(CTOApp.getAppContext(), callBack);
            }
        }.execute();
    }
}
