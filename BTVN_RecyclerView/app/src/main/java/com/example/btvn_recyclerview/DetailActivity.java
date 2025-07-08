package com.example.btvn_recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgCategory;
    TextView txtCategory;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgCategory = findViewById(R.id.imgCategory);
        txtCategory = findViewById(R.id.txtCategory);
        btnBack = findViewById(R.id.btnBack);

        int imageRes = getIntent().getIntExtra("imageResId", 0);
        String title = getIntent().getStringExtra("name");

        imgCategory.setImageResource(imageRes);
        txtCategory.setText(title);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại MainActivity
            }
        });
    }
}
