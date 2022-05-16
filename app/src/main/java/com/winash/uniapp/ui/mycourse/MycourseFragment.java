package com.winash.uniapp.ui.mycourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.R;
import com.winash.uniapp.UserCourseView;
import com.winash.uniapp.activity_update_profile;
import com.winash.uniapp.databinding.FragmentHomeBinding;
import com.winash.uniapp.navigationui;
import com.winash.uniapp.ui.AddCourse.Course;

import java.util.ArrayList;

public class MycourseFragment extends Fragment {

    private TextView txt1;
    private Button button;
    private ArrayList<Course> list;
    private DatabaseReference ref;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view=null;
        view = inflater.inflate(R.layout.fragment_mycourse,container, false);
        View xyz=view;
        txt1=view.findViewById(R.id.textView221);
        button = view.findViewById(R.id.mycouresebutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c=new Intent(view.getContext(), UserCourseView.class);
              //  Toast.makeText(itemView.getContext(), ""+list.get(getAbsoluteAdapterPosition()).getCoursename(), Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(c);
            }
        });


        ref= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Registercourse").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s :
                        snapshot.getChildren()) {
                    if(s.getKey()== FirebaseAuth.getInstance().getUid())
                        txt1.setText(s.getValue(String.class));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
}