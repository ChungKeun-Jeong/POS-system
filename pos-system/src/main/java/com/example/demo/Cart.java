package com.example.demo;

public class Cart {
    private String code;
    private String name;
    private int price;
    private int quantity;      

    public Cart(String code, String name, int price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public void setCode(String code) { this.code = code; }
    public String getCode() { return code; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setPrice(int price) { this.price = price; }
    public int getPrice() { return price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }
}