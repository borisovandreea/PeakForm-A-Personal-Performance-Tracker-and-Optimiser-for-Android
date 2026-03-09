package com.example.peakform.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "entries")
public class Entry {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int domainId;

    public float value;

    public long timestamp;

    public boolean qualityFlag;
}
