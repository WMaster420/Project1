package com.example.myapplication;

import android.app.Activity;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends Fragment {

    EditText name, email, pass, city, gender, birthdate;
    Button btn_register;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String UserID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container,false);
        name = view.findViewById(R.id.Tname);
        email = view.findViewById(R.id.Temail);
        pass = view.findViewById(R.id.Tpass);
        city = view.findViewById(R.id.Tcity);
        gender = view.findViewById(R.id.Tgender);
        birthdate = view.findViewById(R.id.Bdate);
        btn_register = (Button) view.findViewById(R.id.Rbutton);

        fAuth= FirebaseAuth.getInstance();


        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getContext(), Dashboard.class));
            getActivity().finish();
        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Etr_name = name.getText().toString().trim();
                String Etr_email = email.getText().toString().trim();
                String Etr_pass = pass.getText().toString().trim();
                String Etr_city = city.getText().toString().trim();
                String Etr_gender = gender.getText().toString().trim();
                String Etr_birthdate = birthdate.getText().toString().trim();

                if (TextUtils.isEmpty(Etr_email)){
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(Etr_pass)){
                    pass.setError("Password is empty");
                    return;
                }
                if (Etr_pass.length() < 6){
                    pass.setError("Password must be atleast 6 char long.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(Etr_email, Etr_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Welcome :)", Toast.LENGTH_SHORT).show();
                            UserID = fAuth.getCurrentUser().getUid();
                            fstore=FirebaseFirestore.getInstance();
                            DocumentReference documentReference = fstore.collection("Users").document(UserID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name",Etr_name);
                            user.put("Email",Etr_email);
                            user.put("city", Etr_city);
                            user.put("birthdate", Etr_birthdate);
                            user.put("gender",Etr_gender);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Data sent to cloud", Toast.LENGTH_SHORT).show();
                                }
                            });
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