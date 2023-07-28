package com.example.vignannewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class comment extends AppCompatActivity {

        private EditText titleEditText, descriptionEditText;
        private Button submitButton;
        private DatabaseReference reviewsRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_comment);

            // Get reference to Firebase database
            reviewsRef = FirebaseDatabase.getInstance().getReference().child("reviews");

            // Get references to views
            titleEditText = findViewById(R.id.description_edit_text);
            descriptionEditText = findViewById(R.id.description_edit_text2);
            submitButton = findViewById(R.id.but);

            // Set click listener for submit button
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the title and description entered by user
                    String title = titleEditText.getText().toString().trim();
                    String description = descriptionEditText.getText().toString().trim();

                    // Create a new Review object to store in Firebase
                    Review review = new Review(title, description);

                    // Push the review to Firebase database
                    reviewsRef.push().setValue(review)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(comment.this, "Review submitted successfully", Toast.LENGTH_SHORT).show();
                                    // Clear the EditTexts after successful submission
                                    titleEditText.setText("");
                                    descriptionEditText.setText("");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(comment.this, "Error submitting review", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }



