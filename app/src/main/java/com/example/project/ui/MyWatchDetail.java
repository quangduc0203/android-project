package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.db.DBHelper;
import com.example.project.model.Phone;
import com.example.project.model.Watch;

public class MyWatchDetail extends AppCompatActivity {
    private TextView txtWatchID;
    private ImageView imageView;
    private EditText etTitle;
    private EditText etPrice;
    private EditText etDescription;
    private EditText etModel;
    private EditText etColor;
    private EditText etMemory;
    private EditText etStatus;
    DBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_watch_detail);
        imageView = findViewById(R.id.viewWatch2);
        etTitle = findViewById(R.id.txtTilteWatch2);
        etPrice = findViewById(R.id.txtPriceWatch2);
        etDescription = findViewById(R.id.txtDescriptionWatch2);
        etModel = findViewById(R.id.txtModelWatch2);
        etColor = findViewById(R.id.txtColorWatch2);
        etMemory = findViewById(R.id.txtMemoryWatch2);
        etStatus = findViewById(R.id.txtStatusWatch2);
        txtWatchID = findViewById(R.id.txtWatchID);

        Intent intent = getIntent();
        Watch watch = (Watch) intent.getSerializableExtra("watch");
        txtWatchID.setText(watch.getWatchID() + "");
        etTitle.setText(watch.getTitle());
        etPrice.setText(watch.getPrice() );
        etDescription.setText(watch.getDescription());
        etMemory.setText(watch.getMemory());
        etModel.setText(watch.getModel());
        etStatus.setText(watch.getStatus());
        etColor.setText(watch.getColor());

        byte[] image = watch.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
    }

    public void onDelete(View view) {
        showAlertDialog();
    }

    public void onUpdate(View view) {
        dbHelper = new DBHelper(this);
        int watchID = Integer.parseInt(txtWatchID.getText().toString());
        String title = etTitle.getText().toString();
        String price = etPrice.getText().toString();
        String description = etDescription.getText().toString();
        String model = etModel.getText().toString();
        String color = etColor.getText().toString();
        String memory = etMemory.getText().toString();
        String status = etStatus.getText().toString();

        SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        dbHelper.updateWatch(watchID, title, model, status, color, memory, description, price, id);
        Toast.makeText(getApplicationContext(), "Thay đổi thông tin thành công!!!", Toast.LENGTH_SHORT).show();
        setResult(200);
        finish();
    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa bài đăng không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper = new DBHelper(MyWatchDetail.this);
                int watchID = Integer.parseInt(txtWatchID.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("MyReferences", MODE_PRIVATE);
                int id = sharedPreferences.getInt("id", 0);
                dbHelper.deleteWatch(watchID, id);
                Toast.makeText(getApplicationContext(), "Xóa bài đăng thành công!!!", Toast.LENGTH_SHORT).show();
                setResult(200);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}