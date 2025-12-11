package com.ishmeetsingh.assignment3.services;
// Service enhancement: Added reasoning behind delayed reminder for demonstrating background execution.
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.ishmeetsingh.assignment3.utils.NotificationHelper;

public class ReminderService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            NotificationHelper.showReminder(ReminderService.this, "Don't forget your notes!", "Open the app to review your notes.");
            stopSelf();
        }, 7000);
        return START_NOT_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }
}
