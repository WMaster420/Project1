package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;


public class profileFragment extends Fragment {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    EditText name, email, city, birthdate, gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        name= view.findViewById(R.id.Fname);
        email= view.findViewById(R.id.Femail);
        city= view.findViewById(R.id.Fcity);
        birthdate= view.findViewById(R.id.Fgender);


        return view;
    }
}