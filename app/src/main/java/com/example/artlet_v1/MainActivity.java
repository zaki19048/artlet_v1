package com.example.artlet_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.db = new DatabaseHelper(getApplicationContext());
    }




    public void show_login(View v)
    {
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void show_register(View v)
    {
        Intent i=new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}
