package com.example.peakform.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries")
public class Entry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String activityName;
    public String domainName;
    public float actualMinutes;
    public float goalMinutes;
    public float quality;
    public float userWeight;
    public long timestamp;

    public Entry(String activityName, String domainName, float actualMinutes,
                 float goalMinutes, float quality, float userWeight, long timestamp) {
        this.activityName = activityName;
        this.domainName = domainName;
        this.actualMinutes = actualMinutes;
        this.goalMinutes = goalMinutes;
        this.quality = quality;
        this.userWeight = userWeight;
        this.timestamp = timestamp;
    }
}