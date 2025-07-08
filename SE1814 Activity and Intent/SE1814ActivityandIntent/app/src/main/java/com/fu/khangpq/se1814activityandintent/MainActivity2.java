package com.fu.khangpq.se1814activityandintent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private EditText edtShowData;
    private Button btnSave;

    private void bindingView() {
        edtShowData = findViewById(R.id.edtShowData);
        btnSave = findViewById(R.id.btnSave);
    }

    private void bindingAction() {
        btnSave.setOnClickListener(this::onBtnSaveClick);
    }

    private void onBtnSaveClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("data", edtShowData.getText().toString());
//        startActivity(i);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
        onReceiveIntent();
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        int age = i.getIntExtra("age", 0);
        edtShowData.setText(name + " - " + age);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("KhangPQ.Debug", "onDestroy act 2");
    }
}