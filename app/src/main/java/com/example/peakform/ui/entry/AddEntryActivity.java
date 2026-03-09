package com.example.peakform.ui.entry;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.peakform.R;
import com.example.peakform.data.entity.Entry;
import com.example.peakform.viewmodel.EntryViewModel;

public class AddEntryActivity extends AppCompatActivity {
    private EntryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        EditText valueInput = findViewById(R.id.valueInput);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {

            String valueText = valueInput.getText().toString().trim();

            if (valueText.isEmpty()) {
                valueInput.setError("Enter a value");
                return;
            }

            Entry entry = new Entry();
            entry.domainId = 1; // temporary
            entry.value = Float.parseFloat(valueText);
            entry.timestamp = System.currentTimeMillis();
            entry.qualityFlag = true;

            viewModel.addEntry(entry);
            finish();
        });
    }
}
