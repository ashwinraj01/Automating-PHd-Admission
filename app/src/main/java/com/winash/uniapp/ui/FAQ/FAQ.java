package com.winash.uniapp.ui.FAQ;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.R;
import com.winash.uniapp.ui.SearchCourse.SearchCourseAdapter;
import com.winash.uniapp.version;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class FAQ extends Fragment {
    private RecyclerView recyclerview;
    private List<FAQ_question> versionList;
    private EditText reply;
    private DatabaseReference ref;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_faq,container, false);
        versionList =new ArrayList<FAQ_question>();
        try {
            recyclerview=view.findViewById(R.id.recyclerView_admin_faq);
            reply=view.findViewById(R.id.ans_admin);
            String ans=reply.getText().toString().trim();
            ref= FirebaseDatabase.getInstance().getReference("Faqs");
            FAQfragementadapter adapter= new FAQfragementadapter(versionList,view.getContext());
            recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerview.setAdapter(adapter);
            recyclerview.setHasFixedSize(true);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot s:snapshot.getChildren()){
                        FAQ_question question=s.getValue(FAQ_question.class);
                        versionList.add(question);
                    }
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            Toast.makeText(getView().getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }


        return view;
    }
}