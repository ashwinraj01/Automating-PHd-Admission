package com.winash.uniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winash.uniapp.ui.AddCourse.Course;

public class AdminCourseUpdation extends AppCompatActivity {
    private EditText coursename,coursecampus,department,duration,outcome,syllabus,ug,pg,q10,q12,dead;
    private Button update;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course_updation);
        Course now= (Course) getIntent().getSerializableExtra("coursee");
        coursename=(EditText) findViewById(R.id.updacn);
        coursecampus=(EditText) findViewById(R.id.updacampus);
        department=(EditText) findViewById(R.id.updadepartment);
        duration=(EditText) findViewById(R.id.updaduration);
        outcome=(EditText) findViewById(R.id.updaoutcome);
        syllabus=(EditText) findViewById(R.id.updasyllabus);
        ug=(EditText) findViewById(R.id.updaug);
        pg=(EditText) findViewById(R.id.updapg);
        q10=(EditText) findViewById(R.id.upda10th);
        q12=(EditText) findViewById(R.id.upda12th);
        update=(Button)findViewById(R.id.buttoninsideupdate);
        dead=(EditText) findViewById(R.id.updadeadline);
        dead.setText(now.getDeadline());
        coursename.setText(now.getCoursename());
        coursecampus.setText(now.getCampus());
        department.setText(now.getDepartment());
        duration.setText(now.getDuration());
        outcome.setText(now.getOutcome());
        syllabus.setText(now.getSyllabus());
        ug.setText(now.getUg().toString());
        pg.setText(now.getPg().toString());
        q10.setText(now.getQ10().toString());
        q12.setText(now.getQ12().toString());
        ref= FirebaseDatabase.getInstance().getReference();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("Course").child(now.getCoursename()).removeValue();
                updatecourse();
            }
        });
    }
    public void updatecourse(){

        String cn=coursename.getText().toString().trim();
        String d=department.getText().toString().trim();
        String du=duration.getText().toString().trim();
        String out=outcome.getText().toString().trim();
        String sy=syllabus.getText().toString().trim();
        String c=coursecampus.getText().toString().trim();
        String tempq10=q10.getText().toString().trim();
        String tempq12=q12.getText().toString().trim();
        String tempqug=ug.getText().toString().trim();
        String tempqpg=pg.getText().toString().trim();
        String deadline=dead.getText().toString();

        if(cn.isEmpty())
        {
            coursename.setError("Course Name Required!!!");
            coursename.requestFocus();
            return;
        }
        if(d.isEmpty())
        {
            department.setError("Department Name Required!!!");
            department.requestFocus();
            return;
        }
        if(du.isEmpty())
        {
            duration.setError("Duration Name Required!!!");
            duration.requestFocus();
            return;
        }if(out.isEmpty())
        {
            outcome.setError("Outcome Name Required!!!!");
            outcome.requestFocus();
            return;
        }if(sy.isEmpty())
        {
            syllabus.setError("Syllabus Name Required!!!");
            syllabus.requestFocus();
            return;
        }if(c.isEmpty())
        {
            coursecampus.setError("Campus Name Required!!!");
            coursecampus.requestFocus();
            return;
        }if(tempq10.isEmpty()||!isInteger(tempq10))
        {
            q10.setError("10th mark Required!!!");
            q10.requestFocus();
            return;
        }if(tempq12.isEmpty()||!isInteger(tempq12))
        {
            q12.setError("12th mark Required!!!");
            q12.requestFocus();
            return;
        }if(tempqug.isEmpty()||!isInteger(tempqug))
        {
            ug.setError("UG mark Required!!!");
            ug.requestFocus();
            return;
        }if(tempqpg.isEmpty()||!isInteger(tempqpg))
        {
            pg.setError("PG mark Required!!!");
            pg.requestFocus();
            return;
        }
        if(deadline.isEmpty())
        {
            dead.setError("Date Required!!!");
            dead.requestFocus();
            return;
        }
        Course newcourse=new Course(c,cn,d,du,out,tempqpg,tempq10,tempq12,sy,tempqug,deadline);
        ref.child("Course").child(cn).setValue(newcourse).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AdminCourseUpdation.this, "Course has been successfully Updated!!!!", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(AdminCourseUpdation.this, "An error Occured while Updating. Try Again!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isInteger(String s){
        int flag=0;
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                flag++;
            }
        }
        if(flag==s.length())
            return true;
        else
            return false;
    }
}