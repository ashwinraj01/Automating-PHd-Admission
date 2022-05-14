package com.winash.uniapp.ui.AddAssessment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winash.uniapp.AddAssessmentMain;
import com.winash.uniapp.AdminCourseView;
import com.winash.uniapp.AdminDashboard;
import com.winash.uniapp.LoginUser;
import com.winash.uniapp.R;
import com.winash.uniapp.ui.AddCourse.Course;

import java.io.Serializable;
import java.util.ArrayList;

public class AddAssessmentAdapter extends RecyclerView.Adapter<AddAssessmentAdapter.MyViewHolder> {
    public ArrayList<Course> list;
    public Context context;
    public AddAssessmentAdapter(ArrayList<Course> a,Context context){
        this.list=a;
        this.context=context;
    }
    public void filterList (ArrayList<Course> filterlist){
        list=filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AddAssessmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_search_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course=list.get(position);
        holder.coursename.setText(course.getCoursename());
        holder.campus.setText(course.getCampus());
        holder.syllabus.setText(course.getSyllabus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView coursename,campus,syllabus;
        Button b;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coursename=itemView.findViewById(R.id.CourseNameDisplay);
            campus=itemView.findViewById(R.id.CourseCampusDisplay);
            syllabus=itemView.findViewById(R.id.CourseSyllabusDisplay);
            b=itemView.findViewById(R.id.buttoncard);
            b.setText("Add Assessment");
            itemView.findViewById(R.id.buttoncard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent now=new Intent(view.getContext(), AddAssessmentMain.class);
                    try {
                        now.putExtra("course", list.get(getAdapterPosition()));
                        context.startActivity(now);
                    }catch (Exception e){
                        Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
