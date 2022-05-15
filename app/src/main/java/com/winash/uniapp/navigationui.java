package com.winash.uniapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winash.uniapp.databinding.ActivityAdminDashboardBinding;
import com.winash.uniapp.databinding.ActivityNavigationuiBinding;

import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class navigationui extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationuiBinding binding;
    private TextView email,name;
    private DatabaseReference ref;
    private View hview;
    private FirebaseAuth fauth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private ImageButton imgbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityNavigationuiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarNavigationui.toolbar);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Applicant");

        fauth=FirebaseAuth.getInstance();
        DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;
            hview=navigationView.getHeaderView(0);
            email=hview.findViewById(R.id.Email_id_for_user);
            name=hview.findViewById(R.id.Username_in_mainpage);

        reference.child(fauth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Applicant a=snapshot.getValue(Applicant.class);
                email.setText(a.getEmail());
                name.setText(a.getFname()+" "+a.getLname());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            imgbutton = hview.findViewById(R.id.userprofilebutton);

            imgbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(navigationui.this,activity_update_profile.class));
                }
            });

            email.setText(fauth.getCurrentUser().getEmail());
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_mycourse)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigationui);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigationui, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_updateprofile:
                startActivity(new Intent(this, activity_update_profile.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, LoginUser.class));
                fauth.signOut();
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed () {
        //finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigationui);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}