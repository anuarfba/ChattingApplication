 package com.example.chatwithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.example.chatwithme.databinding.ActivitySignInBinding;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

 public class Sign_In extends AppCompatActivity {



ActivitySignInBinding binding;
private FirebaseAuth mAuth;
ProgressDialog progressDialog;
FirebaseDatabase firebaseDatabase;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         binding = ActivitySignInBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
         mAuth = FirebaseAuth.getInstance();
         firebaseDatabase = FirebaseDatabase.getInstance();
         progressDialog = new ProgressDialog(Sign_In.this);
         progressDialog.setTitle("Login");
         progressDialog.setMessage("Please wait \n, Validation is Progress");

//
//


         binding.btnSigin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (!binding.textEMail.getText().toString().isEmpty() && !binding.textPass.getText().toString().isEmpty()) {
                     progressDialog.show();
                     mAuth.signInWithEmailAndPassword(binding.textEMail.getText().toString(), binding.textPass.getText().toString())
                             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     progressDialog.dismiss();
                                     if (task.isSuccessful()) {
                                         Intent intent = new Intent(Sign_In.this, MainActivity.class);
                                         startActivity(intent);
                                     } else {
                                         Toast.makeText(Sign_In.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             });


                 } else {
                     Toast.makeText(Sign_In.this, "Enter credentials", Toast.LENGTH_SHORT).show();
                 }
             }
         });

         if (mAuth.getCurrentUser() != null) {
             Intent intent = new Intent(Sign_In.this, MainActivity.class);
             startActivity(intent);
         }

         binding.textAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Sign_In.this, Sign_Up.class);
                 startActivity(intent);
             }
         });




     }


 }