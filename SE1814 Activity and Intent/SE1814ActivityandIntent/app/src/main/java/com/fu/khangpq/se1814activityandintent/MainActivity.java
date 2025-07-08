package com.fu.khangpq.se1814activityandintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToAct2;
    private Button btnOpen;
    private Button btnDial;
    private EditText edtData;
    private ActivityResultLauncher<Intent> launcher;

    private void bindingView() {
        btnGoToAct2 = findViewById(R.id.btnGoToAct2);
        btnOpen = findViewById(R.id.btnOpen);
        btnDial = findViewById(R.id.btnDial);
        edtData = findViewById(R.id.edtData);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onActivity2Result);
    }

    private void onActivity2Result(ActivityResult activityResult) {
        if (activityResult.getResultCode() != RESULT_OK) {
            Toast.makeText(this, "LOI ROI", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = activityResult.getData();
        String name = data.getStringExtra("data");
        edtData.setText(name);
    }

    private void bindingAction() {
        btnGoToAct2.setOnClickListener(this::onBtnGoToAct2Click);
        btnOpen.setOnClickListener(this::onBtnOpenClick);
        btnDial.setOnClickListener(this::onBtnDialClick);
    }

    private void onBtnDialClick(View view) {
        String data = edtData.getText().toString();
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + data));
        startActivity(i);
    }

    private void onBtnOpenClick(View view) {
        String data = edtData.getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://" + data));
        startActivity(i);
    }

    private final static int REQUEST_CODE = 1;

    private void onBtnGoToAct2Click(View view) {
        String data = edtData.getText().toString();
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("name", data);
        i.putExtra("age", 20);
//        startActivity(i);
//        startActivityForResult(i, REQUEST_CODE);
        launcher.launch(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "LOI ROI", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE:
                String name = data.getStringExtra("data");
                edtData.setText(name);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d("KhangPQ.Debug", "onCreate");
        bindingView();
        bindingAction();
//        onReceiveIntent();
    }

    private void onReceiveIntent() {
        Intent i = getIntent();
        String data = i.getStringExtra("data");
        edtData.setText(data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("KhangPQ.Debug", "onStart");

    }

    @Override
    protected void onResume() {
        Log.d("KhangPQ.Debug", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("KhangPQ.Debug", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("KhangPQ.Debug", "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("KhangPQ.Debug", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("KhangPQ.Debug", "onDestroy");
        super.onDestroy();
    }
}