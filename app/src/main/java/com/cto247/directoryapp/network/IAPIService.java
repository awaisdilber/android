package com.cto247.directoryapp.network;

import com.cto247.directoryapp.models.ContactsData;
import com.cto247.directoryapp.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sam on 10/7/2017.
 */

public interface IAPIService {

    @GET("/api/employee")
    Call<List<Employee>> getContactInfoCall();
}
