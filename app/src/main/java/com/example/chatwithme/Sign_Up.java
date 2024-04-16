package com.example.chatwithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatwithme.Model.Users;
import com.example.chatwithme.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Sign_Up extends AppCompatActivity {

ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = ActivitySignUpBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());



        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(Sign_Up.this);
              progressDialog.setTitle("Creating Account");
                progressDialog.setMessage("We're Creating your account");




        binding.BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (!binding.textUserName.getText().toString().isEmpty() && !binding.textEMail.getText().toString().isEmpty() && !binding.textPass.getText().toString().isEmpty()){
                    mAuth.createUserWithEmailAndPassword(binding.textEMail.getText().toString(),binding.textPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()){
                                        Toast.makeText(Sign_Up.this, "Sign Up is Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Sign_Up.this,MainActivity.class);
                                        startActivity(intent);
                                        Users users = new Users(binding.textUserName.getText().toString(),binding.textEMail.getText().toString(),binding.textPass.getText().toString());
                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(users);

                                    }
                                    else {
                                        Toast.makeText(Sign_Up.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Sign_Up.this, "you not write User name or Emain id or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.textAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_Up.this,Sign_In.class);
                startActivity(intent);
            }
        });
    }
}