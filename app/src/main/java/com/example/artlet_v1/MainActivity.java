package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import DatabaseHelper;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.db = new DatabaseHelper(getApplicationContext());
        Search_Fragment searchFragment=new Search_Fragment();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainactivity,searchFragment,"Search_Fragment");
        fragmentTransaction.commit();

    }
}
