package com.winash.uniapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddAssessmentMainAdapter extends RecyclerView.Adapter<AddAssessmentMainAdapter.MyViewHolder>{
    ArrayList<ComponentsClass> list;
    Context context;

    public AddAssessmentMainAdapter(Context context, ArrayList<ComponentsClass> items){
        this.context = context;
        this.list = items;
    }

    @NonNull
    @Override
    public AddAssessmentMainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.assessment_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {
        holder.coursename.setText(list.get(position).getName());
        holder.percentage.setText(list.get(position).getPercentage());

    }
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText coursename,percentage;
        Button b;
        ImageView remove;
        String cn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                coursename = itemView.findViewById(R.id.Component_name);
                coursename.setHint("Enter Component Name");
                percentage = itemView.findViewById(R.id.Component_Marks);
                percentage.setHint("Enter Component marks");
                remove = itemView.findViewById(R.id.remove);
                coursename.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try {
                            ComponentsClass c = (ComponentsClass) list.get(getAbsoluteAdapterPosition());
                            c.setName(charSequence.toString());
                            list.set(getAbsoluteAdapterPosition(), c);

                        } catch (Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                percentage.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ComponentsClass c = list.get(getAbsoluteAdapterPosition());
                        c.setPercentage(charSequence.toString());
                        list.set(getAbsoluteAdapterPosition(), c);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddAssessmentMain.removeItem(getAbsoluteAdapterPosition());
                    }
                });

            }catch (Exception e){
                Toast.makeText(itemView.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        }
    }
