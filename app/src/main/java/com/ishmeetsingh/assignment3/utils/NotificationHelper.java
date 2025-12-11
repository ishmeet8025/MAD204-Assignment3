package com.ishmeetsingh.assignment3.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.ishmeetsingh.assignment3.MainActivity;
import com.ishmeetsingh.assignment3.R;

public class NotificationHelper {
    private static final String CHANNEL_ID = "notes_channel";
    public static void showReminder(Context ctx, String title, String body) {
        NotificationManager nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(CHANNEL_ID, "Notes reminders", NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(ch);
        }
        Intent intent = new Intent(ctx, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Notification notif = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        nm.notify(1001, notif);
    }
}
