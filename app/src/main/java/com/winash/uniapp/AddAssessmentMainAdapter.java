package com.winash.uniapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddAssessmentMainAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public AddAssessmentMainAdapter(Context context, ArrayList<String>items){
        super(context, R.layout.assessment_list_view, items);
        this.context = context;
        list = items;
    }


    @NonNull
    @Override
    public View getView(int positions, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.assessment_list_view, null);

            TextView number = convertView.findViewById(R.id.Component_name);
            number.setHint("Enter Component Name");

            TextView name = convertView.findViewById(R.id.Component_Marks);
            name.setHint("Enter Component marks");


            ImageView remove = convertView.findViewById(R.id.remove);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddAssessmentMain.removeItem(positions);
                }
            });
        }
        return convertView;
    }
}
