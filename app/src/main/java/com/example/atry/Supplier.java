package com.example.atry;

class Supplier {

    public String supplierName;
    public int supplierId;
    public String supplierNumber;
    public String supplierEmail;


    public Supplier(String supplierName, int supplierId, String supplierNumber, String supplierEmail) {
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
