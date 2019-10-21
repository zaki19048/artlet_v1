package com.example.artlet_v1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.folioreader.FolioReader;
import com.google.android.material.navigation.NavigationView;


public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper db;
    MangaReader mr;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolBar=findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        drawer=findViewById(R.id.dashboardactivity);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolBar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();



// -------Can be used later; dont remove
//        Search_Fragment searchFragment = new Search_Fragment();
//        FragmentManager mFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.dashboardactivity, searchFragment, "Search_Fragment");
//        fragmentTransaction.commit();


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
