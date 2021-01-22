package com.example.project.model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String password;
    private String address;

    public Account() {
    }

    public Account(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Account(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Account(int id, String name, String phone, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }


    public Account(String name, String phone, String password, String address) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }

    public Account(int id, String name, String phone, String password, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
