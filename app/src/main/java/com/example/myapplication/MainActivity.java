package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button RegButton = findViewById(R.id.Bregister);
        Button LogButton = findViewById(R.id.Blogin);



        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                register reg = new register();
                fragmentTransaction.replace(R.id.fragment_container,reg);
                fragmentTransaction.commit();
            }
        });

        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                login log = new login();
                fragmentTransaction.replace(R.id.fragment_container,log);
                fragmentTransaction.commit();
            }
        });

    }
}