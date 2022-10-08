package com.example.activity5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recView = findViewById(R.id.displayRecycleView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(new RecyclerAdapter(getResources().getStringArray(R.array.titleList),
                getResources().getStringArray(R.array.descriptionList),
                getResources().obtainTypedArray(R.array.imageIdList),
                this));
    }
}