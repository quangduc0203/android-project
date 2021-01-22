package com.example.project.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapter.PhoneAdapter;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class MyListPhone extends AppCompatActivity {
    private ListView listView;
    private List<Phone> phoneList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_phone);
        listView = findViewById(R.id.listView2);
        phoneList.clear();
        DBHelper helper = new DBHelper(this);
        SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",0);
        phoneList = helper.listAllPhoneUser(id);
        PhoneAdapter adapter = new PhoneAdapter(phoneList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyListPhone.this, MyPhoneDetail.class);
                DBHelper dbHelper = new DBHelper(MyListPhone.this);
                int phoneID = phoneList.get(position).getPhoneID();
                String model = phoneList.get(position).getModel();
                String title = phoneList.get(position).getTitle();
                String des = phoneList.get(position).getDescription();
                String status = phoneList.get(position).getStatus();
                String price = phoneList.get(position).getPrice();
                String memory = phoneList.get(position).getMemory();
                String color = phoneList.get(position).getColor();
                byte[] image = phoneList.get(position).getImage();
                int ida = phoneList.get(position).getAccountID();
                Phone phone = new Phone(phoneID, title, model, status, color, memory, des, price, image, ida);
                intent.putExtra("phone", phone);
                Account account = dbHelper.findAcc(ida);
                intent.putExtra("acc", account);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==200){
            phoneList.clear();
            DBHelper helper = new DBHelper(this);
            SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
            int id = sharedPreferences.getInt("id",0);
            phoneList = helper.listAllPhoneUser(id);
            PhoneAdapter adapter = new PhoneAdapter(phoneList, this);
            listView.setAdapter(adapter);
        }
    }
}


