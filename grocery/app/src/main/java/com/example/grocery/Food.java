package com.example.grocery;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class Food {
    private int id;
    private String catg;
    private String name;
    private int price;

    // private String quantity;
    private byte[] image;


    public Food(String catg, String name, int price, byte[] image, int id) {
        this.catg = catg;
        this.name = name;
        this.price = price;
        this.image = image;

        this.id = id;
        //  this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}
