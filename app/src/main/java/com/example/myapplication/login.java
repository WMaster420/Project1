package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login extends Fragment {
    EditText email, password;
    Button Logbtn;
    FirebaseAuth fauth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);
        email= view.findViewById(R.id.LEmail);
        password = view.findViewById(R.id.Lpass);
        Logbtn = view.findViewById(R.id.btnLogin);

        Logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Etr_email = email.getText().toString().trim();
                String Etr_pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(Etr_email)){
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Etr_pass)){
                    password.setError("Password is empty");
                    return;
                }
                if (Etr_pass.length() < 6){
                    password.setError("Password must be atleast 6 char long.");
                    return;
                }
                fauth=FirebaseAuth.getInstance();
            fauth.signInWithEmailAndPassword(Etr_email,Etr_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if ((task.isSuccessful())){
                        Toast.makeText(getContext(), "Welcome back :)", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), Dashboard.class));
                    }
                    else{
                        Toast.makeText(getContext(), "Something is wrong :( " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            }
        });

        return view;
    }
}