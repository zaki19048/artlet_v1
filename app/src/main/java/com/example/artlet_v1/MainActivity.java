package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;
//import DatabaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    MangaReader mr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DatabaseHelper(getApplicationContext());
    }

    public void testManga(View view)
    {
        Intent newIntent = new Intent(this, MangaReader.class);
        startActivity(newIntent);
    }
}
