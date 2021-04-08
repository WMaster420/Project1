package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fauth=FirebaseAuth.getInstance();
        if (fauth.getCurrentUser() != null){
            startActivity(new Intent(this, Dashboard.class));
            finish();
        }

        Button lbutton = findViewById(R.id.LogButton);
        Button Rbutton = findViewById(R.id.RegButton);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                login log = new login();
                fragmentTransaction.replace(R.id.fragment_container,log);
                fragmentTransaction.commit();
            }
        });

        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                register reg = new register();
                fragmentTransaction.replace(R.id.fragment_container,reg);
                fragmentTransaction.commit();
            }
        });

    }
}