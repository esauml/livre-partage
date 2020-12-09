package com.iut.lpsmin.livrepartage.fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iut.lpsmin.livrepartage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivresFragment extends Fragment {
    public static final String TAG_CLASS = LivresFragment.class.getSimpleName();

    private Button mInformationBtn;
    private Button mWishListBtn;

    public LivresFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // life expectancy code
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_livres, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initializes and parses all components from xml
        // initComponents(view);
        Log.d(TAG_CLASS, "Fragment Livre created");

        view.findViewById(R.id.mInformationBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LivresFragment.this)
                        .navigate(R.id.action_livresFragment_to_informationFragment);
            }
        });
    }

    public void initComponents(View view) {

    }
}