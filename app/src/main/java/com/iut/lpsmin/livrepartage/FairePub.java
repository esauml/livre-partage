package com.iut.lpsmin.livrepartage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iut.lpsmin.livrepartage.model.Firebase;
import com.iut.lpsmin.livrepartage.model.Genre;
import com.iut.lpsmin.livrepartage.model.Livre;
import com.iut.lpsmin.livrepartage.model.Publication;

public class FairePub extends AppCompatActivity {
    private Livre l;
    private Publication p;
    private Genre g;
    private TextInputLayout textTitre;
    private TextInputLayout textAuteur;
    private TextInputLayout textEdition;
    private TextInputLayout textVille;
    private Firebase mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faire_pub);
        mDatabaseRef=new Firebase("livres") ;
        textTitre=findViewById(R.id.textTitre);
        textAuteur=findViewById((R.id.textAuteur));
        textEdition=findViewById(R.id.textEdition);
        textVille=findViewById(R.id.textVille);
    }

    public void Enregistrer(View view) {
        l=new Livre(textTitre.getEditText().getText().toString(),
                textAuteur.getEditText().getText().toString(),
                textEdition.getEditText().getText().toString(),
                new Genre(
                        textVille.getEditText().getText().toString()));
        mDatabaseRef.enRegist(l);
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}