package com.example.atry;

class Shipment {

    private int shipmentQuote;
    private int shipmentNo;
    private int supplierId;
    private String shipmentDate;
    private double totalCost;


    public Shipment(int shipmentQuote, int shipmentNo, int supplierId, String shipmentDate, double totalCost) {
        this.shipmentQuote = shipmentQuote;
        this.shipmentNo = shipmentNo;
        this.supplierId = supplierId;
        this.shipmentDate = shipmentDate;
        this.totalCost=totalCost;

    }


    public int getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(int shipmentNo) {
        this.shipmentNo = shipmentNo;
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

    public void setShipmentQuote(int shipmentQuote) {
        this.shipmentNo = shipmentNo;
    }

    public int getShipmentQuote() {
        return shipmentQuote;
    }

    public void setTotalCost(int shipmentQuote) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
