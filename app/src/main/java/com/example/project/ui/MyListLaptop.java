package com.example.project.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapter.LaptopAdapter;
import com.example.project.adapter.PhoneAdapter;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.model.Laptop;
import com.example.project.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class MyListLaptop extends AppCompatActivity {
    private ListView listView;
    private List<Laptop> laptopList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_laptop);
        listView = findViewById(R.id.listView2);
        laptopList.clear();
        DBHelper helper = new DBHelper(this);
        SharedPreferences myPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = myPreferences.getInt("id", 0);
        laptopList = helper.listAllLaptopUser(id);
        LaptopAdapter adapter = new LaptopAdapter(laptopList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyListLaptop.this, MyLaptopDetail.class);
                DBHelper dbHelper = new DBHelper(MyListLaptop.this);
                int LaptopID = laptopList.get(position).getLaptopID();
                String model = laptopList.get(position).getModel();
                String title = laptopList.get(position).getTitle();
                String des = laptopList.get(position).getDescription();
                String status = laptopList.get(position).getStatus();
                String price = laptopList.get(position).getPrice();
                String memory = laptopList.get(position).getMemory();
                String color = laptopList.get(position).getColor();
                byte[] image = laptopList.get(position).getImage();
                int ida = laptopList.get(position).getAccountID();
                Laptop laptop = new Laptop(LaptopID, title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("laptop", laptop);
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
            laptopList.clear();
            DBHelper helper = new DBHelper(this);
            SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
            int id = sharedPreferences.getInt("id",0);
            laptopList = helper.listAllLaptopUser(id);
            LaptopAdapter adapter = new LaptopAdapter(laptopList, this);
            listView.setAdapter(adapter);
        }
    }
}