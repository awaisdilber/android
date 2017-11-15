package com.cto247.directoryapp.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by myasin on 11/15/2017.
 */
@Dao
public interface EmployeeInfoDAO {

    @Query("SELECT * FROM EmployeeInfo")
    public List<EmployeeInfo> getAllEmployees();

    @Query("SELECT * FROM EmployeeInfo WHERE FullName LIKE :name")
    public List<EmployeeInfo> getEmployeesByName(String name);

    @Insert
    public List<Long> insertAll(List<EmployeeInfo> employees);

    @Insert
    public void insert(EmployeeInfo employee);

    @Delete
    public int deleteEmployees(List<EmployeeInfo> employees);

    @Delete
    public int deleteEmployee(EmployeeInfo employeeInfo);

    @Query("DELETE FROM EmployeeInfo")
    public void deleteAll();
}
