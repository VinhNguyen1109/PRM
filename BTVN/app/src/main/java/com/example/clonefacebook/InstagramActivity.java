package com.example.clonefacebook;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InstagramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram); // XML mới cho màn này

        View root = findViewById(R.id.instagram);

        int[] colors = new int[]{
                Color.parseColor("#405de6"),
                Color.parseColor("#5851db"),
                Color.parseColor("#833ab4"),
                Color.parseColor("#c13584"),
                Color.parseColor("#e1306c"),
                Color.parseColor("#fd1d1d"),
                Color.parseColor("#f56040"),
                Color.parseColor("#f77737"),
                Color.parseColor("#fcaf45"),
                Color.parseColor("#ffdc80")
        };

        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors
        );

        root.setBackground(gradient);
    }
}