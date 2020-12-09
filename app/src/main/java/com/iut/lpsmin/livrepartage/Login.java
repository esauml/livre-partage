package com.iut.lpsmin.livrepartage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.iut.lpsmin.livrepartage.model.Firebase;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEmail, loginPassword;
    private Button singIn;

    private FirebaseAuth mAuth;
    private ProgressBar loginProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        singIn = (Button) findViewById(R.id.loginBtn);
        singIn.setOnClickListener(this);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);

        mAuth  = FirebaseAuth.getInstance();


        //loginProgressBar =(ProgressBar) findViewById(R.id.loginProgressBar);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reRegister:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.loginBtn:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String logEmail = loginEmail.getText().toString().trim();
        String logpassword = loginPassword.getText().toString().trim();


        if (logEmail.isEmpty()){
            loginEmail.setError("Email is Required");
            return;
        }

        if (TextUtils.isEmpty(logpassword)){
            loginPassword.setError("Password is required");
            return;
        }
        if (logpassword.length() <6){
            loginPassword.setError("Password must be greater than 6 characters");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(logEmail).matches()){
            loginPassword.setError("Please provoide a valide Email");
            loginPassword.requestFocus();
            return;
        }
       // loginProgressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(logEmail,logpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this,FairePub.class));
                }else {
                    Toast.makeText(Login.this,"Failed to Login plsr check your Email or Password", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


}