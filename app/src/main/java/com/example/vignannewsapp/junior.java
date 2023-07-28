package com.example.vignannewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class junior extends AppCompatActivity {



    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://vignan-news-app-default-rtdb.firebaseio.com/");
    EditText edUsername,edPassword,edPhone;
    Button btn;
    TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior);
        edPhone=findViewById(R.id.editTextLoginUsername);
        edUsername=findViewById(R.id.editTextLoginUsername2);
        edPassword=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=edPhone.getText().toString();
                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                if(username.length()==0 || password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Juniors").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phone))
                            {
                                final String username1=snapshot.child(phone).child("Username").getValue(String.class);
                                final String password2=snapshot.child(phone).child("Password").getValue(String.class);
                                if((username1.equals(username)) && (password2.equals(password)))
                                {

                                    //String regno=snapshot.getKey()
                                    String dbusername= snapshot.child(phone).child("Username").getValue(String.class);
                                    String dbpassword = snapshot.child(phone).child("Password").getValue(String.class);
                                    String dbemail = snapshot.child(phone).child("Email").getValue(String.class);
                                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                    //intent.putExtra("regno",)
                                    intent.putExtra("regno",phone);
                                    intent.putExtra("name", dbusername);
                                    intent.putExtra("password", dbpassword);
                                    intent.putExtra("email", dbemail);
                                    startActivity(intent);
                                    finish();
                                    // Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"username or password incorrect",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Login with correct registered number",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),junior_register.class));
            }
        });
    }
}