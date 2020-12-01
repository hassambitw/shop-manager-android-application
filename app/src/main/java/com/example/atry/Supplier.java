package com.example.atry;

class Supplier {

    public String supplierName;
    public int supplierId;
    public int supplierNumber;
    public String supplierEmail;


    public Supplier(String supplierName, int supplierId, int supplierNumber, String supplierEmail) {
        this.supplierName = supplierName;
        this.supplierId = supplierId;
        this.supplierNumber = supplierNumber;
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(int supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
