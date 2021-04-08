package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, (R.string.open), (R.string.close));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        dashboardFragment board = new dashboardFragment();
        fragmentTransaction.replace(R.id.Dash_fragment,board);
        fragmentTransaction.commit();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.dash){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            dashboardFragment board = new dashboardFragment();
            fragmentTransaction.replace(R.id.Dash_fragment,board);
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.prof){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            profileFragment userprof = new profileFragment();
            fragmentTransaction.replace(R.id.Dash_fragment,userprof);
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.Menu_log){
            FirebaseAuth.getInstance().signOut();
            onDestroy();
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            Toast.makeText(this, "Successfully Logged out", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}