package com.example.atry;

class Supplier {

    public String supplierName;
    //public String supplierId;
    public String supplierNumber;
    public String supplierEmail;


    public Supplier(String supplierName, String supplierNumber, String supplierEmail) {
        this.supplierName = supplierName;
        this.supplierNumber = supplierNumber;
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
