package com.example.project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.model.Account;
import com.example.project.model.Laptop;
import com.example.project.model.Phone;
import com.example.project.model.Tablet;
import com.example.project.model.Watch;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String dbname = "project.db";
    private static  final  int version = 1;


    public DBHelper(Context context) {
        super(context, dbname,null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    // WORKING WITH ACCOUNT
    public void addAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", account.getName());
        values.put("phone", account.getPhone());
        values.put("password",account.getPassword());
        values.put("address", account.getAddress());

        db.insert("Account", null, values);
        db.close();
    }

    public boolean login(String phone, String pass){
        boolean status = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE phone=? and password=?",new String[]{phone,pass});
        if(cursor.moveToNext()){
            status = true;
        }
        return status;
    }

    public Account InforAccount(String number, String pass){
        Account account = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE phone=? and password=?",new String[]{number,pass});
        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("accountID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String Phone = cursor.getString(cursor.getColumnIndex("phone"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            account = new Account(id,name, Phone, address);
        }
        return account;
    }
    public Account findAcc(int id){
        Account account = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE accountID=?", new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            int id1 = cursor.getInt(cursor.getColumnIndex("accountID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String Phone = cursor.getString(cursor.getColumnIndex("phone"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            account = new Account(id1,name, Phone,password, address);
        }
        return account;
    }

    public void updateAcc(int id,String name,String password,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("password",password);
        values.put("address", address);
        db.update("Account",values,"accountID ="+id,null);
    }
    // WORKING WITH ACCOUNT

    // ===============================================================================================================
    // lIST PRODUCTS
    public List<Phone> listAllPhone(){
        List<Phone> phoneList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Phone", null);
        try {
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("phoneID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Phone phone = new Phone(id, title,model,status,color,memory,description,price,image,accountID);
                phoneList.add(phone);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return phoneList;

    }

    public List<Laptop> listAllLaptop(){
        List<Laptop> laptopList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Laptop", null);
        try {
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("laptopID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Laptop laptop= new Laptop(id,title,model,status,color,memory,description,price,image,accountID);
                laptopList.add(laptop);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return laptopList;
    }

    public List<Tablet> listAllTablet(){
        List<Tablet> tabletList= new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Tablet", null);
        try{
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("tabletID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Tablet tablet = new Tablet(id,title,model,status,color,memory,description,price,image,accountID);
                tabletList.add(tablet);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return tabletList;
    }


    public List<Watch> listAllWatch(){
        List<Watch> watchList= new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Watch", null);
        try{
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("watchID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Watch watch = new Watch(id,title,model,status,color,memory,description,price,image,accountID);
                watchList.add(watch);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return watchList;
    }

    public List<Phone> listAllPhoneUser(int uid){
        List<Phone> phoneList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account INNER JOIN Phone ON Account.accountID = Phone.accountID WHERE Account.accountID = ?", new String[]{String.valueOf(uid)});
        try {
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("phoneID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Phone phone = new Phone(id, title,model,status,color,memory,description,price,image,accountID);
                phoneList.add(phone);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return phoneList;

    }

    public List<Laptop> listAllLaptopUser(int uid){
        List<Laptop> laptopList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account INNER JOIN Laptop ON Account.accountID = Laptop.accountID WHERE Account.accountID = ?", new String[]{String.valueOf(uid)});
        try {
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("laptopID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Laptop laptop= new Laptop(id,title,model,status,color,memory,description,price,image,accountID);
                laptopList.add(laptop);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return laptopList;
    }

    public List<Tablet> listAllTabletUser(int uid){
        List<Tablet> tabletList= new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account INNER JOIN Tablet ON Account.accountID = Tablet.accountID WHERE Account.accountID = ?", new String[]{String.valueOf(uid)});
        try{
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("tabletID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Tablet tablet = new Tablet(id,title,model,status,color,memory,description,price,image,accountID);
                tabletList.add(tablet);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return tabletList;
    }

    public List<Watch> listAllWatchUser(int uid){
        List<Watch> watchList= new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account INNER JOIN Watch ON Account.accountID = Watch.accountID WHERE Account.accountID = ?", new String[]{String.valueOf(uid)});
        try{
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("watchID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String model = cursor.getString(cursor.getColumnIndex("model"));
                String status= cursor.getString(cursor.getColumnIndex("status"));
                String color =cursor.getString(cursor.getColumnIndex("color"));
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                String description =cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                int accountID = cursor.getInt(cursor.getColumnIndex("accountID"));
                Watch watch = new Watch(id,title,model,status,color,memory,description,price,image,accountID);
                watchList.add(watch);
            }
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        db.close();
        return watchList;
    }
    // lIST PRODUCTS

    // ===============================================================================================================
    // DELETE PRODUCTS
    public void deletePhone(int phoneID, int accountID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Phone", "phoneID =" +phoneID, null);
        db.close();
    }

    public void deleteTablet(int tabletID, int accountID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Tablet", "tabletID =" +tabletID, null);
        db.close();
    }
    public void deleteLaptop(int laptopID, int accountID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Laptop", "laptopID =" +laptopID, null);
        db.close();
    }
    public void deleteWatch(int watchID, int accountID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Watch", "watchID =" +watchID, null);
        db.close();
    }
    // DELETE PRODUCTS

    // ===============================================================================================================
    // UPDATE PRODUCTS
    public void updatePhone(int phoneID, String title, String model, String status, String color, String memory,
                            String description, String price,  int accountID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("model", model);
        values.put("status",status);
        values.put("color", color);
        values.put("memory", memory);
        values.put("description", description);
        values.put("price", price);
        db.update("Phone", values, "phoneID =" +phoneID, null);
        db.close();
    }

    public void updateWatch(int watchID, String title, String model, String status, String color, String memory,
                            String description, String price, int accountID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("model", model);
        values.put("status",status);
        values.put("color", color);
        values.put("memory", memory);
        values.put("description", description);
        values.put("price", price);
        db.update("Watch", values, "watchID =" +watchID, null);
        db.close();
    }
    public void updateLaptop(int laptopID, String title, String model, String status, String color, String memory,
                            String description, String price, int accountID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("model", model);
        values.put("status",status);
        values.put("color", color);
        values.put("memory", memory);
        values.put("description", description);
        values.put("price", price);
        db.update("Laptop", values, "laptopID =" +laptopID, null);
        db.close();
    }
    public void updateTablet(int tabletID, String title, String model, String status, String color, String memory,
                            String description, String price,int accountID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("model", model);
        values.put("status",status);
        values.put("color", color);
        values.put("memory", memory);
        values.put("description", description);
        values.put("price", price);
        db.update("Tablet", values, "tabletID =" +tabletID, null);
        db.close();
    }
    // UPDATE PRODUCTS
    // ===============================================================================================================
    // ADD NEW PRODUCTS
    public void addPhone(Phone phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", phone.getTitle());
        values.put("model", phone.getModel());
        values.put("status",phone.getStatus());
        values.put("color", phone.getColor());
        values.put("memory", phone.getMemory());
        values.put("description", phone.getDescription());
        values.put("price", phone.getPrice());
        values.put("image", phone.getImage());
        values.put("accountID", phone.getAccountID());

        db.insert("Phone",null, values);
        db.close();

    }

    public void addLaptop(Laptop laptop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",laptop.getTitle());
        values.put("model",laptop.getModel());
        values.put("status",laptop.getStatus());
        values.put("color",laptop.getColor());
        values.put("memory",laptop.getMemory());
        values.put("description",laptop.getDescription());
        values.put("price",laptop.getPrice());
        values.put("image",laptop.getImage());
        values.put("accountID",laptop.getAccountID());

        db.insert("Laptop",null,values);
        db.close();
    }

    public void addWatch(Watch watch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",watch.getTitle());
        values.put("model",watch.getModel());
        values.put("status",watch.getStatus());
        values.put("color",watch.getColor());
        values.put("memory",watch.getMemory());
        values.put("description",watch.getDescription());
        values.put("price",watch.getPrice());
        values.put("image",watch.getImage());
        values.put("accountID",watch.getAccountID());

        db.insert("Watch",null,values);
        db.close();
    }

    public void addTable(Tablet tablet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",tablet.getTitle());
        values.put("model",tablet.getModel());
        values.put("status",tablet.getStatus());
        values.put("color",tablet.getColor());
        values.put("memory",tablet.getMemory());
        values.put("description",tablet.getDescription());
        values.put("price",tablet.getPrice());
        values.put("image",tablet.getImage());
        values.put("accountID",tablet.getAccountID());

        db.insert("Tablet",null,values);
        db.close();
    }
    // ADD NEW PRODUCTS

}
