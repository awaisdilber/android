package com.cto247.directoryapp.models;

import java.util.Comparator;

/**
 * Created by Sam on 10/7/2017.
 */

public class EmployeeInfo {

    private String EmployeeId;
    private String EmployeeName;
    private String FullName;
    private String Department;
    private String Ext;
    private String ContactNumber;
    private String EmergencyContactNumber;
    private String Address;

    public String getEmployeeId() {
        return EmployeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getFullName() {
        return FullName;
    }

    public String getDepartment() {
        return Department;
    }

    public String getExt() {
        return Ext;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getEmergencyContactNumber() {
        return EmergencyContactNumber;
    }

    public String getAddress() {
        return Address;
    }

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
