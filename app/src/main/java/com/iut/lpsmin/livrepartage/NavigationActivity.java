package com.iut.lpsmin.livrepartage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class NavigationActivity extends AppCompatActivity {

    private Button mBtnPublish;
    private Button mBtnLogout;
    private Button mBtnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Intent intent = getIntent();

//        mBtnLogout = findViewById(R.id.btn_logout);
//        mBtnProfile = findViewById(R.id.btn_profile);
//        mBtnPublish = findViewById(R.id.btn_publish_book);


       // findViewById(R.id.btn_publish_book).setEnabled(false);
        findViewById(R.id.btn_logout).setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut(); //so i can logout
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        });

        findViewById(R.id.btn_profile).setOnClickListener(view -> {
            startActivity(new Intent(NavigationActivity.this, ProfileActivity.class));
        });


        findViewById(R.id.btn_publish_book).setOnClickListener(view -> {
            startActivity(new Intent(NavigationActivity.this, FairePub.class));
        });
    }


}