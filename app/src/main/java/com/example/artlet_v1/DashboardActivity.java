package com.example.artlet_v1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.folioreader.FolioReader;


public class DashboardActivity extends AppCompatActivity {
    DatabaseHelper db;
    MangaReader mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


// -------Can be used later; dont remove
//        Search_Fragment searchFragment = new Search_Fragment();
//        FragmentManager mFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.dashboardactivity, searchFragment, "Search_Fragment");
//        fragmentTransaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSearchableInfo( searchManager.getSearchableInfo(new
                ComponentName(this,SearchResultsActivity.class)));
        return true;
    }


    public void testManga(View view) {
        Intent newIntent = new Intent(this, MangaReader.class);
        startActivity(newIntent);
    }

    public void testEpub(View view) {
        FolioReader folioReader = FolioReader.get();
        folioReader.openBook(R.raw.lightningthief);
    }

    public void openDoc(View view) {
        //ISHANI; YOUR CODE GOES HERE
    }

    public void openPdf(View view) {
        //UTSAV; YOUR CODE GOES HERE
    }
}
