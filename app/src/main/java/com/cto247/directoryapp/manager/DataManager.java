package com.cto247.directoryapp.manager;

import com.cto247.directoryapp.models.EmployeeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class DataManager {

    private static DataManager dataManager;

    private List<EmployeeInfo> employeeInfoList;

    private DataManager() {
        employeeInfoList = new ArrayList<EmployeeInfo>();
    }

    public static DataManager getDataManager() {
        if(dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public List<EmployeeInfo> getEmployeeInfoList() {
        return employeeInfoList;
    }

    public void setEmployeeInfoList(List<EmployeeInfo> employeeInfoList) {
        this.employeeInfoList = employeeInfoList;
    }
}
