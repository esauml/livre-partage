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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.iut.lpsmin.livrepartage.model.Utilisateur;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView mBanner, mRegisterBtn;
    private EditText mFullName, mEmail, mPassword, mPhone;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth           = FirebaseAuth.getInstance();

        mBanner         = (TextView) findViewById(R.id.appNameB);
        mFullName       = (EditText) findViewById(R.id.fullNamee);
        mEmail          = (EditText) findViewById(R.id.email);
        mPassword       = (EditText) findViewById(R.id.password);
        mPhone          = (EditText) findViewById(R.id.phoneNumber);
        mRegisterBtn    = (Button) findViewById(R.id.registerBtn);
        mRegisterBtn.setOnClickListener(this);

        progressBar     = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appNameB:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerBtn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String fullName = mFullName.getText().toString().trim();
        String phoneNumber= mPhone.getText().toString().trim();


        if (fullName.isEmpty()){
            mFullName.setError("Full Name is Required");
            return;
        }
        if (phoneNumber.isEmpty()){
            mPhone.setError("Phone Number is Required");
            return;
        }


        if (email.isEmpty()){
            mEmail.setError("Email is Required");
            return;
        }

        if (TextUtils.isEmpty(password)){
            mPassword.setError("Password is required");
            return;
        }
        if (password.length() <6){
            mPassword.setError("Password must be greater than 6 characters");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please provoide a valide Email");
            mEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        fAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Utilisateur user = new Utilisateur(fullName, email, phoneNumber, password, "04/12/2020","15/02/2020");
                        FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.VISIBLE);
                                }else{
                                    Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.VISIBLE);

                                }
                            }
                        });
                    }else {
                        Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            });

    }

}