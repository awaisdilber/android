package com.cto247.directoryapp.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Comparator;

/**
 * Created by Sam on 10/7/2017.
 */
@Entity
public class Employee {
    @PrimaryKey
    private int EmployeeID;
    private String EmployeeNo;
    private String FullName;
    private String Department;
    private String Designation;
    private String Ext;
    private String ContactPhone;
    private String Mobile;
    private String PersonalEmail;
    private String OfficialEmail;

    public int getEmployeeID() {
        return EmployeeID;
    }
    public void setEmployeeID(int employeeID){ EmployeeID = employeeID; }

    public String getEmployeeNo() {
        return EmployeeNo;
    }
    public void setEmployeeNo(String employeeNo){ EmployeeNo = employeeNo; }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName){ FullName = fullName; }

    public String getMobile() {
        return Mobile;
    }
    public void setMobile(String mobile){ Mobile = mobile; }

    public String getDesignation() {
        return Designation;
    }
    public void setDesignation(String designation){ Designation = designation; }

    public String getDepartment() {
        return Department;
    }
    public void setDepartment(String department){ Department = department; }

    public String getExt() {
        return Ext;
    }
    public void setExt(String ext){ Ext = ext; }

    public String getContactPhone() {
        return ContactPhone;
    }
    public void setContactPhone(String contactPhone){ ContactPhone = contactPhone; }

    public String getPersonalEmail(){ return PersonalEmail; }
    public void setPersonalEmail(String personalEmail){ PersonalEmail = personalEmail; }

    public String getOfficialEmail(){ return OfficialEmail; }
    public void setOfficialEmail(String officialEmail){ OfficialEmail = officialEmail; }

    public static Comparator<Employee> CompareByName = new Comparator<Employee>(){
        public int compare(Employee e1, Employee e2) {
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
