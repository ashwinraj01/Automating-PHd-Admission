package com.winash.uniapp.ui.SearchCourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winash.uniapp.R;
import com.winash.uniapp.ui.AddCourse.Course;

import java.util.ArrayList;
import java.util.Locale;

public class SearchCourseAdapter extends RecyclerView.Adapter<SearchCourseAdapter.MyViewHolder> implements Filterable {
    public ArrayList<Course> list;
    public ArrayList<Course> listfull;
    public Context context;

    public SearchCourseAdapter(ArrayList<Course> a,Context context){
        this.list=a;
        this.listfull=new ArrayList<Course>(list);
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    @Override
    public Filter getFilter() {
        return filterfull;
    }
    public Filter filterfull = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Course> filteredlist = new ArrayList<Course>();

            if(charSequence == null || charSequence.length()==0){
                filteredlist.addAll(listfull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Course item: listfull){
                    if(item.getCoursename().toLowerCase().contains(filterPattern))
                        filteredlist.add(item);
                }
            }
            FilterResults results= new FilterResults();
            results.values=filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((ArrayList<Course>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView coursename,campus,syllabus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coursename=itemView.findViewById(R.id.CourseNameDisplay);
            campus=itemView.findViewById(R.id.CourseCampusDisplay);
            syllabus=itemView.findViewById(R.id.CourseSyllabusDisplay);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getAdapterPosition();
                }
            });
        }
    }
}
