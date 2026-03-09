package com.example.peakform.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Domain.class,
                parentColumns = "id",
                childColumns = "domainId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("domainId")}
)
public class WeightProfile {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int domainId;

    public float weight;
}
