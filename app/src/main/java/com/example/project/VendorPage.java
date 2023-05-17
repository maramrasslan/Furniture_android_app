package com.example.project;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class VendorPage extends AppCompatActivity {

    Bitmap bitmap;

    DB MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_page);
        ImageView imageView = findViewById(R.id.imageUpload);
        Button logout = findViewById(R.id.logout_btn);
        Button upload = findViewById(R.id.upload);
        Button addProduct = findViewById(R.id.addProduct_btn);
        EditText productName = findViewById(R.id.productName);
        EditText productPrice = findViewById(R.id.productPrice);
        EditText productAmount = findViewById(R.id.productAmount);
        CheckBox Beds = findViewById(R.id.Beds);
        CheckBox Chairs = findViewById(R.id.Chairs);
        CheckBox Couch = findViewById(R.id.Couch);
        CheckBox Wardrobe = findViewById(R.id.Wardrobe);

        MyDB = new DB(this);

        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                                imageView.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productName.getText().toString();
                Integer amount = Integer.valueOf(productAmount.getText().toString());
                Double price = Double.valueOf(productPrice.getText().toString());

                //Bitmap im1 = BitmapFactory.decodeResource(getResources(), R.drawable.ch1);

                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);
//                im1.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);

                byte[] img = byteArray.toByteArray();

                productName.setText("");
                productAmount.setText("");
                productPrice.setText("");



                boolean ins= false;
                if(Beds.isChecked()){
                    ins = MyDB.insertProducts(name,"Beds",amount,price,img);
                    Beds.setChecked(false);
                } else if (Chairs.isChecked()) {
                    ins = MyDB.insertProducts(name,"Chairs",amount,price,img);
                    Chairs.setChecked(false);
                } else if (Wardrobe.isChecked()) {
                    ins = MyDB.insertProducts(name,"Wardrobe",amount,price,img);
                    Wardrobe.setChecked(false);
                } else if (Couch.isChecked()) {
                    ins = MyDB.insertProducts(name,"Couch",amount,price,img);
                    Couch.setChecked(false);
                }
                if(ins){
                    Toast.makeText(VendorPage.this,"Product Added Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(VendorPage.this,"Error! please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}