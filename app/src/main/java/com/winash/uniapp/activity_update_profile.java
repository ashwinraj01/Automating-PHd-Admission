package com.winash.uniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_update_profile extends AppCompatActivity {

    //varables
    TextView uname, uemail, uphno, upass;
    TextView fullname, email;
    Button update;

    FirebaseAuth fauth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        try {
            uname = findViewById(R.id.updatename);
            uemail = findViewById(R.id.updateemail);
            uphno = findViewById(R.id.updatephno);
            upass = findViewById(R.id.updatepass);
            update = findViewById(R.id.updatebutton);

            fullname = findViewById(R.id.full_name);

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Applicant");
            email = findViewById(R.id.useremail);
            reference.child(fauth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Applicant a=snapshot.getValue(Applicant.class);
                    email.setText(a.getEmail());
                    fullname.setText(a.getFname()+" "+a.getLname());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Applicant");

                    String fname = uname.getText().toString();
                    String email = uname.getText().toString();
                    String phone = uname.getText().toString();
                    String pass = uname.getText().toString();






                }
            });


        }
        catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}