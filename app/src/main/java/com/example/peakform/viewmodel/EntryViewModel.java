package com.example.peakform.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.peakform.data.entity.Entry;
import com.example.peakform.data.repository.EntryRepository;
import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    private EntryRepository repository;
    private LiveData<List<Entry>> allEntries;

    public EntryViewModel(Application application) {
        super(application);
        repository = new EntryRepository(application);
        allEntries = repository.getAllEntries();
    }

    public LiveData<List<Entry>> getAllEntries() { return allEntries; }

    public void insert(Entry entry) { repository.insert(entry); }

    public void deleteAll() {
        repository.deleteAll();
    }
}
