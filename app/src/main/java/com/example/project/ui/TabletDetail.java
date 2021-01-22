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
import com.example.project.model.Phone;
import com.example.project.model.Tablet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TabletDetail extends AppCompatActivity {
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
        setContentView(R.layout.activity_tablet_detail);
        imageView = findViewById(R.id.viewPhone4);
        txtTitle = findViewById(R.id.txtTilte3);
        txtPrice = findViewById(R.id.txtPrice3);
        txtDescription = findViewById(R.id.txtDescription3);
        txtModel = findViewById(R.id.txtModel3);
        txtColor = findViewById(R.id.txtColor3);
        txtMemory = findViewById(R.id.txtMemory3);
        txtStatus = findViewById(R.id.txtStatus3);

        Intent intent = getIntent();
        Tablet tablet = (Tablet) intent.getSerializableExtra("tablet");
        final Account account = (Account) intent.getSerializableExtra("acc");
        txtTitle.setText(tablet.getTitle());
        txtPrice.setText(tablet.getPrice() +" vnđ");
        txtDescription.setText(tablet.getDescription());
        txtMemory.setText(tablet.getMemory());
        txtModel.setText(tablet.getModel());
        txtStatus.setText(tablet.getStatus());
        txtColor.setText(tablet.getColor());
        byte[] image = tablet.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length);
        imageView.setImageBitmap(bitmap);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view3);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        if (ContextCompat.checkSelfPermission(TabletDetail.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
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
                        Intent i = new Intent(TabletDetail.this, SendSMS.class);
                        i.putExtra("number", account);
                        startActivity(i);
                        break;
                }

                return true;
            }
        });
    }
}