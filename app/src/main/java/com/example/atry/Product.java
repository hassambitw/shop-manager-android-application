package com.example.atry;

class Product {
    private int productId;
    private String category;
    private String brandName;
    private String productName;
    private int modalYear;
    private double price;
    private int quantity;

    public Product(int productId, String category, String brandName, String productName, int modalYear, double price, int quantity) {
        this.productId = productId;
        this.category = category;
        this.brandName = brandName;
        this.productName = productName;
        this.modalYear = modalYear;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getModalYear() {
        return modalYear;
    }

    public void setModalYear(int modalYear) {
        this.modalYear = modalYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
