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
import com.winash.uniapp.R;
import com.winash.uniapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
TextView simply;
FirebaseAuth fauth;
public Button hobutn;


private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        fauth=FirebaseAuth.getInstance();
        View v = null;
        v = inflater.inflate(R.layout.fragment_home,container, false);
        return v;

    }
}