package com.example.vignannewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity
{

    EditText  editEmail, editUsername, editPassword;
    Button saveButton;
    String  nameUser,emailUser, usernameUser, passwordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();
        String s=intent.getStringExtra("regno");
       if(s.startsWith("19FA0"))
       {
           reference = FirebaseDatabase.getInstance().getReference("Senior");
       }
       else if(s.startsWith("201FA0"))
       {
           reference = FirebaseDatabase.getInstance().getReference("Juniors");
       }
       else
       {
           reference = FirebaseDatabase.getInstance().getReference("Users");
       }
        //editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);
        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c=0;
                /*if (isNameChanged() && isPasswordChanged() && isEmailChanged()){
                    Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfile.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }*/
                if(isNameChanged())
                {
                    c=c+1;
                }
                if(isPasswordChanged())
                {
                    c=c+1;
                }
                if(isEmailChanged())
                {
                    c=c+1;
                }
                if(c>=1)
                {
                    Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isNameChanged() {
        if (!usernameUser.equals(editUsername.getText().toString())){
            reference.child(nameUser).child("Username").setValue(editUsername.getText().toString());
            usernameUser = editUsername.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(nameUser).child("Email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPasswordChanged() {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(nameUser).child("Password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public void showData(){
        Intent intent = getIntent();
        nameUser = intent.getStringExtra("regno");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("name");
        passwordUser = intent.getStringExtra("password");
        /*editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);*/
    }

}