package com.ishmeetsingh.assignment3.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ishmeetsingh.assignment3.models.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    long insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();

    @Query("SELECT * FROM notes WHERE isFavorite = 1 ORDER BY id DESC")
    List<Note> getFavoriteNotes();

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    Note getNoteById(long id);
}
