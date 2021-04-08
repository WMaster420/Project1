package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;


public class profileFragment extends Fragment {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String UserID;

    TextView name, email, city, birthdate, gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        name= view.findViewById(R.id.Fname);
        email= view.findViewById(R.id.Femail);
        city= view.findViewById(R.id.Fcity);
        birthdate= view.findViewById(R.id.Fdate);
        gender=view.findViewById(R.id.Fgender);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        UserID = fauth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("Users").document(UserID);
        documentReference.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("Email"));
                city.setText(value.getString("city"));
                birthdate.setText(value.getString("birthdate"));
                gender.setText(value.getString("gender"));
            }
        });
        return view;
    }
}