package com.example.sqllite;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtWord;
    private EditText edtDefination;

    private EditText edtShowData;

    private Button btnAdd;

    private Dbcontext db;

    private void bindingAction() {
        btnAdd.setOnClickListener(this::onBtnAddClick);
    }

    private void onBtnAddClick(View view) {
        String word = edtWord.getText().toString();
        String def = edtDefination.getText().toString();
        Long id = db.insert2(word, def);
        Toast.makeText(this, "Inser with id + " + id, Toast.LENGTH_SHORT).show();
        edtShowData.append(id + "-" + word + "-" + def );
    }

    private void bindingView() {
        edtWord = findViewById(R.id.edtWord);
        edtDefination = findViewById(R.id.edtDefination);
        edtShowData = findViewById(R.id.edtShowData);
        btnAdd = findViewById(R.id.btnAdd);
        db = new Dbcontext(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}