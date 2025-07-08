package com.example.dowloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private EditText edtUrl;
    private Button btnDownload;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUrl = findViewById(R.id.edtUrl);
        btnDownload = findViewById(R.id.btnDownload);
        imageView = findViewById(R.id.imageView);

        btnDownload.setOnClickListener(v -> {
            String url = edtUrl.getText().toString().trim();
            if (TextUtils.isEmpty(url)) {
                Toast.makeText(this, "Vui lòng nhập URL", Toast.LENGTH_SHORT).show();
                return;
            }
            downloadImage(url);
        });
    }

    private void downloadImage(String imageUrl) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                URL url = new URL(imageUrl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

                handler.post(() -> imageView.setImageBitmap(bitmap));

            } catch (Exception e) {
                handler.post(() -> Toast.makeText(MainActivity.this, "Lỗi tải ảnh", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        });
    }
}
