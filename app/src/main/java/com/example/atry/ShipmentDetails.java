package com.example.atry;

public class ShipmentDetails {

    int shipmentID;
    String shipmentDate;
    double price;

    public ShipmentDetails(int shipmentID, String shipmentDate, double price) {
        this.shipmentID = shipmentID;
        this.shipmentDate = shipmentDate;
        this.price = price;
    }

    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
