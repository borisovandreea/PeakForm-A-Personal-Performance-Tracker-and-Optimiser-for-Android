package com.example.peakform.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ScoreSnapshot {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public long timestamp;

    public float score;

    @NonNull
    public String period;
}
