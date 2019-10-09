package com.example.artlet_v1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.artlet_v1.Adapters.SearchViewAdapter;
import com.folioreader.FolioReader;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResultsActivity extends AppCompatActivity {

    private  ArrayList<HashMap<String, String>> results= new ArrayList<>();
    private String query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        handleIntent(getIntent());
        String query = getIntent().getStringExtra(SearchManager.QUERY);
        this.query = query;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchResultsActivity.this, SearchResultsActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Intent intent = new Intent(SearchResultsActivity.this, SearchResultsActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return false;
            }
        });
        searchView.setIconified(false);
        searchView.setQuery(this.query, false);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
        this.query = query;
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

        this.results.clear();

        String tableName = "content";

        DatabaseHelper dbHelper = new DatabaseHelper(this.getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            String lowerQuery = this.query.toLowerCase();
            //Join query to be used : private final String MY_QUERY = "SELECT id, name, type , file,  FROM content c1 INNER JOIN tag ON content.id=tag.content_id WHERE tag.name like ?";
            //db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});
            Cursor c = db.rawQuery("SELECT id, name FROM genre WHERE LOWER(name) like ?", new String[] {"%" + lowerQuery + "%"});
//            Cursor c = db.rawQuery("SELECT id, name FROM genre", null);

            int count = 0;
            if (c != null ) {

                if  (c.moveToFirst()) {
//                    Log.d("before count", "" + c.getCount());
                    do {
                        HashMap<String,String> data = new HashMap<>();
                        String firstName = c.getString(c.getColumnIndex("name"));

                        int age = c.getInt(c.getColumnIndex("id"));

                            data.put("title", firstName);
                            data.put("type", new String(age + " "));
                            data.put("file", " " + firstName);
                            count++;
                            this.results.add(data);

                    }while (c.moveToNext());

                }
//                Log.d("total-count", "" + count);
            }
            c.close();
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
        if(this.results.size() == 0) {
            lv.setEmptyView(this.findViewById(R.id.empty));
        } else {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openManga();
            }
        });
        ListAdapter adapter = new SearchViewAdapter(SearchResultsActivity.this, this.results);
        lv.setAdapter(null);
        lv.setAdapter(adapter);
        }
    }

    public void openManga() {
        Intent intent = new Intent(SearchResultsActivity.this, MangaReader.class);
        startActivity(intent);
    }

    public void openEpub() {
        FolioReader folioReader = FolioReader.get();
        folioReader.openBook(R.raw.lightningthief);
    }

    public void openPdfReader() {
        Intent intent = new Intent(SearchResultsActivity.this, MangaReader.class);
        startActivity(intent);
    }


    public void openDocReader() {
        Intent intent = new Intent(SearchResultsActivity.this, MangaReader.class);
        startActivity(intent);
    }

}
