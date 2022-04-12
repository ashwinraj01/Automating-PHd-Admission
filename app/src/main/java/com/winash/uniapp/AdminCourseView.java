package com.winash.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winash.uniapp.ui.AddCourse.Course;
import com.winash.uniapp.ui.SearchCourse.SearchCourse;

public class AdminCourseView extends AppCompatActivity {
    private TextView coursename,coursecampus,department,duration,outcome,syllabus,ug,pg,q10,q12,dead;
    private Button del,upda;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course_view);
        Course now=(Course) getIntent().getSerializableExtra("course");
    coursename=(TextView) findViewById(R.id.CourseNameNew);
    coursecampus=(TextView) findViewById(R.id.CourseCampusNew);
    department=(TextView) findViewById(R.id.CourseDepartmentNew);
    duration=(TextView) findViewById(R.id.CourseDurationNew);
    outcome=(TextView) findViewById(R.id.CourseOutcomeNew);
    syllabus=(TextView) findViewById(R.id.CourseSyllabusNew);
    ug=(TextView) findViewById(R.id.CourseUGNew);
    pg=(TextView) findViewById(R.id.CoursePGNew);
    q10=(TextView) findViewById(R.id.Course10thNew);
    q12=(TextView) findViewById(R.id.Course12thNew);
    upda=(Button)findViewById(R.id.courseUpdationBtn);
    dead=(TextView)findViewById(R.id.updadeadlineview);
    coursename.setText(now.getCoursename());
    coursecampus.setText(now.getCampus());
    department.setText(now.getDepartment());
    duration.setText(now.getDuration());
    outcome.setText(now.getOutcome());
    syllabus.setText(now.getSyllabus());
    dead.setText(now.getDeadline());
    ug.setText(now.getUg().toString());
    pg.setText(now.getPg().toString());
    q10.setText(now.getQ10().toString());
    q12.setText(now.getQ12().toString());
    del=(Button) findViewById(R.id.CourseDeleteBtn);
    ref=FirebaseDatabase.getInstance().getReference();
    del.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ref.child("Course").child(now.getCoursename()).removeValue();
            Toast.makeText(AdminCourseView.this, "Course Successfully Removed", Toast.LENGTH_SHORT).show();
            finish();
        }
    });
    upda.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent up=new Intent(view.getContext(),AdminCourseUpdation.class);
            up.putExtra("coursee",now);
                startActivity(up);
                finish();
        }
    });
    }
}