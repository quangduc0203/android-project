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
import com.example.project.model.Laptop;
import com.example.project.model.Phone;

public class MyLaptopDetail extends AppCompatActivity {
    private TextView txtLaptopID;
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
        setContentView(R.layout.activity_my_laptop_detail);
        imageView = findViewById(R.id.viewLaptop2);
        etTitle = findViewById(R.id.txtTilteLaptop2);
        etPrice = findViewById(R.id.txtPriceLaptop2);
        etDescription = findViewById(R.id.txtDescriptionLaptop2);
        etModel = findViewById(R.id.txtModelLaptop2);
        etColor = findViewById(R.id.txtColorLaptop2);
        etMemory = findViewById(R.id.txtMemoryLaptop2);
        etStatus = findViewById(R.id.txtStatusLaptop2);
        txtLaptopID = findViewById(R.id.txtLaptopID);

        Intent intent = getIntent();
        Laptop laptop = (Laptop) intent.getSerializableExtra("laptop");
        txtLaptopID.setText(laptop.getLaptopID() + "");
        etTitle.setText(laptop.getTitle());
        etPrice.setText(laptop.getPrice() );
        etDescription.setText(laptop.getDescription());
        etMemory.setText(laptop.getMemory());
        etModel.setText(laptop.getModel());
        etStatus.setText(laptop.getStatus());
        etColor.setText(laptop.getColor());

        byte[] image = laptop.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
    }

    public void onDelete(View view) {
        showAlertDialog();
    }

    public void onUpdate(View view) {
        dbHelper = new DBHelper(this);
        int laptopID = Integer.parseInt(txtLaptopID.getText().toString());
        String title = etTitle.getText().toString();
        String price = etPrice.getText().toString();
        String description = etDescription.getText().toString();
        String model = etModel.getText().toString();
        String color = etColor.getText().toString();
        String memory = etMemory.getText().toString();
        String status = etStatus.getText().toString();

        SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        dbHelper.updateLaptop(laptopID, title, model, status, color, memory, description, price, id);
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
                dbHelper = new DBHelper(MyLaptopDetail.this);
                int laptopID = Integer.parseInt(txtLaptopID.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("MyReferences", MODE_PRIVATE);
                int id = sharedPreferences.getInt("id", 0);
                dbHelper.deleteLaptop(laptopID, id);
                Toast.makeText(getApplicationContext(), "Xóa bài đăng thành công!!!", Toast.LENGTH_SHORT).show();
                setResult(200);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}