package com.cto247.directoryapp.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.cto247.directoryapp.app.CTOApp;

/**
 * Created by myasin on 11/10/2017.
 */
@Database(entities = {EmployeeInfo.class},version = 1)
public abstract class DirectoryDatabase extends RoomDatabase {
    public static Context context;
    private static DirectoryDatabase instance;

    public static DirectoryDatabase getInstance(){

        if (instance == null)
        {
            instance = Room.databaseBuilder(CTOApp.getAppContext(),
                    DirectoryDatabase.class, "DirectoryDatabase").fallbackToDestructiveMigration().build();
        }
        return  instance;
    }

    public abstract EmployeeInfoDAO getEmployeeInfoDAO();
}
