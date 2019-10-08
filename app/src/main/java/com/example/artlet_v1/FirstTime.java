package com.example.artlet_v1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.DbProvider.ContentTableProvider;
import com.example.artlet_v1.DbProvider.TagTableProvider;
import com.example.artlet_v1.DbProvider.UserGenreTableProvider;


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
            d1.InsertGenreData(d1);
            ContentTableProvider c1 = new ContentTableProvider(this);
            TagTableProvider t1 = new TagTableProvider(this);
            UserGenreTableProvider u1 = new UserGenreTableProvider(this);
            c1.populateDataContent();
            t1.populateDataTag();
            u1.populateDataUserGenre();


            Log.d("Inside if","bool is still false");

            // mark first time has ran.
            SharedPreferences.Editor editor = ins.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
            Log.d("Inside if","bool is true now");

        }
    }
}

