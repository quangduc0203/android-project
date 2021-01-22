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
import com.example.project.adapter.TabletAdapter;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.model.Phone;
import com.example.project.model.Tablet;

import java.util.ArrayList;
import java.util.List;

public class MyListTablet extends AppCompatActivity {
    private ListView listView;
    private List<Tablet> tabletList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_tablet);
        listView = findViewById(R.id.listView2);
        tabletList.clear();
        DBHelper helper = new DBHelper(this);
        SharedPreferences myPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = myPreferences.getInt("id", 0);
        tabletList = helper.listAllTabletUser(id);
        TabletAdapter adapter = new TabletAdapter(tabletList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyListTablet.this, MyTabletDetail.class);
                DBHelper dbHelper = new DBHelper(MyListTablet.this);
                int TabletID = tabletList.get(position).getTabletID();
                String model = tabletList.get(position).getModel();
                String title = tabletList.get(position).getTitle();
                String des = tabletList.get(position).getDescription();
                String status = tabletList.get(position).getStatus();
                String price = tabletList.get(position).getPrice();
                String memory = tabletList.get(position).getMemory();
                String color = tabletList.get(position).getColor();
                byte[] image = tabletList.get(position).getImage();
                int ida = tabletList.get(position).getAccountID();
                Tablet tablet= new Tablet(TabletID, title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("tablet",tablet);
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
            tabletList.clear();
            DBHelper helper = new DBHelper(this);
            SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
            int id = sharedPreferences.getInt("id",0);
            tabletList = helper.listAllTabletUser(id);
            TabletAdapter adapter = new TabletAdapter(tabletList, this);
            listView.setAdapter(adapter);
        }
    }
}