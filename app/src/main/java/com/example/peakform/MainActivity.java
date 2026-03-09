package com.example.peakform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.peakform.ui.entry.AddEntryActivity;
import com.example.peakform.viewmodel.EntryViewModel;

public class MainActivity extends AppCompatActivity {

    private EntryViewModel viewModel;
    private TextView entryCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entryCount = findViewById(R.id.entryCount);
        Button addEntryButton = findViewById(R.id.addEntryButton);

        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        viewModel.getAllEntries().observe(this, entries -> {
            entryCount.setText("Entries: " + entries.size());
        });

        addEntryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
            startActivity(intent);
        });
    }
}