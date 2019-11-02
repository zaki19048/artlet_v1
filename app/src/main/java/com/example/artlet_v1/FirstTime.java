package com.example.artlet_v1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.artlet_v1.DbProvider.ContentTableProvider;
import com.example.artlet_v1.DbProvider.GenreTableProvider;
import com.example.artlet_v1.DbProvider.TagTableProvider;
import com.example.artlet_v1.DbProvider.UserGenreTableProvider;
import com.example.artlet_v1.DbProvider.UserTableProvider;


public class FirstTime extends Application {

    DatabaseHelper db;


    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences ins  = PreferenceManager.getDefaultSharedPreferences(this);
        if (!ins.getBoolean("firstTime", false)) {

            Log.d("Inside if","bool is false");
            this.db = new DatabaseHelper(getApplicationContext());
            DatabaseHelper d1 = new DatabaseHelper(this);
            d1.InsertGenreData();
            GenreTableProvider g1 = new GenreTableProvider(this);
            UserTableProvider u0 = new UserTableProvider(this);
            ContentTableProvider c1 = new ContentTableProvider(this);
            TagTableProvider t1 = new TagTableProvider(this);
            UserGenreTableProvider u1 = new UserGenreTableProvider(this);
            u0.populateDataUser();
            g1.populateDataGenre();
            c1.populateDataContent();
            u1.populateDataUserGenre();
            t1.populateDataTag();


            Log.d("Inside if","bool is still false");

            SharedPreferences.Editor editor = ins.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
            Log.d("Inside if","bool is true now");

        }
    }
}

