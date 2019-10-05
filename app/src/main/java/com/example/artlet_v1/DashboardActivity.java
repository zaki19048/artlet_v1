package com.example.artlet_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.folioreader.FolioReader;


public class DashboardActivity extends AppCompatActivity {
    DatabaseHelper db;
    MangaReader mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //this.db = new DatabaseHelper(getApplicationContext());
        Search_Fragment searchFragment = new Search_Fragment();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.dashboardactivity, searchFragment, "Search_Fragment");
        fragmentTransaction.commit();
    }

    public void testManga(View view) {
        Intent newIntent = new Intent(this, MangaReader.class);
        startActivity(newIntent);
    }

    public void testEpub(View view) {
        FolioReader folioReader = FolioReader.get();
        folioReader.openBook(R.raw.lightningthief);
    }
}
