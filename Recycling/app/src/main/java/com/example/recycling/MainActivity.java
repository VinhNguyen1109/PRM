package com.example.recycling;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dict;
    private List<Word> words = new ArrayList<>();

    private void fakeData(){
        for(int i = 0; i < 1000; i++){
            words.add(new Word("Word" + i, "word " + i));
        }
    }

    private void bindingView(){
        dict = findViewById(R.id.dict);
    }

    private void bindingAction(){

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
        bindingView();
        bindingAction();
        fakeData();
        initRecylerView();
    }


    private void initRecylerView(){
        DictionaryAdapter adapter = new DictionaryAdapter(words, this);
        dict.setAdapter(adapter);
        dict.setLayoutManager(new LinearLayoutManager(this));
    }
}