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

public class Signup extends AppCompatActivity {

    ImageView backBtn;
    TextView noAccount;
    EditText usernametxt, emailtxt, phoneNumtxt, passwordtxt;
    Button regBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

//
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
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        backBtn = findViewById(R.id.backBtn);
        noAccount =  findViewById(R.id.noAccount);
        usernametxt = findViewById(R.id.usernametxt);
        emailtxt = findViewById(R.id.emailtxt);
        phoneNumtxt = findViewById(R.id.phoneNumtxt);
        passwordtxt =findViewById(R.id.passwordtxt);
        regBtn = findViewById(R.id.regBtn);
        progressBar = findViewById(R.id.progressBar);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                String username, email, phoneNum, password;
                username = String.valueOf(usernametxt.getText()).trim();
                email = String.valueOf(emailtxt.getText());
                phoneNum = String.valueOf(phoneNumtxt.getText());
                password = String.valueOf(passwordtxt.getText());


                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(Signup.this,"Enter username",Toast.LENGTH_SHORT).show();
                    emailtxt.setError("Field is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
//                    Toast.makeText(Signup.this,"Enter email",Toast.LENGTH_SHORT).show();
                    emailtxt.setError("Field is required");
                    return;
                }
//
//                if (TextUtils.isEmpty(phoneNum)){
//                    Toast.makeText(Signup.this,"Enter your phone number",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)){
//                    Toast.makeText(Signup.this,"Enter password",Toast.LENGTH_SHORT).show();
//                    return;
//                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(Signup.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), login.class));
                                    finish();


                                } else {

                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

        //this is for back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, login.class));
            }
        });
        //this is for if the user wants to sign up
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, login.class));
            }
        });



    }
}