package com.example.grocery;

public class FoodCart {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int totalprice;

   // private byte[] image;


    public FoodCart(String name, int price, int quantity, int totalprice, int id) {
        this.name = name;
        this.price = price;
       // this.image = image;
        this.id = id;
         this.quantity = quantity;
        this.totalprice = totalprice;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
   // public int getTotalcart() {
   //     return totalcart;
   // }

   // public void setTotalcart(int totalcart) {
     //   this.totalprice = totalcart;
    //}

    // public void setImage(byte[] image) {
     //   this.image = image;
   // }



}
