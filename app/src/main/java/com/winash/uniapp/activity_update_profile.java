package com.winash.uniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    TextView uname, uemail, uphno, upass , um10, um12, umug, umpg;
    TextView fullname, email;
    TextView pl, pd, bl, bd;
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
//            upass = findViewById(R.id.updatepass);
            update = findViewById(R.id.updatebutton);
            um10 = findViewById(R.id.updatetenth);
            um12 = findViewById(R.id.updateinter);
            umug = findViewById(R.id.updateugmarks);
            umpg = findViewById(R.id.updatepgmarks);

            pl = findViewById(R.id.payment_label);
            pd = findViewById(R.id.payment_desc);
            bl = findViewById(R.id.booking_label);
            bd = findViewById(R.id.booking_desc);

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
                    uname.setText(a.getFname()+" "+a.getLname());
                    uemail.setText(a.getEmail());
                    uphno.setText(a.getPhone());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("ApplicantMarks");

                    String fname = uname.getText().toString();
                    String email = uemail.getText().toString();
                    String phone = uphno.getText().toString();
                    String pass = upass.getText().toString();
                    String m10 = um10.getText().toString();
                    String m12 = um12.getText().toString();
                    String mug = umug.getText().toString();
                    String mpg = umpg.getText().toString();
                    MarksApplicantUpdation obj = new MarksApplicantUpdation(m10, m12, mug, mpg);
                    Toast.makeText(view.getContext(), " Updated ", Toast.LENGTH_SHORT).show();

                    fauth=FirebaseAuth.getInstance();
                    reference.child(fauth.getCurrentUser().getUid()).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(view.getContext(), "ApplicantMarks", Toast.LENGTH_SHORT).show();


                        }
                    });






                }
            });
//            rootNode = FirebaseDatabase.getInstance();
//            rootNode.getReference("ApplicantMarks").child(fauth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    MarksApplicantUpdation a=snapshot.getValue(MarksApplicantUpdation.class);
//                    pl.setText(a.getM10());
//                    pd.setText(a.getM12());
//                    bl.setText(a.getMug());
//                    bd.setText(a.getMpg());
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });




        }
        catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}