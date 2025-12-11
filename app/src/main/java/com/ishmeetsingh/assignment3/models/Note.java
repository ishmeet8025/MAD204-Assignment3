package com.ishmeetsingh.assignment3.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.Nullable;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;
    public String content;
    @Nullable
    public String mediaUri;
    public boolean isFavorite;

    public Note() {}

    public Note(String title, String content, String mediaUri, boolean isFavorite) {
        this.title = title;
        this.content = content;
        this.mediaUri = mediaUri;
        this.isFavorite = isFavorite;
    }
}
