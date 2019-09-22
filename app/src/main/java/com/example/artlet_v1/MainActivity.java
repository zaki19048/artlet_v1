package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;
//import DatabaseHelper;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DatabaseHelper(getApplicationContext());

    }
}
