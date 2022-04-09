package com.winash.uniapp.ui.Blacklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.Applicant;
import com.winash.uniapp.R;
import com.winash.uniapp.ui.AddCourse.Course;
import com.winash.uniapp.ui.SearchCourse.SearchCourseAdapter;

import java.util.ArrayList;

public class Blacklist extends Fragment {

    private RecyclerView recycleview;
    private SearchView search;
    private ArrayList<Applicant> list;
    private DatabaseReference ref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_blacklist_user,container, false);
        View xyz=view;
        recycleview=(RecyclerView) view.findViewById(R.id.Search_Recycler_View_blacklist);
        search=(SearchView) view.findViewById(R.id.Search_course_field_blacklist);
        list=new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference();
        BlacklistAdapter adapter= new BlacklistAdapter(list,getContext());
        recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleview.setAdapter(adapter);
        recycleview.setHasFixedSize(true);
        ref.child("Applicant").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    for(DataSnapshot db:snapshot.getChildren()){
                        Applicant c=db.getValue(Applicant.class);
                        list.add(c);
                    }
                    adapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    Toast.makeText(xyz.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Applicant> filteredlist = new ArrayList<>();
                for(Applicant item: list){
                    if(item.getFname().toLowerCase().contains(s.toLowerCase()) || item.getLname().toLowerCase().contains(s.toLowerCase()) )
                        filteredlist.add(item);
                }
                adapter.filterList(filteredlist);
                return false;
            }
        });
        return view;
    }
}