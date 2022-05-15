package com.winash.uniapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.ui.AddCourse.Course;

import java.util.ArrayList;

public class UserCourseView extends AppCompatActivity {
    private TextView coursename,coursecampus,department,duration,outcome,syllabus,ug,pg,q10,q12,dead;
    private Button register;
    static RecyclerView recycleview;
    private DatabaseReference ref;
    static ArrayList<ComponentsClass> clss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clss=new ArrayList<>();
        setContentView(R.layout.user_view_course);
        recycleview = findViewById(R.id.CourseViewAssessmentComponents1);
        Course now = (Course) getIntent().getSerializableExtra("Course");
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

            coursename = (TextView) findViewById(R.id.CourseNameNew1);
            coursecampus = (TextView) findViewById(R.id.textView31);
            department = (TextView) findViewById(R.id.textView41);
            duration = (TextView) findViewById(R.id.textView51);
            outcome = (TextView) findViewById(R.id.textView61);
            syllabus = (TextView) findViewById(R.id.textView71);
            ug = (TextView) findViewById(R.id.textView161);
            pg = (TextView) findViewById(R.id.textView171);
            q10 = (TextView) findViewById(R.id.textView141);
            q12 = (TextView) findViewById(R.id.textView151);
            dead = (TextView) findViewById(R.id.updadeadlineview1);
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
            ref = FirebaseDatabase.getInstance().getReference();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}