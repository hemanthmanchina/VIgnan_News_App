package com.example.vignannewsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class response extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        TextView re1 = (TextView)findViewById(R.id.editTextRegUsername);
        TextView re2 = (TextView)findViewById(R.id.editTextRegEmail);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        re1.setText("Name : " +str);
        String str2 = intent.getStringExtra("message_key5");
        re2.setText("Email : "+str2);

    }
}