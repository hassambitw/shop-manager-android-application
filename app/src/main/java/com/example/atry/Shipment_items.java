package com.example.atry;

class Shipment_items {


    private int shipmentNo;
    private int shipmentQuantity;
    private int itemId;
    private String productName;
    private int productId;
    private double product_cost;



    public Shipment_items( int shipmentNo, int shipmentQuantity, int productId, int itemId, double product_cost, String productName) {

        this.shipmentNo = shipmentNo;
        this.shipmentQuantity = shipmentQuantity;
        this.productId = productId;
        this.itemId = itemId;
        this.product_cost=product_cost;
        this.productName=productName;

    }




    public int getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(int shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public int getShipmentQuantity() {
        return shipmentQuantity;
    }

    public void setShipmentQuantity(int shipmentQuantity) {
        this.shipmentQuantity = shipmentQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setProductCost(double product_cost){this.product_cost=product_cost;};

    public double getProduct_cost(){return product_cost;}

    public String getProduct_name(){return productName;}

}
