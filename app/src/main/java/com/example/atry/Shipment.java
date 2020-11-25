package com.example.atry;

class Shipment {

    private int shipmentCost;
    private int shipmentNo;
    private int shipmentQuantity;
    private int productId;
    private int supplierId;
    private String shipmentDate;

    public Shipment(int shipmentCost, int shipmentNo, int shipmentQuantity, int productId, int supplierId, String shipmentDate) {
        this.shipmentCost = shipmentCost;
        this.shipmentNo = shipmentNo;
        this.shipmentQuantity = shipmentQuantity;
        this.productId = productId;
        this.supplierId = supplierId;
        this.shipmentDate = shipmentDate;
    }

    public int getShipmentCost() {
        return shipmentCost;
    }

    public void setShipmentCost(int shipmentCost) {
        this.shipmentCost = shipmentCost;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }
}
