package com.grace.thetraveldiary.services;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grace.thetraveldiary.models.DiaryEntry;

public class EntriesRepo {

    public void createEntry(DiaryEntry entry) {

        // Add a new document with a generated ID
        FirebaseFirestore.getInstance().collection("entries")
                .add(entry.toJSON())
                .addOnSuccessListener(documentReference -> Log.d("HomeViewModel", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("HomeViewModel", "Error adding document", e));
    }

}
