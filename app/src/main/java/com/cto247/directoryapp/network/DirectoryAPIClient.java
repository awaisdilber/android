package com.cto247.directoryapp.network;

import android.content.Context;
import android.widget.Toast;

import com.cto247.directoryapp.callbacks.IAPICallBack;
import com.cto247.directoryapp.manager.DataManager;
import com.cto247.directoryapp.models.ContactsData;
import com.cto247.directoryapp.models.EmployeeInfo;

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
}
