package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;

public class LoginActivity extends AppCompatActivity {
    private EditText etPhone;
    private EditText etPass;
    private Button btnLogin;
    private CheckBox checkBox;
    private String phone, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone = findViewById(R.id.etPhone);
        etPass = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = etPhone.getText().toString();
                pass = etPass.getText().toString();
            }
        });
    }

    public void login(View view){
        DBHelper helper = new DBHelper(this);
        String phone = etPhone.getText().toString();
        String password = etPass.getText().toString();
        if(helper.login(phone,password)==true){
            Intent intent = new Intent(this, ScreenActivity.class);
            Account account = helper.InforAccount(phone,password);
            int id = account.getId();
            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("MyReferences", LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id", id);
            editor.commit();
            startActivity(intent);
        }else {
            Toast.makeText(this,"Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
        }

    }

    public void Register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        SharedPreferences pref = getSharedPreferences("remember", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user", phone );
        editor.putString("pass", pass);
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        SharedPreferences pref = getSharedPreferences("remember", Context.MODE_PRIVATE);
        phone = pref.getString("user", phone);
        pass = pref.getString("pass", pass);
        etPhone.setText(phone);
        etPass.setText(pass);
        super.onResume();
    }
}