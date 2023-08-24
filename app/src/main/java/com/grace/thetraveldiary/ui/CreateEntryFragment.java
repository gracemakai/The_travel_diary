package com.grace.thetraveldiary.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.grace.thetraveldiary.R;
import com.grace.thetraveldiary.databinding.FragmentCreateEntryBinding;
import com.grace.thetraveldiary.models.DiaryEntry;
import com.grace.thetraveldiary.services.EntriesRepo;
import com.grace.thetraveldiary.util.Validation;

public class CreateEntryFragment extends Fragment {

    FragmentCreateEntryBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) requireActivity()).getSupportActionBar().setTitle("Create entries");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_entry, container, false);
        binding.setLifecycleOwner(this);

        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.saveEntry.setOnClickListener(v -> {

            if(new Validation().hasText(binding.titleEditText) &&
                    new Validation().hasText(binding.dateEditText) &&
                    new Validation().hasText(binding.locationEditText)){

                new EntriesRepo().createEntry(new DiaryEntry(
                        FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        binding.titleEditText.getText().toString(),
                        binding.dateEditText.getText().toString(),
                        binding.locationEditText.getText().toString(),
                        binding.notesEditText.getText().toString()
                ));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, new HomeScreenFragment())
                        .addToBackStack("");
                fragmentTransaction.commit();
            }


        });
    }
}
