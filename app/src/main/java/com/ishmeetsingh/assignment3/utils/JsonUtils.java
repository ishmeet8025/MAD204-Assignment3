package com.ishmeetsingh.assignment3.utils;

import com.ishmeetsingh.assignment3.models.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new Gson();
    public static String notesToJson(List<Note> notes) {
        return gson.toJson(notes);
    }
    public static List<Note> jsonToNotes(String json) {
        Type t = new TypeToken<List<Note>>(){}.getType();
        return gson.fromJson(json, t);
    }
}
