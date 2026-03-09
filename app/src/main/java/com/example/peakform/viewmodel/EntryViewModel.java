package com.example.peakform.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.peakform.data.entity.Entry;
import com.example.peakform.data.repository.EntryRepository;

import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    private final EntryRepository repository;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        repository = new EntryRepository(application);
    }

    public void addEntry(Entry entry) {
        repository.insert(entry);
    }

    public LiveData<List<Entry>> getAllEntries() {
        return repository.getAllEntries();
    }
}
