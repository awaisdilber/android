package com.cto247.directoryapp.callbacks;

import com.cto247.directoryapp.models.EmployeeInfo;

import java.util.List;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public interface IAPICallBack {

    void onSuccess();
    void onFailure(Object obj);
}
