package com.example.vignannewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView p=(TextView)findViewById(R.id.titleName);
        TextView r = (TextView)findViewById(R.id.profileName);
        TextView re1 = (TextView)findViewById(R.id.profilePassword);
        TextView re2 = (TextView)findViewById(R.id.profileEmail);
        TextView re3 = (TextView)findViewById(R.id.profileUsername);
        Intent intent = getIntent();
        String s=intent.getStringExtra("regno");
        r.setText(s);
        p.setText(s);
        String str = intent.getStringExtra("name");

        String str2 = intent.getStringExtra("password");
        String str3 = intent.getStringExtra("email");
        re2.setText(str3);
        re1.setText(str2);
        re3.setText(str);
        b1=findViewById(R.id.editButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),EditProfile.class);
                i.putExtra("regno",s);
                i.putExtra("name", str);
                i.putExtra("password", str2);
                i.putExtra("email", str3);
                startActivity(i);
            }
        });
    }
}