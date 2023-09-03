package com.example.cyclecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    ImageView backBtn;
    TextView noAccount;
    EditText emailtxt, passwordtxt;
    Button loginBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            startActivity(new Intent(getApplicationContext(), Home.class));
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        backBtn = findViewById(R.id.backBtn);
        noAccount =  findViewById(R.id.noAccount);
        mAuth = FirebaseAuth.getInstance();
        backBtn = findViewById(R.id.backBtn);
        noAccount =  findViewById(R.id.noAccount);
        emailtxt = findViewById(R.id.emailtxt);
        loginBtn = findViewById(R.id.loginBtn);
        passwordtxt =findViewById(R.id.passwordtxt);
        progressBar = findViewById(R.id.progressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String email, password;
                email = String.valueOf(emailtxt.getText());
                password = String.valueOf(passwordtxt.getText());


                if (email.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(login.this,"Enter username",Toast.LENGTH_SHORT).show();
                    emailtxt.setError("Field is required");
                    emailtxt.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(login.this,"Enter email",Toast.LENGTH_SHORT).show();
                    passwordtxt.setError("Field is required");
                    passwordtxt.requestFocus();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(login.this, "Authentication success.",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            } else {

                                Toast.makeText(login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });



        //this is for "if you don't have an account" button
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, Signup.class));
            }
        });


//this is for back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, GetStarted.class));
            }
        });
    }
}