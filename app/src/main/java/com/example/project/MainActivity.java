package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {
    EditText email, password ,signupEmail, signupPass;
    Button customerLogin , vendorLogin, signUp;


    DB MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = findViewById(R.id.login_pass);
        email = findViewById(R.id.login_email);
        customerLogin = findViewById(R.id.cust_btn);
        vendorLogin = findViewById(R.id.ven_btn);
        signupEmail = findViewById(R.id.signup_email);
        signupPass = findViewById(R.id.signup_pass);
        signUp = findViewById(R.id.signup);


        MyDB  = new DB(this);






        customerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                email.setText("");
                password.setText("");



                if(userEmail.equals("")||userPassword.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkUser = MyDB.checkEmailPassword(userEmail,userPassword);
                    if (checkUser==true){
                        Intent intent = new Intent(getApplicationContext(),Navigation.class);
                        startActivity(intent);
                        MyDB.cartTable();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Please enter valid credentials",Toast.LENGTH_SHORT).show();
                    }
                }
                

            }
        });

        vendorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                email.setText("");
                password.setText("");

                if(userEmail.equals("")||userPassword.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkUser = MyDB.checkEmailPassword(userEmail,userPassword);
                    if (checkUser==true){
                        Intent intent = new Intent(getApplicationContext(),VendorPage.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Please enter valid credentials",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            String emailP= "[a-zA-Z0-9._-]+\\.+[a-z]+";
            @Override
            public void onClick(View view) {
                String userEmail = signupEmail.getText().toString();
                String userPassword = signupPass.getText().toString();
                signupEmail.setText("");
                signupPass.setText("");
                if(userEmail.equals("")||userPassword.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkuser = MyDB.checkEmail(userEmail);
                    if (checkuser==false) {
                        if (userEmail.matches(emailP)) {
                            boolean insert = MyDB.insertData(userEmail, userPassword);
                            if (insert == true)
                                Toast.makeText(MainActivity.this, "Thanks for signing up, you can log in now.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "invalid email address.", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                        Toast.makeText(MainActivity.this,"Account already exists, try logging in.",Toast.LENGTH_SHORT).show();

                }
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bed1 = BitmapFactory.decodeResource(getResources(), R.drawable.sofabed);
                Bitmap bed2 = BitmapFactory.decodeResource(getResources(), R.drawable.woodenbed);
                Bitmap bed3 = BitmapFactory.decodeResource(getResources(), R.drawable.bunkbed);
                Bitmap bed4 = BitmapFactory.decodeResource(getResources(), R.drawable.bunnybed);
                Bitmap bed5 = BitmapFactory.decodeResource(getResources(), R.drawable.luxurybed);
                Bitmap bed6 = BitmapFactory.decodeResource(getResources(), R.drawable.babybed);

                ByteArrayOutputStream bed1b = new ByteArrayOutputStream();
                ByteArrayOutputStream bed2b = new ByteArrayOutputStream();
                ByteArrayOutputStream bed3b = new ByteArrayOutputStream();
                ByteArrayOutputStream bed4b = new ByteArrayOutputStream();
                ByteArrayOutputStream bed5b = new ByteArrayOutputStream();
                ByteArrayOutputStream bed6b = new ByteArrayOutputStream();

                bed1.compress(Bitmap.CompressFormat.JPEG, 50, bed1b);
                bed2.compress(Bitmap.CompressFormat.JPEG, 50, bed2b);
                bed3.compress(Bitmap.CompressFormat.JPEG, 50, bed3b);
                bed4.compress(Bitmap.CompressFormat.JPEG, 50, bed4b);
                bed5.compress(Bitmap.CompressFormat.JPEG, 50, bed5b);
                bed6.compress(Bitmap.CompressFormat.JPEG, 50, bed6b);

                byte[] bedim1 = bed1b.toByteArray();
                byte[] bedim2 = bed2b.toByteArray();
                byte[] bedim3 = bed3b.toByteArray();
                byte[] bedim4 = bed4b.toByteArray();
                byte[] bedim5 = bed5b.toByteArray();
                byte[] bedim6 = bed6b.toByteArray();

                MyDB.insertProducts("Sofa Bed","Beds",5,500.0,bedim1);
                MyDB.insertProducts("Wooden Bed","Beds",10,350.0,bedim2);
                MyDB.insertProducts("Bunk Bed","Beds",8,350.0,bedim3);
                MyDB.insertProducts("Bunny Bed","Beds",7,350.0,bedim4);
                MyDB.insertProducts("Luxury Bed","Beds",3,700.0,bedim5);
                MyDB.insertProducts("Kids Bed","Beds",10,1000.0,bedim6);
//----------------------------------------------------------------------------------------------------
//                Bitmap ch1 = BitmapFactory.decodeResource(getResources(), R.drawable.lazyboychair);
//                Bitmap ch2 = BitmapFactory.decodeResource(getResources(), R.drawable.diningchair);
//                Bitmap ch3 = BitmapFactory.decodeResource(getResources(), R.drawable.hangingchair);
//                Bitmap ch4 = BitmapFactory.decodeResource(getResources(), R.drawable.modernchair);
//                Bitmap ch5 = BitmapFactory.decodeResource(getResources(), R.drawable.barchair);
//                Bitmap ch6 = BitmapFactory.decodeResource(getResources(), R.drawable.officechair);
//
//                ByteArrayOutputStream ch1b = new ByteArrayOutputStream();
//                ByteArrayOutputStream ch2b = new ByteArrayOutputStream();
//                ByteArrayOutputStream ch3b = new ByteArrayOutputStream();
//                ByteArrayOutputStream ch4b = new ByteArrayOutputStream();
//                ByteArrayOutputStream ch5b = new ByteArrayOutputStream();
//                ByteArrayOutputStream ch6b = new ByteArrayOutputStream();
//
//                ch1.compress(Bitmap.CompressFormat.JPEG, 50, ch1b);
//                ch2.compress(Bitmap.CompressFormat.JPEG, 50, ch2b);
//                ch3.compress(Bitmap.CompressFormat.JPEG, 50, ch3b);
//                ch4.compress(Bitmap.CompressFormat.JPEG, 50, ch4b);
//                ch5.compress(Bitmap.CompressFormat.JPEG, 50, ch5b);
//                ch6.compress(Bitmap.CompressFormat.JPEG, 50, ch6b);
//
//                byte[] chim1 = ch1b.toByteArray();
//                byte[] chim2 = ch2b.toByteArray();
//                byte[] chim3 = ch3b.toByteArray();
//                byte[] chim4 = ch4b.toByteArray();
//                byte[] chim5 = ch5b.toByteArray();
//                byte[] chim6 = ch6b.toByteArray();
//
//                MyDB.insertProducts("Lazyboy","Chairs",5,500.0,chim1);
//                MyDB.insertProducts("Dining Chair","Chairs",10,350.0,chim2);
//                MyDB.insertProducts("Hanging Chair","Chairs",8,400.0,chim3);
//                MyDB.insertProducts("Modern Chair","Chairs",7,550.0,chim4);
//                MyDB.insertProducts("Bar Chair","Chairs",3,300.0,chim5);
//                MyDB.insertProducts("Office Chair","Chairs",10,370.0,chim6);
//-----------------------------------------------------------------------------------------------------
//                Bitmap co1 = BitmapFactory.decodeResource(getResources(), R.drawable.outdoorcouch);
//                Bitmap co2 = BitmapFactory.decodeResource(getResources(), R.drawable.lshapecouch);
////                Bitmap co3 = BitmapFactory.decodeResource(getResources(), R.drawable.ushapedsofa);
////                Bitmap co4 = BitmapFactory.decodeResource(getResources(), R.drawable.velevtsofa);
////                Bitmap co5 = BitmapFactory.decodeResource(getResources(), R.drawable.corner);
////                Bitmap co6 = BitmapFactory.decodeResource(getResources(), R.drawable.moonshapedsofa);
//
//                ByteArrayOutputStream co1b = new ByteArrayOutputStream();
//                ByteArrayOutputStream co2b = new ByteArrayOutputStream();
////                ByteArrayOutputStream co3b = new ByteArrayOutputStream();
////                ByteArrayOutputStream co4b = new ByteArrayOutputStream();
////                ByteArrayOutputStream co5b = new ByteArrayOutputStream();
////                ByteArrayOutputStream co6b = new ByteArrayOutputStream();
//
//                co1.compress(Bitmap.CompressFormat.JPEG, 50, co1b);
//                co2.compress(Bitmap.CompressFormat.JPEG, 50, co2b);
////                co3.compress(Bitmap.CompressFormat.JPEG, 50, co3b);
////                co4.compress(Bitmap.CompressFormat.JPEG, 50, co4b);
////                co5.compress(Bitmap.CompressFormat.JPEG, 50, co5b);
////                co6.compress(Bitmap.CompressFormat.JPEG, 50, co6b);
//
//                byte[] coim1 = co1b.toByteArray();
//                byte[] coim2 = co2b.toByteArray();
////                byte[] coim3 = co3b.toByteArray();
////                byte[] coim4 = co4b.toByteArray();
////                byte[] coim5 = co5b.toByteArray();
////                byte[] coim6 = co6b.toByteArray();
////
//                MyDB.insertProducts("Outdoor Couch","Couch",5,500.0,coim1);
////                MyDB.insertProducts("L-Shaped Couch","Couch",10,350.0,coim2);
////                MyDB.insertProducts("U-Shaped Couch","Couch",8,350.0,coim3);
////                MyDB.insertProducts("Leather Couch","Couch",7,350.0,coim4);
////                MyDB.insertProducts("Corner Couch","Couch",3,350.0,coim5);
////                MyDB.insertProducts("Moon-Shaped Couch","Couch",10,350.0,coim6);
//----------------------------------------------------------------------------------------------------------
                Bitmap w1 = BitmapFactory.decodeResource(getResources(), R.drawable.modernwardrobe);
                Bitmap w2 = BitmapFactory.decodeResource(getResources(), R.drawable.doublewardrobe);
//                Bitmap w3 = BitmapFactory.decodeResource(getResources(), R.drawable.glasswardrobe);
//                Bitmap w4 = BitmapFactory.decodeResource(getResources(), R.drawable.rattanwardrobe);
//                Bitmap w5 = BitmapFactory.decodeResource(getResources(), R.drawable.slidingdoorwardrobe);
//                Bitmap w6 = BitmapFactory.decodeResource(getResources(), R.drawable.openwardrobe);

                ByteArrayOutputStream w1b = new ByteArrayOutputStream();
                ByteArrayOutputStream w2b = new ByteArrayOutputStream();
//                ByteArrayOutputStream w3b = new ByteArrayOutputStream();
//                ByteArrayOutputStream w4b = new ByteArrayOutputStream();
//                ByteArrayOutputStream w5b = new ByteArrayOutputStream();
//                ByteArrayOutputStream w6b = new ByteArrayOutputStream();

                w1.compress(Bitmap.CompressFormat.JPEG, 50, w1b);
                w2.compress(Bitmap.CompressFormat.JPEG, 50, w2b);
//                w3.compress(Bitmap.CompressFormat.JPEG, 50, w3b);
//                w4.compress(Bitmap.CompressFormat.JPEG, 50, w4b);
//                w5.compress(Bitmap.CompressFormat.JPEG, 50, w5b);
//                w6.compress(Bitmap.CompressFormat.JPEG, 50, w6b);

                byte[] wim1 = w1b.toByteArray();
                byte[] wim2 = w2b.toByteArray();
//                byte[] wim3 = co3b.toByteArray();
//                byte[] wim4 = co4b.toByteArray();
//                byte[] wim5 = co5b.toByteArray();
//                byte[] wim6 = co6b.toByteArray();

                MyDB.insertProducts("Modern Wardrobe","Wardrobe",5,500.0,wim1);
                MyDB.insertProducts("Double Wardrobe","Wardrobe",10,350.0,wim2);
//                MyDB.insertProducts("Glass Wardrobe","Wardrobe",8,350.0,wim3);
//                MyDB.insertProducts("Rattan Wardrobe","Wardrobe",7,350.0,wim4);
//                MyDB.insertProducts("Sliding Wardrobe","Wardrobe",3,350.0,wim5);
//                MyDB.insertProducts("Open Wardrobe","Wardrobe",10,350.0,wim6);
//
//
//
//




 //-----------------------------------------------------------------------------------------------
                MyDB.addToCart("Sofa Bed","Beds",500.0,bedim1);
                MyDB.addToCart("Wooden Bed","Beds",350.0,bedim2);
                MyDB.addToCart("Modern Wardrobe","Wardrobe",500.0,wim1);
                MyDB.addToCart("Double Wardrobe","Wardrobe",350.0,wim2);

//                Bitmap im1 = BitmapFactory.decodeResource(getResources(), R.drawable.ch1);
//                Bitmap im2 = BitmapFactory.decodeResource(getResources(), R.drawable.ch2);
//                Bitmap im3 = BitmapFactory.decodeResource(getResources(), R.drawable.ch3);
//
//                ByteArrayOutputStream b1 = new ByteArrayOutputStream();
//                ByteArrayOutputStream b2 = new ByteArrayOutputStream();
//                ByteArrayOutputStream b3 = new ByteArrayOutputStream();
//
//
//                im1.compress(Bitmap.CompressFormat.JPEG, 50, b1);
//                im2.compress(Bitmap.CompressFormat.JPEG, 50, b2);
//                im3.compress(Bitmap.CompressFormat.JPEG, 50, b3);
//
//                byte[] img1 = b1.toByteArray();
//                byte[] img2 = b2.toByteArray();
//                byte[] img3 = b3.toByteArray();
//
//                MyDB.insertProducts("lazy boy","Chairs",10,1000.0,img1);
//                MyDB.insertProducts("Comfy chair","Chairs",10,1000.0,img2);
//                MyDB.insertProducts("Sweet bed","Beds",10,1000.0,img3);
//
//                MyDB.addToCart("lazy boy","Chairs",1000.0,img1);
//                MyDB.addToCart("Comfy chair","Chairs",1000.0,img2);
//                MyDB.addToCart("Sweet bed","Beds",1000.0,img3);
//




            }
        }).start();


    }




}

