package com.example.peakform.ui.entry;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.peakform.R;
import com.example.peakform.data.entity.Entry;
import com.example.peakform.viewmodel.EntryViewModel;

public class AddEntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        EntryViewModel viewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        Button btnSave = findViewById(R.id.button_save);
        EditText etName = findViewById(R.id.edit_activity_name);
        EditText etGoal = findViewById(R.id.edit_goal_minutes);
        EditText etActual = findViewById(R.id.edit_actual_minutes);
        SeekBar skQual = findViewById(R.id.seek_quality);
        SeekBar skWeight = findViewById(R.id.seek_weight);
        Spinner spDomain = findViewById(R.id.spinner_domain);

        String[] domains = {"Work", "Study", "Exercise", "Health", "Social"};
        if (spDomain != null) {
            spDomain.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, domains));
        }

        if (btnSave != null) {
            btnSave.setOnClickListener(v -> {
                try {
                    String name = etName.getText().toString();
                    float goal = Float.parseFloat(etGoal.getText().toString());
                    float actual = Float.parseFloat(etActual.getText().toString());
                    float weight = (skWeight != null) ? skWeight.getProgress() / 100f : 0.5f;
                    float quality = (skQual != null) ? (float)skQual.getProgress() : 5f;

                    Entry entry = new Entry(name, spDomain.getSelectedItem().toString(),
                            actual, goal, quality, weight, System.currentTimeMillis());

                    viewModel.insert(entry);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(this, "Please enter valid numbers for Goal and Actual", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}