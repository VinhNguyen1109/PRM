package com.example.clonefacebook;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    TextView nameDisplay;
    Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameDisplay = findViewById(R.id.textName);
        closeBtn = findViewById(R.id.buttonClose);

        // Nhận name từ LoginActivity
        String name = getIntent().getStringExtra("USERNAME");
        nameDisplay.setText("Welcome, " + name + "!");

        closeBtn.setOnClickListener(v -> finishAffinity()); // Close the app
    }
}
