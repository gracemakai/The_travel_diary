package com.grace.thetraveldiary.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grace.thetraveldiary.R;
import com.grace.thetraveldiary.adapters.DiaryListAdapter;
import com.grace.thetraveldiary.databinding.FragmentHomeScreenBinding;
import com.grace.thetraveldiary.models.DiaryEntry;
import com.grace.thetraveldiary.viemodels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenFragment extends Fragment {
    FragmentHomeScreenBinding binding;
    DiaryListAdapter adapter;
    HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        ((MainActivity) requireActivity()).getSupportActionBar().setTitle("Diary entries");

        homeViewModel.getMealsListMutableLiveData()
                .observe(this, mealsObserver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);

        homeViewModel.getEntries();

        initView();

        return binding.getRoot();
    }

    private void initView() {
        // Create the RecyclerView and set its adapter
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<DiaryEntry> entries = new ArrayList<>(); // Load entries here if needed
        adapter = new DiaryListAdapter(entries);
        recyclerView.setAdapter(adapter);

        binding.fabButton.setOnClickListener(v -> {
            newPage(new CreateEntryFragment());
        });

        // Set click listener for the adapter
        adapter.setOnItemClickListener(entry -> {
            Log.i(getClass().getSimpleName(), "clicked " + entry.getTitle());

            newPage(new DiaryEntryDetailsFragment(entry));
        });
    }

    Observer<ArrayList<DiaryEntry>> mealsObserver = new Observer<ArrayList<DiaryEntry>>() {
        @Override
        public void onChanged(ArrayList<DiaryEntry> entries) {
            Log.i(getClass().getSimpleName(), "onChanged: Observer " + entries.toString());
            adapter.setNewDiaryEntries(entries);
        }
    };

    private void newPage(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                .addToBackStack("");
        fragmentTransaction.commit();
    }
}
