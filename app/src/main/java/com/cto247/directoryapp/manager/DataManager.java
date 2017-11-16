package com.cto247.directoryapp.manager;

import com.cto247.directoryapp.models.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class DataManager {

    private static DataManager dataManager;

    private List<Employee> employeeList;

    private DataManager() {
        employeeList = new ArrayList<Employee>();
    }

    public static DataManager getDataManager() {
        if(dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;

    }
}
