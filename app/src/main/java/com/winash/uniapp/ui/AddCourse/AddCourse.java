package com.winash.uniapp.ui.AddCourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winash.uniapp.AdminDashboard;
import com.winash.uniapp.LoginUser;
import com.winash.uniapp.R;
import com.winash.uniapp.RegisterUser;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCourse extends Fragment {
private TextView CourseName,Department,Duration,Outcome,Syllabus,Campus,q10th,q12th,qUG,qPG,Deadline;
private Button addcourse;
private FirebaseAuth fAuth;
private DatabaseReference ref;
private ProgressBar progress;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        v = inflater.inflate(R.layout.fragment_add_course,container, false);
        CourseName=(TextView) v.findViewById(R.id.InputCourseName);
        Department=(TextView) v.findViewById(R.id.InputCourseDepartment);
        Duration=(TextView) v.findViewById(R.id.InputCourseDuration);
        Outcome=(TextView) v.findViewById(R.id.InputCourseOutcome);
        Syllabus=(TextView) v.findViewById(R.id.InputCourseSyllabus);
        Campus=(TextView) v.findViewById(R.id.InputCourseCampus);
        q10th=(TextView) v.findViewById(R.id.InputCourse10th);
        q12th=(TextView) v.findViewById(R.id.InputCourse12th);
        qUG=(TextView) v.findViewById(R.id.InputCourseUG);
        qPG=(TextView) v.findViewById(R.id.InputCoursePG);
        addcourse=(Button) v.findViewById(R.id.AddCourserBtn);
        fAuth=FirebaseAuth.getInstance();
        ref=FirebaseDatabase.getInstance().getReference();
        progress=(ProgressBar) v.findViewById(R.id.progressBarAddCourse);
        Deadline=(TextView) v.findViewById(R.id.InputCourseDeadline);
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcourse();
            }
        });
        return v;
    }
    public void addcourse(){
        String coursename=CourseName.getText().toString().trim();
        String department=Department.getText().toString().trim();
        String duration=Duration.getText().toString().trim();
        String outcome=Outcome.getText().toString().trim();
        String syllabus=Syllabus.getText().toString().trim();
        String campus=Campus.getText().toString().trim();
        String tempq10=q10th.getText().toString().trim();
        String tempq12=q12th.getText().toString().trim();
        String tempqug=qUG.getText().toString().trim();
        String tempqpg=qPG.getText().toString().trim();
        String dead=Deadline.getText().toString().trim();
        if(coursename.isEmpty())
        {
            CourseName.setError("Course Name Required!!!");
            CourseName.requestFocus();
            return;
        }
        if(department.isEmpty())
        {
            Department.setError("Department Name Required!!!");
            Department.requestFocus();
            return;
        }
        if(duration.isEmpty())
        {
            Duration.setError("Duration Name Required!!!");
            Duration.requestFocus();
            return;
        }if(outcome.isEmpty())
        {
            Outcome.setError("Outcome Name Required!!!!");
            Outcome.requestFocus();
            return;
        }if(syllabus.isEmpty())
        {
            Syllabus.setError("Syllabus Name Required!!!");
            Syllabus.requestFocus();
            return;
        }if(campus.isEmpty())
        {
            Campus.setError("Campus Name Required!!!");
            Campus.requestFocus();
            return;
        }if(tempq10.isEmpty()||!isInteger(tempq10)||tempq10.length()>2)
        {
            q10th.setError("10th mark Invalid!!!");
            q10th.requestFocus();
            return;
        }if(tempq12.isEmpty()||!isInteger(tempq12)||tempq12.length()>2)
        {
            q12th.setError("12th mark Invalid!!!");
            q12th.requestFocus();
            return;
        }if(tempqug.isEmpty()||!floatchecker(tempqug))
        {
            qUG.setError("UG mark Invalid!!!");
            qUG.requestFocus();
            return;
        }if(tempqpg.isEmpty()||!floatchecker(tempqpg))
        {
            qPG.setError("PG mark Invalid!!!");
            qPG.requestFocus();
            return;
        }
        if(dead.isEmpty())
        {
            Deadline.setError("Date Invalid!!!");
            Deadline.requestFocus();
            return;
        }
        progress.setVisibility(View.VISIBLE);
        addcourse.setVisibility(View.GONE);
        Course newcourse=new Course(campus,coursename,department,duration,outcome,tempqpg,tempq10,tempq12,syllabus,tempqug,dead);
        ref.child("Course").child(coursename).setValue(newcourse).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Course has been successfully Added", Toast.LENGTH_SHORT).show();
                    progress.setProgress(View.INVISIBLE);
                    startActivity(new Intent(getActivity(), AdminDashboard.class));
                }else
                {
                    addcourse.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "An error Occured while Registering", Toast.LENGTH_SHORT).show();
                    progress.setProgress(View.INVISIBLE);
                }
                progress.setProgress(View.INVISIBLE);
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
    public boolean floatchecker(String a){
        int flag=0;
        char[] chars = a.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(c=='.'){
                flag++;
            }
        }
        if(flag==1)
            return true;
        else
            return false;
    }
}