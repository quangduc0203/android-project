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
import com.example.project.model.Tablet;
import com.example.project.model.Watch;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WatchDetail extends AppCompatActivity {
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
        setContentView(R.layout.activity_watch_detail);
        imageView = findViewById(R.id.viewPhone3);
        txtTitle = findViewById(R.id.txtTilte4);
        txtPrice = findViewById(R.id.txtPrice4);
        txtDescription = findViewById(R.id.txtDescription4);
        txtModel = findViewById(R.id.txtModel4);
        txtColor = findViewById(R.id.txtColor4);
        txtMemory = findViewById(R.id.txtMemory4);
        txtStatus = findViewById(R.id.txtStatus4);

        Intent intent = getIntent();
        Watch watch = (Watch) intent.getSerializableExtra("watch");
        final Account account = (Account) intent.getSerializableExtra("acc");
        txtTitle.setText(watch.getTitle());
        txtPrice.setText(watch.getPrice() +" vnđ");
        txtDescription.setText(watch.getDescription());
        txtMemory.setText(watch.getMemory());
        txtModel.setText(watch.getModel());
        txtStatus.setText(watch.getStatus());
        txtColor.setText(watch.getColor());
        byte[] image = watch.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length);
        imageView.setImageBitmap(bitmap);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view4);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        if (ContextCompat.checkSelfPermission(WatchDetail.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
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
                        Intent i = new Intent(WatchDetail.this, SendSMS.class);
                        i.putExtra("number", account);
                        startActivity(i);
                        break;
                }

                return true;
            }
        });
    }
}