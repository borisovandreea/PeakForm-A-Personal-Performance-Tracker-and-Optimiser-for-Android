package com.example.peakform.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.peakform.data.dao.EntryDao;
import com.example.peakform.data.entity.Domain;
import com.example.peakform.data.entity.Entry;
import com.example.peakform.data.entity.FeedbackEvent;
import com.example.peakform.data.entity.Goal;
import com.example.peakform.data.entity.ScoreSnapshot;
import com.example.peakform.data.entity.WeightProfile;

@Database(
        entities = {
                Entry.class,
                Domain.class,
                Goal.class,
                WeightProfile.class,
                ScoreSnapshot.class,
                FeedbackEvent.class
        },
        version = 1
)
public abstract class PeakFormDatabase extends RoomDatabase {
    private static PeakFormDatabase INSTANCE;

    public abstract EntryDao entryDao();

    public static synchronized PeakFormDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PeakFormDatabase.class,
                    "peakform_db"
            ).build();
        }
        return INSTANCE;
    }
}
