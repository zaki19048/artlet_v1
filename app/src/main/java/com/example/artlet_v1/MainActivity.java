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


public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.db = new DatabaseHelper(getApplicationContext());
//        DatabaseHelper d1 = new DatabaseHelper(this);
//        this.db.InsertGenreData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
//                intent.putExtra("query", query);
//                startActivity(intent);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
        searchView.setSearchableInfo( searchManager.getSearchableInfo(new
                ComponentName(this,SearchResultsActivity.class)));
//        searchView.setIconifiedByDefault(true);
//        Intent intent = new Intent(this, SearchResultsActivity.class);
        return true;
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
