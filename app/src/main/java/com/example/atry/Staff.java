package com.example.atry;

public class Staff {

    int staffID;
    String staff_fname;
    String staff_lname;
    String staff_email;
    String staff_phone;

    public Staff(int staffID, String staff_fname, String staff_lname, String staff_email, String staff_phone) {
        this.staffID = staffID;
        this.staff_fname = staff_fname;
        this.staff_lname = staff_lname;
        this.staff_email = staff_email;
        this.staff_phone = staff_phone;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStaff_fname() {
        return staff_fname;
    }

    public void setStaff_fname(String staff_fname) {
        this.staff_fname = staff_fname;
    }

    public String getStaff_lname() {
        return staff_lname;
    }

    public void setStaff_lname(String staff_lname) {
        this.staff_lname = staff_lname;
    }

    public String getStaff_email() {
        return staff_email;
    }

    public void setStaff_email(String staff_email) {
        this.staff_email = staff_email;
    }

    public String getStaff_phone() {
        return staff_phone;
    }

    public void setStaff_phone(String staff_phone) {
        this.staff_phone = staff_phone;
    }
}
