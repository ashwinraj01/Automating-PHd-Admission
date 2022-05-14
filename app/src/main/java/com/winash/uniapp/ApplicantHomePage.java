package com.winash.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  ApplicantHomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText name;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_home_page);
        mAuth=FirebaseAuth.getInstance();
        name=(EditText)findViewById(R.id.editTextTextApplicantName);
        ref= FirebaseDatabase.getInstance().getReference("Applicant");
        name.setText(mAuth.getUid());
    }
}