package com.yumetsuki.example.entities;

import java.io.Serializable;

public class Product implements Serializable {

    public Product(){}

    public Product(int id, String name, String category, String detail) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.detail = detail;
    }

    public Product(String name, String category, String detail) {
        this.name = name;
        this.category = category;
        this.detail = detail;
    }

    private int id;

    private String name;

    private String category;

    private String detail;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
