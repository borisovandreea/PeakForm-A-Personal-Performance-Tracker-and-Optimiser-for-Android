package com.example.peakform.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.peakform.data.dao.EntryDao;
import com.example.peakform.data.database.PeakFormDatabase;
import com.example.peakform.data.entity.Entry;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EntryRepository {
    private final EntryDao entryDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public EntryRepository(Application application) {
        PeakFormDatabase db = PeakFormDatabase.getInstance(application);
        entryDao = db.entryDao();
    }

    public void insert(Entry entry) {
        executor.execute(() -> entryDao.insert(entry));
    }

    public LiveData<List<Entry>> getAllEntries() {
        return entryDao.getAllEntries();
    }
}
