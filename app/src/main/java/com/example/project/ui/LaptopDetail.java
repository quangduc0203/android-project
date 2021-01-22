package com.example.project.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Account;
import com.example.project.model.Laptop;
import com.example.project.model.Phone;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LaptopDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView txtTitle;
    private TextView txtPrice;
    private TextView txtDescription;
    private TextView txtModel;
    private TextView txtColor;
    private TextView txtMemory;
    private TextView txtStatus;
    private String txtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_detail);
        imageView = findViewById(R.id.viewPhone2);
        txtTitle = findViewById(R.id.txtTilte2);
        txtPrice = findViewById(R.id.txtPrice2);

        txtDescription = findViewById(R.id.txtDescription2);
        txtModel = findViewById(R.id.txtModel2);
        txtColor = findViewById(R.id.txtColor2);
        txtMemory = findViewById(R.id.txtMemory2);
        txtStatus = findViewById(R.id.txtStatus2);

        Intent intent = getIntent();
        Laptop laptop = (Laptop) intent.getSerializableExtra("laptop");
        final Account account = (Account) intent.getSerializableExtra("acc");
        txtTitle.setText(laptop.getTitle());
        txtPrice.setText(laptop.getPrice() +" vnđ");
        txtDescription.setText(laptop.getDescription());
        txtMemory.setText(laptop.getMemory());
        txtModel.setText(laptop.getModel());
        txtStatus.setText(laptop.getStatus());
        txtColor.setText(laptop.getColor());
        byte[] image = laptop.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length);
        imageView.setImageBitmap(bitmap);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        if (ContextCompat.checkSelfPermission(LaptopDetail.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ account.getPhone()));
                            startActivity(intent);
                        }
                        else if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){

                        }
                        else {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},100);
                        }
                        break;

                    case R.id.send_message:
                        Intent i = new Intent(LaptopDetail.this, SendSMS.class);
                        i.putExtra("number", account);
                        startActivity(i);
                        break;
                }

                return true;
            }
        });
    }
}