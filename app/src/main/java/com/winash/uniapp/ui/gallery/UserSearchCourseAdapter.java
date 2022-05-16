package com.winash.uniapp.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.winash.uniapp.LoginUser;
import com.winash.uniapp.RegisterUser;
import com.winash.uniapp.ViewAcitivity;
import com.winash.uniapp.ui.AddCourse.Course;
import com.winash.uniapp.R;
import com.winash.uniapp.UserCourseView;
import com.winash.uniapp.ui.AddCourse.Course;
import com.winash.uniapp.ui.mycourse.MycourseFragment;

import java.util.ArrayList;

public class UserSearchCourseAdapter extends RecyclerView.Adapter<UserSearchCourseAdapter.MyViewHolder> {
    public ArrayList<Course> list;
    public Context context;
    public Button viewbtn;

    FirebaseAuth fauth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    public UserSearchCourseAdapter(ArrayList<Course> a, Context context) {
        this.list=a;
        this.context=context;
    }


    public void filterList (ArrayList<Course> filterlist){
        list=filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.search_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course=list.get(position);
        holder.coursename.setText(course.getCoursename());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView coursename,campus,syllabus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewbtn = itemView.findViewById(R.id.but2);
            coursename=itemView.findViewById(R.id.CourseNameDisplay1);
            viewbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });
            itemView.findViewById(R.id.buttoncard1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Toast.makeText(view.getContext(), " Registered "+list.get(getAbsoluteAdapterPosition()).getCoursename(), Toast.LENGTH_SHORT).show();
                    rootNode = FirebaseDatabase.getInstance();
                    fauth=FirebaseAuth.getInstance();
                    reference=rootNode.getReference();
                    reference.child("Registercourse").child(fauth.getCurrentUser().getUid()).setValue(list.get(getAbsoluteAdapterPosition()).getCoursename()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(view.getContext(), "Registercourse", Toast.LENGTH_SHORT).show();

                            
                        }
                    });
                        
                }
            });
            itemView.findViewById(R.id.but2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent c=new Intent(view.getContext(), UserCourseView.class);
                    c.putExtra("Course",list.get(getAbsoluteAdapterPosition()));
                    Toast.makeText(itemView.getContext(), ""+list.get(getAbsoluteAdapterPosition()).getCoursename(), Toast.LENGTH_SHORT).show();
                    context.startActivity(c);
                }
            });
        }
    }
}