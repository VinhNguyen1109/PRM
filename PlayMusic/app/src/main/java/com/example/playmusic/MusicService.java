package com.example.playmusic;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.media.app.NotificationCompat.MediaStyle;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private static final String CHANNEL_ID = "music_channel_id";
    private static final String CHANNEL_NAME = "Music Playback Channel";

    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);

        Log.d(TAG, "MediaPlayer initialized");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand called");

        if (intent != null && "STOP".equals(intent.getAction())) {
            Log.d(TAG, "Received STOP action → stopping service");
            stopSelf();
            return START_NOT_STICKY;
        }

        createNotificationChannelIfNeeded();

        Intent stopIntent = new Intent(this, MusicService.class);
        stopIntent.setAction("STOP");

        PendingIntent stopPendingIntent = PendingIntent.getService(
                this,
                0,
                stopIntent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Playing Music")
                .setContentText("Tap Stop to stop the music")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .addAction(android.R.drawable.ic_media_pause, "Stop", stopPendingIntent)
                .setStyle(new MediaStyle().setShowActionsInCompactView(0))
                .setOngoing(true)
                .build();

        Log.d(TAG, "Calling startForeground()");
        startForeground(1, notification);

        if (player != null && !player.isPlaying()) {
            Log.d(TAG, "Starting music playback");
            player.start();
        }

        return START_STICKY;
    }

    private void createNotificationChannelIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel existing = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE))
                    .getNotificationChannel(CHANNEL_ID);
            if (existing == null) {
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_LOW
                );
                NotificationManager manager = getSystemService(NotificationManager.class);
                if (manager != null) {
                    manager.createNotificationChannel(channel);
                    Log.d(TAG, "NotificationChannel created");
                }
            } else {
                Log.d(TAG, "NotificationChannel already exists");
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy called → releasing MediaPlayer");
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        stopForeground(true);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
