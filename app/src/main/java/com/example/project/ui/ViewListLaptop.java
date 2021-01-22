package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ViewListLaptop extends AppCompatActivity {
    private ListView listView;
    private List<Laptop> laptopList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_laptop);
        listView = findViewById(R.id.listView2);
        DBHelper helper = new DBHelper(this);
        laptopList = helper.listAllLaptop();
        Log.i("Test", laptopList.toString());
        LaptopAdapter adapter = new LaptopAdapter(laptopList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewListLaptop.this, LaptopDetail.class);
                DBHelper dbHelper = new DBHelper(ViewListLaptop.this);
                String model = laptopList.get(position).getModel();
                String title = laptopList.get(position).getTitle();
                String des = laptopList.get(position).getDescription();
                String status = laptopList.get(position).getStatus();
                String price = laptopList.get(position).getPrice();
                String memory = laptopList.get(position).getMemory();
                String color = laptopList.get(position).getColor();
                byte[] image = laptopList.get(position).getImage();
                int ida = laptopList.get(position).getAccountID();
                Laptop laptop = new Laptop(title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("laptop", laptop);
                Account account = dbHelper.findAcc(ida);
                intent.putExtra("acc", account);
                startActivity(intent);
            }
        });
    }
}