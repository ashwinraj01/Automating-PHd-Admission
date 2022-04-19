package com.winash.uniapp;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddAssessmentMainAdapter extends ArrayAdapter<ComponentsClass> implements Cloneable{
    ArrayList<ComponentsClass> list;
    Context context;

    public AddAssessmentMainAdapter(Context context, ArrayList<ComponentsClass>items){
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
            convertView.setId(positions);
            EditText name = convertView.findViewById(R.id.Component_name);
            name.setHint("Enter Component Name");
            name.setText(list.get(positions).getName());
            EditText percentage = convertView.findViewById(R.id.Component_Marks);
            percentage.setHint("Enter Component marks");
            percentage.setText(list.get(positions).getPercentage());
            name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try {
                        ComponentsClass c= (ComponentsClass) list.get(positions);
                        c.setName(charSequence.toString());
                        list.set(positions,c);

                    }catch (Exception e){
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
                    ComponentsClass c=list.get(positions);
                    c.setPercentage(charSequence.toString());
                    list.set(positions,c);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
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
