package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class addProductsActivity extends AppCompatActivity {

    DB MyDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDB = new DB(this);

        new Thread(new Runnable() {

            @Override
            public void run() {
                Bitmap im1 = BitmapFactory.decodeResource(getResources(), R.drawable.ch1);
                Bitmap im2 = BitmapFactory.decodeResource(getResources(), R.drawable.ch2);
                Bitmap im3 = BitmapFactory.decodeResource(getResources(), R.drawable.ch3);

                ByteArrayOutputStream b1 = new ByteArrayOutputStream();
                ByteArrayOutputStream b2 = new ByteArrayOutputStream();
                ByteArrayOutputStream b3 = new ByteArrayOutputStream();


                im1.compress(Bitmap.CompressFormat.JPEG, 0, b1);
                im2.compress(Bitmap.CompressFormat.JPEG, 0, b2);
                im3.compress(Bitmap.CompressFormat.JPEG, 0, b3);

                byte[] img1 = b1.toByteArray();
                byte[] img2 = b2.toByteArray();
                byte[] img3 = b3.toByteArray();

                MyDB.insertProducts("lazy boy","Chairs",10,1000.0,img1);
                MyDB.insertProducts("Comfy chair","Chairs",10,1000.0,img2);
                MyDB.insertProducts("Sweet bed","Beds",10,1000.0,img3);






            }
        }).start();




    }

}