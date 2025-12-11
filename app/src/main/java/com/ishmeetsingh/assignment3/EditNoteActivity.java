package com.ishmeetsingh.assignment3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ishmeetsingh.assignment3.db.AppDatabase;
import com.ishmeetsingh.assignment3.models.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private ImageView ivPreview;
    private Button btnPick, btnSave;
    private AppDatabase db;
    private String mediaUri = null;
    private long noteId = 0;

    private ActivityResultLauncher<String> pickMedia = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    mediaUri = uri.toString();
                    ivPreview.setImageURI(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        db = AppDatabase.getInstance(this);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        ivPreview = findViewById(R.id.imgPreview);
        btnPick = findViewById(R.id.btnPick);
        btnSave = findViewById(R.id.btnSave);

        noteId = getIntent().getLongExtra("noteId", 0);
        if (noteId != 0) loadNote();

        btnPick.setOnClickListener(v -> pickMedia.launch("*/*"));
        btnSave.setOnClickListener(v -> saveNote());
    }

    private void loadNote() {
        Note n = db.noteDao().getNoteById(noteId);
        if (n != null) {
            etTitle.setText(n.title);
            etContent.setText(n.content);
            if (n.mediaUri != null) {
                mediaUri = n.mediaUri;
                ivPreview.setImageURI(Uri.parse(mediaUri));
            }
        }
    }

    private void saveNote() {
        String t = etTitle.getText().toString().trim();
        String c = etContent.getText().toString().trim();
        if (t.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (noteId == 0) {
            Note n = new Note(t, c, mediaUri, false);
            db.noteDao().insert(n);
        } else {
            Note n = db.noteDao().getNoteById(noteId);
            if (n != null) {
                n.title = t;
                n.content = c;
                n.mediaUri = mediaUri;
                db.noteDao().update(n);
            }
        }
        finish();
    }
}
