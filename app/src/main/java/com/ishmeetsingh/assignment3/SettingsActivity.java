package com.ishmeetsingh.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.ishmeetsingh.assignment3.utils.PrefManager;

public class SettingsActivity extends AppCompatActivity {

    private EditText etName;
    private Switch swTheme;
    private Button btnSave;
    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        pref = new PrefManager(this);
        etName = findViewById(R.id.etName);
        swTheme = findViewById(R.id.swTheme);
        btnSave = findViewById(R.id.btnSaveSettings);

        etName.setText(pref.getUsername());
        swTheme.setChecked(pref.isDarkTheme());

        btnSave.setOnClickListener(v -> {
            pref.setUsername(etName.getText().toString().trim());
            pref.setDarkTheme(swTheme.isChecked());
            finish();
        });
    }
}
