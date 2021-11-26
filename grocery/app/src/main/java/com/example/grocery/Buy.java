package com.example.grocery;

import java.io.Serializable;

public class Buy implements Serializable {
    public int id;
    public String usname;
    public String usemail;
    public String usaddress;
    public String usphone;
    public String cash;
    public String cashmode;
    public String demail;
    public String status;



    public Buy(String usname,String usemail,String usaddress,String usphone,String cash,String cashmode,String demail,String status,int id) {
        this.usname = usname;
        this.usemail = usemail;
        this.usaddress = usaddress;
        this.usphone = usphone;
        this.cash = cash;
        this.cashmode = cashmode;
        this.status = status;
        this.demail = demail;
        this.id = id;
        //  this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsname() {
        return usname;
    }

    public void setUsname(String usname) {
        this.usname = usname;
    }



    public String getUsemail() {
        return usemail;
    }

    public void setUsemail(String usemail) {
        this.usemail = usemail;
    }


    public String getUsaddress() {
        return usaddress;
    }

    public void setUsaddress(String usaddress) {
        this.usaddress = usaddress;
    }




    public String getUsphone() {
        return usphone;
    }

    public void setUsphone(String usphone) {
        this.usphone = usphone;
    }




    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getCashmode() {
        return cashmode;
    }

    public void setCashmode(String cashmode) {
        this.cashmode = cashmode;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }



}
