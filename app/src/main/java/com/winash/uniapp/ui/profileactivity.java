package com.winash.uniapp.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.R;

//profile=(Button) view.findViewById(R.id.);
//        edit.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Intent intent=new Intent(getActivity(), profileactivity.class);
//        startActivity(intent);
//        }
//        }
public class profileactivity extends AppCompatActivity {

    TextView email,fname,lname;
    EditText phone,black;
    DatabaseReference ref;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity);

        fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference("Applicant");

        email=findViewById(R.id.aeEmail);
        phone=findViewById(R.id.aePhoneNum);
        fname=findViewById(R.id.aeUGC);
        black=findViewById(R.id.aeGMAPS);
        lname=findViewById(R.id.aeDP);



        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String e= (String) snapshot.child(fAuth.getUid()).child("Email").getValue();
                String p=(String) snapshot.child(fAuth.getUid()).child("phone").getValue();
                String h=(String) snapshot.child(fAuth.getUid()).child("fname").getValue();
                String a=(String) snapshot.child(fAuth.getUid()).child("black").getValue();
                String d=(String) snapshot.child(fAuth.getUid()).child("lname").getValue();

                fname.setText(h);
                lname.setText(d);
                email.setText(e);
                if(!p.equals(""))
                    phone.setText(p);
                black.setText(a);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ;


    }
}
