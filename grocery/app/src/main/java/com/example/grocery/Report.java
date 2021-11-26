package com.example.grocery;

public class Report {
    int id;
    String username,email,complaint,compdate,status;
    //double bin;
    public Report(int id, String username, String email, String complaint, String compdate,String status ) {
        this.id = id;
        //this.bin = bin;
        this.username = username;
        this.email = email;
        this.complaint = complaint;
        this.compdate = compdate;
        this.status = status;

       // this.status = status;
    }

    public int getId() {
        return id;
    }





    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }



    public String getCompdate() {
        return compdate;
    }
    public void setCompdate(String compdate) {
        this.compdate = compdate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}






