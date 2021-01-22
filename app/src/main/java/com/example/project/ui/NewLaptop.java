package com.example.project.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.db.DBHelper;
import com.example.project.model.Laptop;
import com.example.project.model.Phone;
import com.example.project.model.Tablet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class NewLaptop extends AppCompatActivity {

    private EditText etTitle;
    private EditText etModel;
    private EditText etStatus;
    private EditText etColor;
    private EditText etMemory;
    private EditText etPrice;
    private EditText etDescription;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_laptop);
        etTitle = findViewById(R.id.etTitle1);
        etModel = findViewById(R.id.etModel1);
        etStatus = findViewById(R.id.etStatus1);
        etColor = findViewById(R.id.etColor1);
        etMemory = findViewById(R.id.etMemory1);
        etPrice = findViewById(R.id.etPrice1);
        etDescription = findViewById(R.id.etDescription1);
        imageView = findViewById(R.id.imageView12);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.add:
                        try {
                            DBHelper dbHelper = new DBHelper(NewLaptop.this);
                            String title = etTitle.getText().toString();
                            String model = etModel.getText().toString();
                            String status = etStatus.getText().toString();
                            String color = etColor.getText().toString();
                            String memory = etMemory.getText().toString();
                            String price = etPrice.getText().toString();
                            String description = etDescription.getText().toString();
                            byte[] image = imageViewToByte(imageView);
                            SharedPreferences sharedPreferences = NewLaptop.this.getSharedPreferences("MyReferences", NewLaptop.MODE_PRIVATE);
                            int id = sharedPreferences.getInt("id",0);
                            if(title=="" || model=="" || status=="" || memory=="" || description=="" || price==""){
                                Toast.makeText(getApplicationContext(), "Thêm Laptop  không thành công vui lòng thử lại!!!", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                Laptop laptop = new Laptop(title,model,status,color,memory,description,price,image,id);
                                dbHelper.addLaptop(laptop);
                                Toast.makeText(getApplicationContext(), "Thêm Laptop thành công", Toast.LENGTH_SHORT).show();
                                NewLaptop.super.onBackPressed();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        break;
                    case R.id.back:
                        NewLaptop.super.onBackPressed();
                        break;
                }
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(NewLaptop.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 300);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 300){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,300);
            }else {

            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==300 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }
}