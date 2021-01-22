package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class ListProductUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_user);
    }

    public void callActivity(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnListPhone:
                intent = new Intent(this, MyListPhone.class );
                break;
            case R.id.btnListLaptop:
                intent = new Intent(this, MyListLaptop.class);
                break;
            case R.id.btnListTablet:
                intent = new Intent(this, MyListTablet.class );
                break;
            case R.id.btnListWath:
                intent = new Intent(this, MyListWatch.class);
                break;
        }
        startActivity(intent);
    }
}