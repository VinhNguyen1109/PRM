package com.example.playmusic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        // ✅ Xin quyền gửi thông báo nếu đang ở Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_NOTIFICATION_PERMISSION);
            }
        }

        // 🎵 Bắt đầu dịch vụ phát nhạc
        btnStart.setOnClickListener(v -> {
            startService(new Intent(this, MusicService.class));
        });

        // ⏹ Dừng dịch vụ phát nhạc
        btnStop.setOnClickListener(v -> {
            stopService(new Intent(this, MusicService.class));
        });
    }

    // ✅ Xử lý kết quả xin quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã cấp quyền gửi thông báo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ứng dụng cần quyền gửi thông báo để hiển thị trạng thái nhạc", Toast.LENGTH_LONG).show();
            }
        }
    }
}
