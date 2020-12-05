package com.example.atry;

class Orders {

    private int orderId;
    private int customerId;
    private String orderDate;
    private int staff_id;
    private double total_price;

    public Orders(int orderId, int customerId, String orderDate, int staff_id, double total_price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.staff_id=staff_id;
        this.total_price=total_price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staff_id;
    }

    public void setStaffId(int staff_id) {
        this.staff_id=staff_id;

    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal_price(){return total_price;}


}
