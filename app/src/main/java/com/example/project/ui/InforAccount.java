package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Account;

public class InforAccount extends AppCompatActivity {
    private TextView name, phone, address;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_account);
        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtPhone);
        address = findViewById(R.id.txtAddress);
        imageView = findViewById(R.id.imageView5);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("Account");
        name.setText(account.getName());
        phone.setText(account.getPhone());
        address.setText(account.getAddress());



    }
}