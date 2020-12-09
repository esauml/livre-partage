package com.iut.lpsmin.livrepartage.fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iut.lpsmin.livrepartage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    public static final String TAG_CLASS = InformationFragment.class.getSimpleName();

    public InformationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // life expectancy codes
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG_CLASS, "Fragment Information created");
        // initializes and parses all components from xml
        // initComponents();
    }

    public void initComponents() {

    }
}