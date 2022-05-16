package com.winash.uniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminApplicantEdit extends AppCompatActivity {
    public FirebaseAuth fauth;
    public DatabaseReference ref;
    public TextView name,id,email,phone;
    public RecyclerView rec;
    public boolean flag=false;
    public ArrayList<ComponentsClass> clss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String appid=(String) getIntent().getSerializableExtra("applicantId");
        setContentView(R.layout.activity_admin_applicant_edit);
        name=findViewById(R.id.Applicant_Name);
        email=findViewById(R.id.Applicant_Email);
        id =findViewById(R.id.Applicant_Id);
        phone=findViewById(R.id.Applicant_Number);
        ref= FirebaseDatabase.getInstance().getReference("Applicant").child(appid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Applicant app=snapshot.getValue(Applicant.class);
                name.setText(app.getFname()+" "+app.getLname());
                id.setText(app.getApplicantid());
                phone.setText(app.getPhone());
                email.setText(app.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        clss=new ArrayList<>();
        rec=findViewById(R.id.AdminEditApplicant);
        AdminApplicantEditAdapter adapter = new AdminApplicantEditAdapter(getApplicationContext(), clss);
        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rec.setAdapter(adapter);
        rec.setHasFixedSize(true);
        try {
            DatabaseReference dbb=FirebaseDatabase.getInstance().getReference("Registercourse");
            dbb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(appid)){
                            Toast.makeText(AdminApplicantEdit.this, "Found course", Toast.LENGTH_SHORT).show();
                            try {
                                FirebaseDatabase.getInstance().getReference().child("ApplicantComponents").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                        if (snapshot1.hasChild(appid)) {
                                            Toast.makeText(AdminApplicantEdit.this, "Has components", Toast.LENGTH_SHORT).show();
                                            for (DataSnapshot ss : snapshot1.child(appid).getChildren()
                                            ) {
                                                clss.add(ss.getValue(ComponentsClass.class));
                                            }
                                            Toast.makeText(AdminApplicantEdit.this, ""+clss.size(), Toast.LENGTH_SHORT).show();
                                            adapter.notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(AdminApplicantEdit.this, "hallo", Toast.LENGTH_SHORT).show();
                                            FirebaseDatabase.getInstance().getReference().child("Components").child(snapshot.child(appid).getValue(String.class)).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                                    for (DataSnapshot sss : snapshot2.getChildren()) {
                                                        ComponentsClass obj=sss.getValue(ComponentsClass.class);
                                                        obj.setPercentage("");
                                                        clss.add(obj);
                                                        //Toast.makeText(AdminCourseView.this,s.getValue(ComponentsClass.class).getName() , Toast.LENGTH_SHORT).show();
                                                    }
                                                    Toast.makeText(AdminApplicantEdit.this, ""+clss.size(), Toast.LENGTH_SHORT).show();
                                                    adapter.notifyDataSetChanged();
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }catch (Exception e){
                                Toast.makeText(AdminApplicantEdit.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(AdminApplicantEdit.this, "Applicant Hasent Applied to a course yet", Toast.LENGTH_SHORT).show();
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        findViewById(R.id.admin_add_applicant_component).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("ApplicantComponents").child(appid).setValue(clss);
                Toast.makeText(AdminApplicantEdit.this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}