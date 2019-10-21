package com.example.artlet_v1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.folioreader.FolioReader;
import com.google.android.material.navigation.NavigationView;

import com.ramotion.foldingcell.FoldingCell;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class DashboardActivity extends AppCompatActivity {
    DatabaseHelper db;
    MangaReader mr;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        getSupportActionBar().setLogo(R.drawable.ic_hamburger);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        SpannableString s = new SpannableString(settingsItemTitle);
//
//        s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_LEFT), 0, s.length(), 0);
//
//        item.setTitle(s);
        // get our list view
        ListView theListView = findViewById(R.id.mainListView);

        this.drawer=findViewById(R.id.dashboardactivity);
        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                this.drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().show();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().hide();
            }
        };
        this.drawer.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
                testManga();
            }
        });

        items.get(1).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
                testManga();
            }
        });

        items.get(2).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
                testEpub();
            }
        });

        items.get(3).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
                testManga();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });


    // -------Can be used later; dont remove
    //        Search_Fragment searchFragment = new Search_Fragment();
    //        FragmentManager mFragmentManager = getSupportFragmentManager();
    //        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
    //        fragmentTransaction.add(R.id.dashboardactivity, searchFragment, "Search_Fragment");
    //        fragmentTransaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
//                Toast.makeText(this, "click..!!", Toast.LENGTH_SHORT).show();
                this.drawer.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_leaderboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LeaderBoardFragmentt()).commit();
                break;

            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FeedbackFragmentt()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragmentt()).commit();
                break;

            case R.id.nav_polls:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PollsFragmentt()).commit();
                break;

            case R.id.nav_send:
                Toast.makeText(this,"SEND",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_share:
                Toast.makeText(this,"SHARE",Toast.LENGTH_SHORT).show();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    // add in every common activity
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSearchableInfo( searchManager.getSearchableInfo(new
                ComponentName(this,SearchResultsActivity.class)));

//        MenuItem appBar = findViewById(R.id.navBar);
        MenuItem appBar = menu.getItem(0);

        appBar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                getSupportActionBar().hide();
                getActionBar().hide();
                openNavBar();
                return true;
            }
        });
        return true;
    }

    public void openNavBar() {
        this.drawer.openDrawer(Gravity.LEFT);
    }

    void testManga() {
        Intent newIntent = new Intent(this, MangaReader.class);
        startActivity(newIntent);
    }

    void testEpub() {
        FolioReader folioReader = FolioReader.get();
        folioReader.openBook(R.raw.lightningthief);
    }

    void openDoc() {
        //ISHANI; YOUR CODE GOES HERE
    }

    void openPdf() {
        //UTSAV; YOUR CODE GOES HERE
    }
}
