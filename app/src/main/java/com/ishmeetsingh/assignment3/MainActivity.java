package com.ishmeetsingh.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ishmeetsingh.assignment3.adapters.NoteAdapter;
import com.ishmeetsingh.assignment3.db.AppDatabase;
import com.ishmeetsingh.assignment3.models.Note;
import com.ishmeetsingh.assignment3.services.ReminderService;
import com.ishmeetsingh.assignment3.utils.FileUtils;
import com.ishmeetsingh.assignment3.utils.JsonUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteActionListener {

    private AppDatabase db;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        RecyclerView rv = findViewById(R.id.recyclerNotes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(this, new ArrayList<>(), this);
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, EditNoteActivity.class)));

        findViewById(R.id.btnReminder).setOnClickListener(v -> {
            startService(new Intent(this, ReminderService.class));
            Toast.makeText(this, "Reminder started", Toast.LENGTH_SHORT).show();
        });

        loadAll();
    }

    private void loadAll() {
        List<Note> notes = db.noteDao().getAllNotes();
        adapter.setNotes(notes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAll();
    }

    @Override
    public void onToggleFavorite(Note note) {
        note.isFavorite = !note.isFavorite;
        db.noteDao().update(note);
        loadAll();
    }

    @Override
    public void onDelete(Note note) {
        db.noteDao().delete(note);
        loadAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_export) {
            List<Note> allNotes = db.noteDao().getAllNotes();
            String json = JsonUtils.notesToJson(allNotes);
            String path = FileUtils.saveNotesToExternal(this, "notes_export.json", json);
            Toast.makeText(this, "Exported: " + path, Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.menu_import) {
            String content = FileUtils.readNotesFromExternal(this, "notes_export.json");
            if (content == null || content.trim().isEmpty()) {
                Toast.makeText(this, "No export file", Toast.LENGTH_SHORT).show();
            } else {
                List<Note> imported = JsonUtils.jsonToNotes(content);
                for (Note n : imported) {
                    n.id = 0;
                    db.noteDao().insert(n);
                }
                Toast.makeText(this, "Imported notes", Toast.LENGTH_SHORT).show();
                loadAll();
            }
            return true;
        } else if (item.getItemId() == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
