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
import com.example.project.adapter.TabletAdapter;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.model.Laptop;
import com.example.project.model.Tablet;

import java.util.ArrayList;
import java.util.List;

public class ViewListTablet extends AppCompatActivity {
    private ListView listView;
    private List<Tablet> tabletList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_tablet);
        listView = findViewById(R.id.listView3);
        DBHelper helper = new DBHelper(this);
        tabletList = helper.listAllTablet();
        Log.i("Test", tabletList.toString());
        TabletAdapter adapter = new TabletAdapter(tabletList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewListTablet.this, TabletDetail.class);
                DBHelper dbHelper = new DBHelper(ViewListTablet.this);
                String model = tabletList.get(position).getModel();
                String title = tabletList.get(position).getTitle();
                String des = tabletList.get(position).getDescription();
                String status = tabletList.get(position).getStatus();
                String price = tabletList.get(position).getPrice();
                String memory = tabletList.get(position).getMemory();
                String color = tabletList.get(position).getColor();
                byte[] image = tabletList.get(position).getImage();
                int ida = tabletList.get(position).getAccountID();
                Tablet tablet= new Tablet(title,model,status,color,memory,des,price,image,ida);
                intent.putExtra("tablet",tablet);
                Account account = dbHelper.findAcc(ida);
                intent.putExtra("acc", account);
                startActivity(intent);
            }
        });
    }
}