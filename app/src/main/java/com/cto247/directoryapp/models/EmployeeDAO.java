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
public interface EmployeeDAO {

    @Query("SELECT * FROM Employee")
    public List<Employee> getAllEmployees();

    @Query("SELECT * FROM Employee WHERE FullName LIKE :name")
    public List<Employee> getEmployeesByName(String name);

    @Insert
    public List<Long> insertAll(List<Employee> employees);

    @Insert
    public void insert(Employee employee);

    @Delete
    public int deleteEmployees(List<Employee> employees);

    @Delete
    public int deleteEmployee(Employee employee);

    @Query("DELETE FROM Employee")
    public void deleteAll();
}
