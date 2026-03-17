package com.example.peakform.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.peakform.data.dao.EntryDao;
import com.example.peakform.data.entity.Entry;
import com.example.peakform.data.database.PeakFormDatabase; // Verify your DB class name here
import java.util.List;

public class EntryRepository {
    private EntryDao entryDao;
    private LiveData<List<Entry>> allEntries;

    public EntryRepository(Application application) {
        PeakFormDatabase pfd = PeakFormDatabase.getDatabase(application);
        entryDao = pfd.entryDao();
        allEntries = entryDao.getAllEntries();
    }

    public LiveData<List<Entry>> getAllEntries() { return allEntries; }

    public void insert(Entry entry) {
        PeakFormDatabase.databaseWriteExecutor.execute(() -> entryDao.insert(entry));
    }

    public void deleteAll() {
        PeakFormDatabase.databaseWriteExecutor.execute(() -> {
            entryDao.deleteAll();
        });
    }
}
