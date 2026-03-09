package com.example.peakform.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.peakform.data.entity.Entry;

import java.util.List;
@Dao
public interface EntryDao {
    @Insert
    void insert(Entry entry);

    @Query("SELECT * FROM entries ORDER BY timestamp DESC")
    List<Entry> getAll();

    @Query("SELECT * FROM entries ORDER BY timestamp DESC")
    LiveData<List<Entry>> getAllEntries();
}
