package com.ishmeetsingh.assignment3.utils;
// PR 3 Note: Added explanation for SharedPreferences usage for username and theme.
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "assignment3_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DARK_THEME = "dark_theme";

    private SharedPreferences prefs;

    public PrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        prefs.edit().putString(KEY_USERNAME, username).apply();
    }

    public String getUsername() {
        return prefs.getString(KEY_USERNAME, "");
    }

    public void setDarkTheme(boolean dark) {
        prefs.edit().putBoolean(KEY_DARK_THEME, dark).apply();
    }

    public boolean isDarkTheme() {
        return prefs.getBoolean(KEY_DARK_THEME, false);
    }
}
