package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.model.Account;

public class SendSMS extends AppCompatActivity {
    private EditText txtMobile;
    private EditText txtMessage;
    private Button btnSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_s_m_s);
        txtMobile = findViewById(R.id.mblTxt);
        txtMessage = findViewById(R.id.msgTxt);
        btnSms = findViewById(R.id.btnSend);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("number");
        txtMobile.setText(account.getPhone());
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(txtMobile.getText().toString(),null,txtMessage.getText().toString(),null,null);
                    Toast.makeText(SendSMS.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(SendSMS.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}