package com.example.demo_notification;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnNoti;
    private EditText edtData;

    private Button btnLoad;
    private Button btnSave;

    private EditText showData;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int lastId = 0;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;
    private static final String CHANNEL_ID = "SE1814";

    private void bindingView() {
        btnNoti = findViewById(R.id.btnNoti);
        edtData = findViewById(R.id.edtData);
        btnLoad = findViewById(R.id.btnLoad);
        btnSave = findViewById(R.id.btnSave);
        showData = findViewById(R.id.edtShowData);
        pref = getSharedPreferences("data", MODE_PRIVATE);
        editor = pref.edit();
    }

    private void bindingAction() {
        btnNoti.setOnClickListener(this::onBtnNotiClick);
        btnLoad.setOnClickListener(this::onBtnLoadClick);
        btnSave.setOnClickListener(this::onBtnSaveClick);

    }

    private void onBtnSaveClick(View view) {
        String data = edtData.getText().toString();
        String[] d =data.split("//|");

        String key = d[0];
        String value = d[1];
        editor.putString(key, value);
        if(editor.commit()){
            Toast.makeText(this, "save ok", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "save failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void onBtnLoadClick(View view) {
        showData.setText("");
        pref.getAll().forEach((key, value) -> {
            String data = key + "|" + value;
            showData.append(data);
        });
    }

    private void onBtnNotiClick(View view) {
        String data = edtData.getText().toString();
        sendNotification(data);
    }

    private void sendNotification(String data) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("data", data);
        PendingIntent pi = PendingIntent.getActivity(this,
                1,
                i,
                PendingIntent.FLAG_IMMUTABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Hello",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Hello SE1814")
                .setContentText(data)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data))
                .setSmallIcon(R.drawable.ic_stat_name)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(lastId++, notification);
        Toast.makeText(this, "Notification sent", Toast.LENGTH_SHORT).show();
    }

    private void requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_NOTIFICATION_PERMISSION);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        onReceiveIntent();
        requestNotificationPermissionIfNeeded();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        if(i.hasExtra("data")){
            String data = i.getStringExtra("data");
            edtData.setText(data);
        }

    }
}
