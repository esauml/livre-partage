package com.iut.lpsmin.livrepartage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }
}