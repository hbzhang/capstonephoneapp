package com.example.mortargui;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PhoneActivity extends AppCompatActivity {
   private static final int REQUEST_CALL = 1;
   private EditText mEditTextNumber;
   private EditText mEditTextNumber2;


    Button b1;
    private final static int SEND_SMS_PERMISSION_REQ=1;

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonecall);
        final String url = "https://vmihci.herokuapp.com/getimage";

        b1 = findViewById(R.id.sendButton);
        b1.setEnabled(false);

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            b1.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);

        }


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("Update", "Messages Sent");

                String s2 = "POSSIBLE THREAT AT VIRGINIA MILITARY INSTITUTE : REQUIRES IMMMEDIATE RESPONSE, " +
                        "http://144.75.191.68:5005/getimage";
                String s1 = "7706246972";
                String s3 = "3362072493";
                String myPhone = "15555215554";
                String s4 = "Messages Sent";


                if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {
                    if (checkPermission(Manifest.permission.SEND_SMS)) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(s1, null, s2, null, null);
                        smsManager.sendTextMessage(s3, null, s2, null, null);
                        smsManager.sendTextMessage(myPhone, null, s4, null, null);

                    } else {
                        Toast.makeText(PhoneActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PhoneActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }


        });

        mEditTextNumber = findViewById(R.id.edit_text_number);
        mEditTextNumber2 = findViewById(R.id.edit_text_number2);
        ImageView imageCall= findViewById(R.id.image_call);
        ImageView imageCall2 = findViewById(R.id.image_call2);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        imageCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall2();
            }
        });

    }
    private boolean checkPermission(String sendSms) {
        int check= ContextCompat.checkSelfPermission(this,sendSms);
        return check== PackageManager.PERMISSION_GRANTED;
    }



    private void makePhoneCall(){
        String number = mEditTextNumber.getText().toString();

        if (number.trim().length() >0 ) {

            if (ContextCompat.checkSelfPermission(PhoneActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PhoneActivity.this,
                        new String [] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else{
                String dial = "tel:" + number;

                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }

        } else {
            Toast.makeText(PhoneActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    private void makePhoneCall2(){

        String number2 = mEditTextNumber2.getText().toString();
        if (number2.trim().length() >0 ) {

            if (ContextCompat.checkSelfPermission(PhoneActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PhoneActivity.this,
                        new String [] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else{
                String dial = "tel:" + number2;

                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }

        } else {
            Toast.makeText(PhoneActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                makePhoneCall();
            }else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        switch(requestCode){
            case SEND_SMS_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    b1.setEnabled(true);
                }
                break;
        }

    }
}
