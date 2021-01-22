package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.db.DBHelper;
import com.example.project.fragment.FragmentAccount;
import com.example.project.model.Account;

import static android.content.ContentValues.TAG;

public class ChangeInfo extends AppCompatActivity {
    private EditText name,pass,address;
    private ImageView imageView;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        name = findViewById(R.id.txtName1);
        pass = findViewById(R.id.txtPassword1);
        address = findViewById(R.id.txtAddress1);
        imageView = findViewById(R.id.imageView5);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("Account");
        name.setText(account.getName());
        pass.setText(account.getPassword());
        address.setText(account.getAddress());
        id=account.getId();
    }
    public void onSave(View view){
        DBHelper helper = new DBHelper(this);
        String name1 = name.getText().toString();
        String pass1= pass.getText().toString();
        String address1=address.getText().toString();
        if (name1!=null&&pass1!=null&&address1!=null){
        helper.updateAcc(id,name1,pass1,address1);
            Toast.makeText(getApplicationContext(), "Thay đổi thông tin thành công!!!", Toast.LENGTH_SHORT).show();
            SharedPreferences myPrefs = getApplicationContext().getSharedPreferences("MyReferences",
                    getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.clear();
            editor.commit();
            FragmentAccount.AppState.getSingleInstance().setLoggingOut(true);
            Log.d(TAG, "Now log out and start the activity login");
            Intent intent = new Intent(getApplicationContext(),
                    LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
}