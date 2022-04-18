package com.winash.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.winash.uniapp.ui.AddAssessment.AddAssessmentAdapter;
import com.winash.uniapp.ui.AddCourse.Course;

import java.util.ArrayList;

public class AddAssessmentMain extends AppCompatActivity {
private TextView addassess;
    static ListView listView;
    static ArrayList<String> items;
    static AddAssessmentMainAdapter adapter;
    EditText input;
    ImageView enter;
    static Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment_main);
        Course temp=(Course)getIntent().getSerializableExtra("course");
        addassess =findViewById(R.id.add_assessment_course_name);
        addassess.setText(temp.getCoursename());
        listView=findViewById(R.id.listView_addassessment);
        input=findViewById(R.id.inputcomponents);
        enter=findViewById(R.id.addassessment);
        items = new ArrayList<>();
    con=getApplicationContext();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = items.get(i);
                makeToast(name);
            }
        });

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
                        addItem(text);
                    }
                    input.setText("");
                }
                adapter.notifyDataSetChanged();
                makeToast("Added: " + text);
            }

        });
    }
    public static void removeItem(int remove){
        Toast.makeText(con, Integer.toString(remove), Toast.LENGTH_SHORT).show();
        items.remove(remove);
        adapter.notifyDataSetChanged();
    }
    public static void addItem(String item) {
        items.add(item);

    }

    Toast t;
    private void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}