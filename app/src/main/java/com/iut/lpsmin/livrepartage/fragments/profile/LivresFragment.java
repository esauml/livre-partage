package com.iut.lpsmin.livrepartage.fragments.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.iut.lpsmin.livrepartage.R;
import com.iut.lpsmin.livrepartage.model.Livre;
import com.iut.lpsmin.livrepartage.recyclerview.profile.LivreAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivresFragment extends Fragment {
    public static final String TAG_CLASS = LivresFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    LivreAdapter mAdapter;

    DatabaseReference mReference;
    FirebaseDatabase mFirebaseDatabase;


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

        View view = inflater.inflate(R.layout.fragment_livres, container, false);
        mRecyclerView = view.findViewById(R.id.RecyclerBooks);

        // setup RecyclerView
        setUpRecyclerView();

        return view;
    }

    private void setUpRecyclerView() {
        String uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        Query query =  mFirebaseDatabase.getReference("livres");  // .orderByChild("user").equalTo(uuid);

        FirebaseRecyclerOptions<Livre> options
                = new FirebaseRecyclerOptions.Builder<Livre>()
                .setQuery(query, Livre.class)
                .build();

        mAdapter = new LivreAdapter(options);
//        LinearLayoutManager linearLayoutManager =
//                new LinearLayoutManager(getContext(),
//                        LinearLayoutManager.VERTICAL,
//                        false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG_CLASS, "Livres Fragment Created");
        // initializes and parses all components from xml
        // initComponents(view)
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }

    }
}