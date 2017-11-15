package com.cto247.directoryapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Comparator;

/**
 * Created by Sam on 10/7/2017.
 */
@Entity
public class EmployeeInfo {
    @PrimaryKey(autoGenerate = true)
    private Long Id;
    private String EmployeeId;
    private String EmployeeName;
    private String FullName;
    private String Department;
    private String Ext;
    private String ContactNumber;
    private String EmergencyContactNumber;
    private String Address;
    private String Mobile;

    public Long getId(){ return Id; }
    public void setId(Long value){ Id = value; }

    public String getEmployeeId() {
        return EmployeeId;
    }
    public void setEmployeeId(String employeeId){ EmployeeId = employeeId; }

    public String getEmployeeName() {
        return EmployeeName;
    }
    public void setEmployeeName(String employeeName){ EmployeeName = employeeName; }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName){ FullName = fullName; }

    public String getMobile() {
        return Mobile;
    }
    public void setMobile(String mobile){ Mobile = mobile; }

    public String getDepartment() {
        return Department;
    }
    public void setDepartment(String department){ Department = department; }

    public String getExt() {
        return Ext;
    }
    public void setExt(String ext){ Ext = ext; }

    public String getContactNumber() {
        return ContactNumber;
    }
    public void setContactNumber(String contactNumber){ ContactNumber = contactNumber; }

    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }
    public void setEmergencyContactNumber(String emergencyContactNumber){ EmergencyContactNumber = emergencyContactNumber; }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address){ Address = address; }

    public static Comparator<EmployeeInfo> CompareByName = new Comparator<EmployeeInfo>(){
        public int compare(EmployeeInfo e1, EmployeeInfo e2) {
            String EmployeeName1 = e1.getFullName().toUpperCase();
            String EmployeeName2 = e2.getFullName().toUpperCase();

            //ascending order
            return EmployeeName1.compareTo(EmployeeName2);
        }};

    /*private String dealer_id;
    private String vin;
    private String reg_no;

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }*/
}
