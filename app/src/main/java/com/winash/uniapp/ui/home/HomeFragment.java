package com.winash.uniapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

<<<<<<< Updated upstream
import com.google.firebase.auth.FirebaseAuth;
import com.winash.uniapp.LoginUser;
=======
>>>>>>> Stashed changes
import com.winash.uniapp.R;
import com.winash.uniapp.databinding.FragmentHomeBinding;
import com.winash.uniapp.navigationui;

public class HomeFragment extends Fragment {
<<<<<<< Updated upstream
TextView simply;
FirebaseAuth fauth;
=======


private FragmentHomeBinding binding;

>>>>>>> Stashed changes
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        fauth=FirebaseAuth.getInstance();
        View v = null;
        v = inflater.inflate(R.layout.fragment_home,container, false);
        try {
            simply= v.findViewById(R.id.topcourses);
            simply.setText(fauth.getUid());
        }catch (Exception e)
        {
            Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        return v;
    }
}