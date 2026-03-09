package com.example.peakform.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FeedbackEvent {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public long timestamp;

    @NonNull
    public String message;

    public float relatedScore;
}
