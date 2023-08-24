package com.grace.thetraveldiary.viemodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.grace.thetraveldiary.models.DiaryEntry;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    MutableLiveData<ArrayList<DiaryEntry>> entriesListMutableLiveData;

    DiaryEntry newDiaryEntry;

    Thread thread;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public HomeViewModel() {
        entriesListMutableLiveData = new MutableLiveData<>();
        newDiaryEntry = new DiaryEntry();
    }

    public MutableLiveData<ArrayList<DiaryEntry>> getMealsListMutableLiveData() {
        return entriesListMutableLiveData;
    }

    public void getEntries() {
        Runnable getAllEntries = this::queryEntry;
        queryEntry();
//        startThread(queryEntry);
        ArrayList<DiaryEntry> entries = new ArrayList<>();
    }

    private void queryEntry() {

        db.collection("entries")
                .whereEqualTo("user", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<DiaryEntry> entries = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("HomeViewModel", document.getId() + " => " + document.getData());

//                            entries.add(new DiaryEntry(document.getId(),
//                                    document.getData().get("title"),
//                                    document.getData().get("date"),
//                                    document.getData().get("location"),
//                                    document.getData().get("notes")
//                                    ));

                            entries.add(document.toObject(DiaryEntry.class));
                        }
                        entriesListMutableLiveData.setValue(entries);

                    } else {
                        Log.w("HomeViewModel", "Error getting documents.", task.getException());
                    }
                });
    }


    private void startThread(Runnable runnable){
        if (thread != null){
            thread.interrupt();
        }
        thread = new Thread(runnable);
        thread.start();
    }
}
