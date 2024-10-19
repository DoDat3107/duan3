package com.example.mvc.model;

public class Product {
    private int id;
    private String name;
    private int idCategory;
    private int quantity;
    private double price;
    private String image;
    private String nameCategory;

    public Product(int id, String name, int idCategory,String nameCategory, int quantity, double price, String image) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.nameCategory = nameCategory;
    }

    public Product(int id, String name, int idCategory, int quantity, double price, String image) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Product(String name, int idCategory, int quantity, double price, String image) {
        this.name = name;
        this.idCategory = idCategory;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
