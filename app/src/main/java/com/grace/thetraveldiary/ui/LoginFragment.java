package com.grace.thetraveldiary.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grace.thetraveldiary.R;
import com.grace.thetraveldiary.databinding.FragmentLoginBinding;
import com.grace.thetraveldiary.util.Validation;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    FragmentLoginBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        initViews();

        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            newPage(new HomeScreenFragment());
        }
    }

    private void initViews() {

        binding.login.setOnClickListener(v -> validate());

        binding.noAccount.setOnClickListener(v -> newPage(new SignUpFragment()));

    }

    private void validate() {
        if (new Validation().isEmailAddress(binding.emailEditText, true) &&
                new Validation().isPassword(binding.passwordEditText, true)){
            logInUser();
        }
    }

    private void logInUser() {

        mAuth.signInWithEmailAndPassword(binding.emailEditText.getText().toString(),
                binding.passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    newPage(new HomeScreenFragment());
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(getClass().getSimpleName(), "signInWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void newPage(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                .addToBackStack("");
        fragmentTransaction.commit();
    }
}