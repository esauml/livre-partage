package com.iut.lpsmin.livrepartage.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    private DatabaseReference mDatabaseRef;
    public  Firebase(String ref){
        mDatabaseRef= FirebaseDatabase.getInstance().getReference(ref);
    }
    public void enRegist(Object o){
        mDatabaseRef.push().setValue(o);
    }
    @Override
    public String toString() {
        return "Firebase{" +
                "mDatabaseRef=" + mDatabaseRef +
                '}';
    }
}
