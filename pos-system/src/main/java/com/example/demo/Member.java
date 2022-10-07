package com.example.demo;

public class Member {
    private String id;
    private String password;
    private String name;
    private String position;    // staff or manager
    
    public Member(String id, String password, String name, String position) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.position = position;
    }
    public void setId(String id) { this.id = id; }
    public String getId() { return id; }
    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setPosition(String position) { this.position = position; }
    public String getPosition() { return position; }
}
