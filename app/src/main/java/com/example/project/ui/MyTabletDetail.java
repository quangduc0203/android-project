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
import com.example.project.model.Tablet;

public class MyTabletDetail extends AppCompatActivity {private TextView txtPhoneID;
    private TextView txtTabletID;
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
        setContentView(R.layout.activity_my_tablet_detail);
        imageView = findViewById(R.id.viewTablet2);
        etTitle = findViewById(R.id.txtTilteTablet2);
        etPrice = findViewById(R.id.txtPriceTablet2);
        etDescription = findViewById(R.id.txtDescriptionTablet2);
        etModel = findViewById(R.id.txtModelTablet2);
        etColor = findViewById(R.id.txtColorTablet2);
        etMemory = findViewById(R.id.txtMemoryTablet2);
        etStatus = findViewById(R.id.txtStatusTablet2);
        txtTabletID = findViewById(R.id.txtTabletID);

        Intent intent = getIntent();
        Tablet tablet = (Tablet) intent.getSerializableExtra("tablet");
        txtTabletID.setText(tablet.getTabletID() + "");
        etTitle.setText(tablet.getTitle());
        etPrice.setText(tablet.getPrice() );
        etDescription.setText(tablet.getDescription());
        etMemory.setText(tablet.getMemory());
        etModel.setText(tablet.getModel());
        etStatus.setText(tablet.getStatus());
        etColor.setText(tablet.getColor());

        byte[] image = tablet.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
    }

    public void onDelete(View view) {
        showAlertDialog();
    }

    public void onUpdate(View view) {
        DBHelper dbHelper = new DBHelper(this);
        int tabletID = Integer.parseInt(txtTabletID.getText().toString());
        String title = etTitle.getText().toString();
        String price = etPrice.getText().toString();
        String description = etDescription.getText().toString();
        String model = etModel.getText().toString();
        String color = etColor.getText().toString();
        String memory = etMemory.getText().toString();
        String status = etStatus.getText().toString();

        SharedPreferences sharedPreferences = this.getSharedPreferences("MyReferences", this.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        dbHelper.updateTablet(tabletID, title, model, status, color, memory, description, price, id);
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
                dbHelper = new DBHelper(MyTabletDetail.this);
                int tabletID = Integer.parseInt(txtTabletID.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("MyReferences", MODE_PRIVATE);
                int id = sharedPreferences.getInt("id", 0);
                dbHelper.deleteTablet(tabletID, id);
                Toast.makeText(getApplicationContext(), "Xóa bài đăng thành công!!!", Toast.LENGTH_SHORT).show();
                setResult(200);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}