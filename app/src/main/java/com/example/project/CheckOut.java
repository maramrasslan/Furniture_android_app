package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Blob;
import java.util.ArrayList;

public class CheckOut extends AppCompatActivity {

    RecyclerView recyclerView;
    Button placeOrder;
    ArrayList<Bitmap> images;
    ArrayList<String> product, price, quantity;
    DB MyDB;
    cartAdapter adapter;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Button back = findViewById(R.id.back_checkout_btn);
        placeOrder = findViewById(R.id.checkout_btn);
        MyDB = new DB(this);
        images = new ArrayList<Bitmap>();
        product = new ArrayList<>();
        price = new ArrayList<>();
        quantity = new ArrayList<>();
        recyclerView = findViewById(R.id.items_view);
        adapter = new cartAdapter(this,product,quantity,price);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        total = findViewById(R.id.total);
        displayData();

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyDB.updateProducts();
                Toast.makeText(CheckOut.this,"Order Placed!",Toast.LENGTH_LONG).show();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Navigation.class);
                startActivity(intent);
            }
        });
    }
    private void displayData(){
        total.setText("Total: "+MyDB.getTotal().toString()+"$");
        Cursor cursor = MyDB.getCart();
        if(cursor.getCount()==0){
            Toast.makeText(CheckOut.this,"No items", Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
//                byte [] im = MyDB.getImage(String.valueOf(cursor.getString(0))).getBlob(0);
//                Bitmap bit = BitmapFactory.decodeByteArray(im,0,im.length);
//                images.add(bit);
                product.add(cursor.getString(0));
                quantity.add(cursor.getString(2));
                price.add(cursor.getString(3));
            }
        }
    }




}
