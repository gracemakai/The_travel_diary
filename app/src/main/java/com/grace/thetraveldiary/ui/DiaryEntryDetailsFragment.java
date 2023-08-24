package com.grace.thetraveldiary.ui;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;

import com.grace.thetraveldiary.R;
import com.grace.thetraveldiary.databinding.FragmentDiaryEntryDetailsBinding;
import com.grace.thetraveldiary.models.DiaryEntry;

public class DiaryEntryDetailsFragment extends Fragment {

    DiaryEntry diaryEntry;

    public DiaryEntryDetailsFragment(DiaryEntry diaryEntry) {
        this.diaryEntry = diaryEntry;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDiaryEntryDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_entry_details, container, false);

        binding.titleTextView.setText(diaryEntry.getTitle());
        binding.notesTextView.setText(diaryEntry.getNotes());
        binding.locationTextView.setText(diaryEntry.getLocation());
        binding.dateTextView.setText(diaryEntry.getDate());

        return binding.getRoot();
    }
}
