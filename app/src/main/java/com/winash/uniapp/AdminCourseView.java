package com.winash.uniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.ui.AddCourse.Course;
import com.winash.uniapp.ui.SearchCourse.SearchCourse;
import com.winash.uniapp.AddAssessmentMainAdapter;
import java.util.ArrayList;

public class AdminCourseView extends AppCompatActivity {
    private TextView coursename,coursecampus,department,duration,outcome,syllabus,ug,pg,q10,q12,dead;
    private Button del,upda;
    static RecyclerView recycleview;
    private DatabaseReference ref;
    static ArrayList<ComponentsClass> clss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clss=new ArrayList<>();
        setContentView(R.layout.activity_admin_course_view);
        recycleview = findViewById(R.id.CourseViewAssessmentComponents);
        Course now = (Course) getIntent().getSerializableExtra("course");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Components");
        try {
           CourseViewComponent adapter = new CourseViewComponent(getApplicationContext(), clss);
            recycleview.setLayoutManager(new LinearLayoutManager(this));
            recycleview.setAdapter(adapter);
            recycleview.setHasFixedSize(true);
            db.child(now.getCoursename()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        clss.add(s.getValue(ComponentsClass.class));
                        //Toast.makeText(AdminCourseView.this,s.getValue(ComponentsClass.class).getName() , Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

//       try {
//           Toast.makeText(this, clss.get(0).getName(), Toast.LENGTH_SHORT).show();
//       }catch (Exception e)
//       {
//           Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//       }

        coursename = (TextView) findViewById(R.id.CourseNameNew);
        coursecampus = (TextView) findViewById(R.id.CourseCampusNew);
        department = (TextView) findViewById(R.id.CourseDepartmentNew);
        duration = (TextView) findViewById(R.id.CourseDurationNew);
        outcome = (TextView) findViewById(R.id.CourseOutcomeNew);
        syllabus = (TextView) findViewById(R.id.CourseSyllabusNew);
        ug = (TextView) findViewById(R.id.CourseUGNew);
        pg = (TextView) findViewById(R.id.CoursePGNew);
        q10 = (TextView) findViewById(R.id.Course10thNew);
        q12 = (TextView) findViewById(R.id.Course12thNew);
        upda = (Button) findViewById(R.id.courseUpdationBtn);
        dead = (TextView) findViewById(R.id.updadeadlineview);
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
        del = (Button) findViewById(R.id.CourseDeleteBtn);
        ref = FirebaseDatabase.getInstance().getReference();
    } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
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