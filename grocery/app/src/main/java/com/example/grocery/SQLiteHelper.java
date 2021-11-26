package com.example.grocery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */


public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }


    public void insertcomplaint(String username, String usemail, String complaint,String complaintdate,String status) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO complainttbl VALUES (NULL, ?, ?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, username);
        statement.bindString(2, usemail);
        statement.bindString(3, complaint);
        statement.bindString(4, complaintdate);
        statement.bindString(5, status);


        statement.executeInsert();
    }

    public void updatecomplaint(String username, String usemail, String complaint,String complaintdate,String status, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE complainttbl SET username=?,usemail = ?, complaint = ?,complaintdate=?,status=? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, username);
        statement.bindString(2, usemail);
        statement.bindString(3, complaint);
        statement.bindString(4, complaintdate);
        statement.bindString(5, status);
        statement.bindDouble(6, (double) id);

        statement.execute();
        database.close();
    }


    public void deletecomplaint(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM complainttbl WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }






    public void insertuser(String user_name, String user_email, String user_password) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO registerus VALUES (NULL, ?, ?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, user_name);
        statement.bindString(2, user_email);
        statement.bindString(3, user_password);

        statement.executeInsert();
    }

    public void updateuser(String user_name, String user_email, String user_password, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE registerus SET user_name=?,user_email = ?, user_password = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, user_name);
        statement.bindString(2, user_email);
        statement.bindString(3, user_password);


        statement.bindDouble(4, (double) id);

        statement.execute();
        database.close();
    }

    public void deleteuser(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM registerus WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }

    public void insertpaycart(String usname, String usemail, String address, String phone, String cash, String cashmode,String demail,String status) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO paycarts VALUES (NULL, ?,?, ?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, usname);
        statement.bindString(2, usemail);
        statement.bindString(3, address);
        statement.bindString(4, phone);
        statement.bindString(5, cash);
        statement.bindString(6, cashmode);
        statement.bindString(7, demail);

        statement.bindString(8, status);
        statement.executeInsert();
    }
    public void updatepaycart(String usname, String usemail, String address, String phone, String cash, String cashmode,String demail,String status, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE paycarts SET usname=?,usemail = ?, address = ?,phone = ?,cash = ? ,cashmode=?,demail=?,status=?WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, usname);
        statement.bindString(2, usemail);
        statement.bindString(3, address);
        statement.bindString(4, phone);
        statement.bindString(5, cash);
        statement.bindString(6, cashmode);
        statement.bindString(7, demail);
        statement.bindString(8, status);
        statement.bindDouble(9, (double) id);
        statement.execute();
        database.close();
    }

    public  void deletepaycart(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM paycarts WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }




    public void insertdataemp(String empname, String empemail, String empdes, String empmobile, String empaddress,String emppass) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO tblempoyee VALUES (NULL, ?, ?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, empname);
        statement.bindString(2, empemail);
        statement.bindString(3, empdes);
        statement.bindString(4, empmobile);
        statement.bindString(5, empaddress);
        statement.bindString(6, emppass);
        statement.executeInsert();
    }

    public void updateem(String empname, String empemail, String empdes, String empmobile, String empaddress,String emppass,  int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE tblempoyee SET empname=?,empemail = ?, empdes = ?,empmobile = ?,empaddress = ?,emppass=? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, empname);
        statement.bindString(2, empemail);
        statement.bindString(3, empdes);
        statement.bindString(4, empmobile);
        statement.bindString(5, empaddress);
        statement.bindString(6, emppass);

        statement.bindDouble(7, (double) id);

        statement.execute();
        database.close();
    }

    public void deleteDataemp(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM tblempoyee WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }



    public void insertava(String empemail){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO tblavalbility VALUES (NULL, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, empemail);
        statement.executeInsert();
    }
    public void updateava(String empemail, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE tblavalbility SET empemail=? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, empemail);

        statement.bindDouble(2, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteava(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM tblavalbility WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }



    public void insertDatafcart(String name, int price, int quantity,String email){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO cartlst VALUES (NULL, ?, ?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);


        statement.bindDouble(2, (double)price);
        statement.bindDouble(3, (double)quantity);
        statement.bindString(4, email);


        statement.executeInsert();
    }

    public  void deleteD() {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM cartlst";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.execute();
        database.close();
    }






    public void insertDCRT(String catg,String name, int price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOODSCARTS VALUES (NULL, ?,?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, catg);
        statement.bindString(2, name);
        statement.bindDouble(3, (double)price);

        statement.bindBlob(4, image);

        statement.executeInsert();
    }

    public void updateData(String catg,String name, int price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE FOODSCARTS SET catg=?,name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, catg);
        statement.bindString(2, name);

        statement.bindDouble(3, (double)price);

        statement.bindBlob(4, image);
        statement.bindDouble(5, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOODSCARTS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
