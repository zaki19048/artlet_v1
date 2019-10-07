package com.example.artlet_v1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResultsActivity extends AppCompatActivity {

    private  ArrayList<HashMap<String, String>> results= new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo( searchManager.getSearchableInfo(new
                ComponentName(this,SearchResultsActivity.class)));
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("search", "onNewIntent");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d("search", "intentReceived");
        String query = intent.getStringExtra(SearchManager.QUERY);
        showResults(query);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             query = intent.getStringExtra(SearchManager.QUERY);
        showResults(query);
        }
    }

    private void showResults(String query) {
        Log.d("search", query);
        fetchDatabaseResults();
        displayResultList();

    }

    private void fetchDatabaseResults() {
        String tableName = "content";

        DatabaseHelper dbHelper = new DatabaseHelper(this.getApplicationContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        HashMap<String, String> data = new HashMap<String, String>();

        try {

            Cursor c = db.rawQuery("SELECT id, name FROM genre", null);

            if (c != null ) {

                if  (c.moveToFirst()) {

                    do {
                        HashMap<String,String> data = new HashMap<>();
                        String firstName = c.getString(c.getColumnIndex("name"));

                        int age = c.getInt(c.getColumnIndex("id"));

                        data.put("name", firstName);
                        data.put("id", new String(age+" "));
                        this.results.add(data);

                    }while (c.moveToNext());

                }

            }

        } catch (SQLiteException se ) {

            Log.e(getClass().getSimpleName(), "Could not create or Open the database");

        } finally {

            if (db != null)

                db.execSQL("DELETE FROM " + tableName);

            db.close();

        }

    }

    private void displayResultList() {

        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(SearchResultsActivity.this, this.results, R.layout.list_view_item,new String[]{"name","location"}, new int[]{R.id.name,  R.id.location});
        lv.setAdapter(adapter);

    }
}
