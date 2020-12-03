package com.example.atry;

public class TransactionDetails {

    String cust_fname;
    String cust_lname;
    int order_id;
    double total_price;

    public TransactionDetails(String cust_fname, String cust_lname, int order_id, double total_price) {
        this.cust_fname = cust_fname;
        this.cust_lname = cust_lname;
        this.order_id = order_id;
        this.total_price = total_price;
    }

    public String getCust_fname() {
        return cust_fname;
    }

    public void setCust_fname(String cust_fname) {
        this.cust_fname = cust_fname;
    }

    public String getCust_lname() {
        return cust_lname;
    }

    public void setCust_lname(String cust_lname) {
        this.cust_lname = cust_lname;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
