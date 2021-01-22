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
import com.example.project.adapter.PhoneAdapter;
import com.example.project.adapter.TabletAdapter;
import com.example.project.adapter.WatchAdapter;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.model.Phone;
import com.example.project.model.Tablet;
import com.example.project.model.Watch;

import java.util.ArrayList;
import java.util.List;

public class MyListWatch extends AppCompatActivity {
    private ListView listView;
    private List<Watch> watchList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_watch);
        listView = findViewById(R.id.listView2);
        watchList.clear();
        DBHelper helper = new DBHelper(this);
        SharedPreferences myPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = myPreferences.getInt("id", 0);
        watchList = helper.listAllWatchUser(id);
        WatchAdapter adapter = new WatchAdapter(watchList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyListWatch.this, MyWatchDetail.class);
                DBHelper dbHelper = new DBHelper(MyListWatch.this);
                int WatchID = watchList.get(position).getWatchID();
                String model = watchList.get(position).getModel();
                String title = watchList.get(position).getTitle();
                String des = watchList.get(position).getDescription();
                String status = watchList.get(position).getStatus();
                String price = watchList.get(position).getPrice();
                String memory = watchList.get(position).getMemory();
                String color = watchList.get(position).getColor();
                byte[] image = watchList.get(position).getImage();
                int ida = watchList.get(position).getAccountID();
                Watch watch= new Watch(WatchID,title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("watch",watch);
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
            watchList.clear();
            DBHelper helper = new DBHelper(this);
            SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
            int id = sharedPreferences.getInt("id",0);
            watchList = helper.listAllWatchUser(id);
            WatchAdapter adapter = new WatchAdapter(watchList, this);
            listView.setAdapter(adapter);
        }
    }
}