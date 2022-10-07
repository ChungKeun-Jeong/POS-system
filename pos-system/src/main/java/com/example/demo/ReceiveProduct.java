package com.example.demo;

public class ReceiveProduct {
    private String date;
    private String code;
    private String receiveNumber;
    private int plusQuantity;
    
    public ReceiveProduct (String date, String code, String receiveNumber, int plusQuantity) {
        this.date = date; 
        this.code = code;
        this.plusQuantity = plusQuantity;
        this.receiveNumber = receiveNumber;
    }

    public String getDate() { return date; }
    public String getCode() { return code; }
    public String getReceiveNumber() { return receiveNumber; }
    public int getPlusQuantity() { return plusQuantity; }
}
