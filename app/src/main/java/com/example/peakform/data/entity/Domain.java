package com.example.peakform.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Domain {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    // Study, Sleep, Activity
    public String name;

    public boolean enabled;
}
