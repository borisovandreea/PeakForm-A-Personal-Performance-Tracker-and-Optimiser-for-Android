package com.example.peakform.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.peakform.data.dao.EntryDao;
import com.example.peakform.data.entity.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Entry.class}, version = 4, exportSchema = false) // Upgraded version
public abstract class PeakFormDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();
    private static volatile PeakFormDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static PeakFormDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PeakFormDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PeakFormDatabase.class, "peakform_db")
                            .fallbackToDestructiveMigration() // THIS PREVENTS THE CRASH
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}