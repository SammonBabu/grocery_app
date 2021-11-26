package com.example.grocery;

public class EmpAvaliability {
    public int id;

    public String empemail;

    // private String quantity;



    public EmpAvaliability(String empemail,int id) {

        this.empemail = empemail;

        this.id = id;
        //  this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }






}
