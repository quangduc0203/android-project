package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapter.TabletAdapter;
import com.example.project.adapter.WatchAdapter;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.model.Tablet;
import com.example.project.model.Watch;

import java.util.ArrayList;
import java.util.List;

public class ViewListWatch extends AppCompatActivity {
    private ListView listView;
    private List<Watch> watchList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_watch);
        listView = findViewById(R.id.listView4);
        DBHelper helper = new DBHelper(this);
        watchList = helper.listAllWatch();
        Log.i("Test", watchList.toString());
        WatchAdapter adapter = new WatchAdapter(watchList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewListWatch.this, WatchDetail.class);
                DBHelper dbHelper = new DBHelper(ViewListWatch.this);
                String model = watchList.get(position).getModel();
                String title = watchList.get(position).getTitle();
                String des = watchList.get(position).getDescription();
                String status = watchList.get(position).getStatus();
                String price = watchList.get(position).getPrice();
                String memory = watchList.get(position).getMemory();
                String color = watchList.get(position).getColor();
                byte[] image = watchList.get(position).getImage();
                int ida = watchList.get(position).getAccountID();
                Watch watch= new Watch(title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("watch",watch);
                Account account = dbHelper.findAcc(ida);
                intent.putExtra("acc", account);
                startActivity(intent);
            }
        });
    }
}