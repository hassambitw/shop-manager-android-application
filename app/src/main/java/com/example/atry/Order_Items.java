package com.example.atry;

public class Order_Items {
    private int orderId;
    private int itemId;
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private double discount;

    public Order_Items(int orderId, int itemId, int productId, int quantity, double price, double discount,String productName) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.productName=productName;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        double d=(100-discount)/100;
        return d;
    }

    public String getProductName() {
        return productName;
    }

    public double viewDiscount(){
        return discount;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
