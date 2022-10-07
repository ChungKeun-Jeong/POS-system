package com.example.demo;

public class Pay {
    private String date;
    private String name;
    private String code;
    private int quantity;
    private int price;
    private int totalPrice;
    private String orderNumber;

    public Pay (String date, String name, String code, int quantity, int price, int totalPrice, String orderNumber) {
        this.date = date;
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderNumber = orderNumber;
    }

    public String getDate() { return date; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public int getQuantity() { return quantity; }
    public int getPrice() { return price; }
    public int getTotalPrice() { return totalPrice; }
    public String getOrderNumber() { return orderNumber; }
}
