package com.example.grocery;

public class Employee {
    public int id;
    public  String empname;
    public String empemail;
    public String emppassword;
    public String empmobile;
    public String empaddress;
    public String emppass;
    // private String quantity;



    public Employee(String empname,String empemail,String emppassword,String empmobile,String empaddress,String emppass,int id) {
        this.empname = empname;
        this.empemail = empemail;
        this.emppassword = emppassword;
        this.empmobile = empmobile;
        this.empaddress = empaddress;
        this.emppass = emppass;
        this.id = id;
        //  this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }



    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }


    public String getEmppassword() {
        return emppassword;
    }

    public void setEmppassword(String optionb) {
        this.emppassword = emppassword;
    }




    public String getEmpmobile() {
        return empmobile;
    }

    public void setEmpmobile(String empmobile) {
        this.empmobile = empmobile;
    }

    public String getEmppass() {
        return emppass;
    }

    public void setEmppass(String emppass) {
        this.emppass = emppass;
    }


    public String getEmpaddress() {
        return empaddress;
    }

    public void setEmpaddress(String empaddress) {
        this.empaddress = empaddress;
    }



}
