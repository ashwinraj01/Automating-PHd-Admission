package com.winash.uniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winash.uniapp.ui.AddAssessment.AddAssessmentAdapter;
import com.winash.uniapp.ui.AddCourse.Course;

import java.util.ArrayList;

public class AddAssessmentMain extends AppCompatActivity {
private TextView addassess;
    static ListView listView;
    static ArrayList<ComponentsClass> items;
    static AddAssessmentMainAdapter adapter;
    EditText input;
    ImageView enter;
    static Context con;
    Button component;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment_main);
        try {
            Course temp=(Course)getIntent().getSerializableExtra("course");
            ref= FirebaseDatabase.getInstance().getReference();
            addassess =findViewById(R.id.add_assessment_course_name);
            addassess.setText(temp.getCoursename());
            listView=findViewById(R.id.listView_addassessment);
            input=findViewById(R.id.inputcomponents);
            enter=findViewById(R.id.addassessment);
            component=findViewById(R.id.addassessmentbutton);
            items = new ArrayList<ComponentsClass>();
            con=getApplicationContext();
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    makeToast("Removed: " + items.get(i));
                    removeItem(i);
                    return false;
                }
            });
            adapter = new AddAssessmentMainAdapter(getApplicationContext(), items);
            listView.setAdapter(adapter);


            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = input.getText().toString();
                    if (text == null || text.length() == 0) {
                        makeToast("Enter the number.");
                    }else{
                        for(int  i=0;i<Integer.parseInt(text);i++) {
                            addItem(new ComponentsClass());
                        }
                    }
                    adapter.notifyDataSetChanged();
                    makeToast("Added: " + text);
                }

            });
            component.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        ref.child("Components").child(temp.getCoursename()).setValue(items).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                Toast.makeText(AddAssessmentMain.this, "completed", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(AddAssessmentMain.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public static void removeItem(int remove){
        Toast.makeText(con, Integer.toString(remove), Toast.LENGTH_SHORT).show();
        items.remove(remove);
        adapter.notifyDataSetChanged();
    }
    public static void addItem(ComponentsClass item) {
        items.add(item);

    }

    Toast t;
    private void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}