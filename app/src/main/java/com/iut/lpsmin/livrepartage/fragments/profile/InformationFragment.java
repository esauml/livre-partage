package com.iut.lpsmin.livrepartage.fragments.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iut.lpsmin.livrepartage.R;
import com.iut.lpsmin.livrepartage.model.Utilisateur;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    public static final String TAG_CLASS = InformationFragment.class.getSimpleName();

    // for navigation
    private Button mBooksBtn;
    private Button mWishListBtn;

    // form inputs
    private TextInputLayout nFullNameTextInputLayout;
    private TextInputLayout nPhoneNumberTextInputLayout;
    private TextInputLayout nEmailAdressTextInputLayout;
    private Button nChangeValuesButton;
    // manages button click-> set elements: change values
    private boolean nBound;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    Utilisateur user;

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

        // firebase init
        firebaseDatabase = FirebaseDatabase.getInstance();

        Log.d(TAG_CLASS, "Fragment Information created");

        // init navigation components
        initNavigation(view);

        // initializes and parses all components from xml
        initComponents(view);

        //add data to componentes
        addDataToComponents();

        // listeners on buttons
        addListenersToButtons();
    }

    private void initNavigation(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String currentUser = mAuth.getCurrentUser().getUid();

        view.findViewById(R.id.mBooksBtn).setOnClickListener(viewEvent -> {
            NavHostFragment.findNavController(InformationFragment.this)
                    .navigate(R.id.action_informationFragment_to_livresFragment);

        });

        view.findViewById(R.id.mWishListBtn).setOnClickListener(viewEvent -> {
            NavHostFragment.findNavController(InformationFragment.this)
                    .navigate(R.id.action_informationFragment_to_wishListFragment);
        });
    }

    private void addDataToComponents() {

        String uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = firebaseDatabase.getReference("Users").child(uuid);
        // Query dataQuery = ref.equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Utilisateur user = dataSnapshot.getValue(Utilisateur.class);
                    setDataToComponents(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG_CLASS, "Problem retrieving from database user");
            }
        });
    }

    private void setDataToComponents(Utilisateur user) {
        this.user = user;
        nFullNameTextInputLayout.getEditText().setText(user.getFullName());
        nEmailAdressTextInputLayout.getEditText().setText(user.getEmail());
        nPhoneNumberTextInputLayout.getEditText().setText(user.getPhoneNumber());
    }

    public void initComponents(View view) {
        nFullNameTextInputLayout = view.findViewById(R.id.profile_full_name);
        nEmailAdressTextInputLayout = view.findViewById(R.id.profile_email);
        nPhoneNumberTextInputLayout = view.findViewById(R.id.profile_phone_number);
        nChangeValuesButton = view.findViewById(R.id.profile_button_change_values);

        nBound = false;
    }

    private void addListenersToButtons() {
        nChangeValuesButton.setOnClickListener((View view) -> {
            nBound = !nBound;
            nFullNameTextInputLayout.setEnabled(nBound);
            nEmailAdressTextInputLayout.setEnabled(nBound);
            nPhoneNumberTextInputLayout.setEnabled(nBound);

            if (nBound)
                nChangeValuesButton.setText(R.string.profile_change_values_save);
            else {
                nChangeValuesButton.setText(R.string.profile_change_values_change);
                // saves data to firebase
                savaDataToFireBase();
            }
        });
    }

    private void savaDataToFireBase() {
        String fullName;
        String email;
        String phoneNumber;

        fullName = nFullNameTextInputLayout.getEditText().getText().toString();
        email = nEmailAdressTextInputLayout.getEditText().getText().toString();
        phoneNumber = nPhoneNumberTextInputLayout.getEditText().getText().toString();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        String uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase.getReference("Users").child(uuid).setValue(user);
    }
}