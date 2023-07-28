package com.example.vignannewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class junior_register extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://vignan-news-app-default-rtdb.firebaseio.com/");

    EditText edUsername,edPassword,edEmail,edConfirmPassword,edPhone;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior_register);
        edUsername=findViewById(R.id.editTextRegUsername);
        edPhone=findViewById(R.id.editTextRegPassword2);
        edPassword=findViewById(R.id.editTextRegPassword);
        edEmail=findViewById(R.id.editTextRegEmail);
        edConfirmPassword=findViewById(R.id.editTextRegConfirmPassword);
        btn=findViewById(R.id.buttonReg);
        tv=findViewById(R.id.textViewexistUser);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(junior_register.this,junior.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=edUsername.getText().toString();
                final String phone=edPhone.getText().toString();
                final String password=edPassword.getText().toString();
                final String email=edEmail.getText().toString();
                final String confirm=edConfirmPassword.getText().toString();
                if(username.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(register.this,login.class));
                }
                else if(!phone.startsWith("201FA04"))
                {
                    Toast.makeText(getApplicationContext(),"Invalid Registration number(start with 201FA04)",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirm))
                {
                    Toast.makeText(getApplicationContext(),"Password and confirm does not match",Toast.LENGTH_SHORT).show();
                }
                else if(!isValid(password))
                {
                    Toast.makeText(getApplicationContext(),"Password must contain atleast 8 characters,having letter,digit and special symbol",Toast.LENGTH_SHORT).show();
                }
                else {

                    databaseReference.child("Juniors").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if email is nor registered before or not
                            if(snapshot.hasChild(phone))
                            {
                                Toast.makeText(junior_register.this,"Phone number already registered",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase realtime database
                                databaseReference.child("Juniors").child(phone).child("Username").setValue(username);
                                databaseReference.child("Juniors").child(phone).child("Password").setValue(password);
                                databaseReference.child("Juniors").child(phone).child("Email").setValue(email);
                                Toast.makeText(junior_register.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //sending data to firebase realtime database
                    // databaseReference.child("Users").child(email).child("Username").setValue(username);
                    // databaseReference.child("Users").child(email).child("Password").setValue(password);
                    // databaseReference.child("Users").child(email).child("Confirm Password").setValue(confirm);
                    // Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                }
                /*else {
                    if(password.compareTo(confirm)==0)
                    {
                        if(isValid(password))
                        {
                           // startActivity(new Intent(register.this,response.class));
                            Intent intent = new Intent(getApplicationContext(),response.class);
                            intent.putExtra("message_key", username);
                            intent.putExtra("message_key5", email);

                            startActivity(intent);

                            Toast.makeText(getApplicationContext(),"Record inserted",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Password must contain atleast 8 characters,having letter,digit and special symbol",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password and confirm does not match",Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });
    }
    
    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        }

        else {
            for(int p=0;p<passwordhere.length();p++)
            {
                if(Character.isLetter(passwordhere.charAt(p)))
                {
                    f1=1;
                }
            }
            for(int r=0;r<passwordhere.length();r++)
            {
                if(Character.isDigit(passwordhere.charAt(r)))
                {
                    f2=1;
                }
            }
            for(int s=0;s<passwordhere.length();s++)
            {
                char c=passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64)
                {
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}