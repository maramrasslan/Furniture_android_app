package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

public class DB extends SQLiteOpenHelper {

    public static final String DBname = "MyDB.db";

    public DB(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists customers(email TEXT primary key , password TEXT)");
//        MyDB.execSQL("create Table products(name TEXT primary key ,category TEXT, amount INTEGER, price REAL)");
        MyDB.execSQL("create Table if not exists products(name TEXT primary key, category TEXT,amount INT,price DOUBLE,image BLOB)");



    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        onCreate(MyDB);
    }

    public void cartTable(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("create Table if not exists cart(name TEXT primary key, category TEXT,quantity INT,price DOUBLE,image BLOB)");
    }

    public boolean addToCart(String product,String category,Double price,byte [] image) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product);
        contentValues.put("category", category);
        contentValues.put("quantity", 1);
        contentValues.put("price", price);
        contentValues.put("image", image);

        long result = MyDB.insert("cart", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Cursor getCart(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from cart",null);
        return  cursor;
    }
    public void deleteCartTable(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("drop Table cart");
    }
    public boolean insertData (String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = MyDB.insert("customers",null,contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Cursor getImage(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select image from cart where name = ?",new String[]{name});
        return  cursor;
    }

    public void  updateProducts () {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor1 = MyDB.rawQuery("Select name from cart", null);
        while (cursor1.moveToNext()) {
            String product = String.valueOf(cursor1.getString(0));
            Cursor cursor = MyDB.rawQuery("Select amount from products where name = ?", new String[]{product});
            Integer newAmount = (Integer.valueOf(cursor.getInt(0))) ;
            newAmount = newAmount-1;
            ContentValues contentValues = new ContentValues();
            contentValues.put("amount", newAmount);

            MyDB.update("products", contentValues, "name=?", new String[]{product});


        }    ;}



    public Double getTotal(){
        Double total = 0.0;
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select price from cart",null);
        while(cursor.moveToNext()){
            total = total + (Double.valueOf(cursor.getDouble(0)));
        }
        return total;
    }

    public boolean insertProducts (String product,String category,Integer amount, Double price,byte [] image){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",product);
        contentValues.put("category",category);
        contentValues.put("amount",amount);
        contentValues.put("price",price);
        contentValues.put("image",image);

        long result = MyDB.insert("products",null,contentValues);
        if (result == -1) return false;
        else return true;
    }
//checking if the customer data already exists in our database
    public boolean checkEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from customers where email = ?",new String[]{email});
        if (cursor.getCount() > 0 )
            return true;
        else
            return false;
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from customers where email = ? and password = ?",new String[] {email,password});
        if (cursor.getCount() > 0 )
            return true;
        else
            return false;
    }



}
