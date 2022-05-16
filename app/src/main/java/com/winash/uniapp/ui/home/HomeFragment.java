package com.winash.uniapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.R;
import com.winash.uniapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
TextView simply;
FirebaseAuth fauth;
public Button hobutn;


private FragmentHomeBinding binding;
public TextView progress;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        fauth=FirebaseAuth.getInstance();
        View v = null;
        v = inflater.inflate(R.layout.fragment_home,container, false);
        progress=v.findViewById(R.id.Progress_in_homepage);
        FirebaseDatabase.getInstance().getReference("Progress").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    progress.setText(snapshot.getValue(String.class));
                else
                    progress.setText("Initial Phase");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;

    }
}