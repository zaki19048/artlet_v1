package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import DatabaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.artlet_v1.DbProvider.TagTableProvider;
import com.folioreader.FolioReader;



public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
