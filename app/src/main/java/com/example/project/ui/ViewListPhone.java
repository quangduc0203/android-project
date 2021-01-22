package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ViewListPhone extends AppCompatActivity {
    private ListView listView;
    private List<Phone> phoneList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_phone);
        listView = findViewById(R.id.listView);
        DBHelper helper = new DBHelper(this);
        phoneList = helper.listAllPhone();
        Log.i("Test", phoneList.toString());
        PhoneAdapter adapter = new PhoneAdapter(phoneList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewListPhone.this, PhoneDetail.class);
                DBHelper dbHelper = new DBHelper(ViewListPhone.this);
                String model = phoneList.get(position).getModel();
                String title = phoneList.get(position).getTitle();
                String des = phoneList.get(position).getDescription();
                String status = phoneList.get(position).getStatus();
                String price = phoneList.get(position).getPrice();
                String memory = phoneList.get(position).getMemory();
                String color = phoneList.get(position).getColor();
                byte[] image = phoneList.get(position).getImage();
                int ida = phoneList.get(position).getAccountID();
                Phone phone = new Phone(title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("phone", phone);
                Account account = dbHelper.findAcc(ida);
                intent.putExtra("acc", account);
                startActivity(intent);
            }
        });
    }
}