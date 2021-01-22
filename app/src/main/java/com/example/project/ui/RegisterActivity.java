package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.validate.Validate;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPhone;
    private EditText etPass;
    private EditText etAddress;
    private Button btnHuy;
    private Button btnDangKy;
    private EditText etRePass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etPass = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        btnHuy = findViewById(R.id.btnCancel);
        btnDangKy = findViewById(R.id.btnRegister);
        etRePass=findViewById(R.id.etPasswordRe);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.super.onBackPressed();
            }
        });

    }
    public  void  Register(View view){
        DBHelper helper = new DBHelper(this);
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPass.getText().toString();
        String address = etAddress.getText().toString();
        String  rePass=etRePass.getText().toString();
        if(rePass.equalsIgnoreCase(password)){
            if(Validate.isPhone(phone)&&name!=""&&password!=""&&address!=""){
                Account account = new Account(name, phone, password, address);
                helper.addAccount(account);
                Toast.makeText(this,"Register Successfull", Toast.LENGTH_SHORT).show();
                super.onBackPressed();}
            else{
                Toast.makeText(this,"Số điện thoại sai hoặc dữ liệu thiếu!!!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Mật khẩu gõ lại không trùng!!!",Toast.LENGTH_SHORT).show();
        }


    }

}