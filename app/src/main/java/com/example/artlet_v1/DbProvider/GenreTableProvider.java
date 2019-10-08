package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableGenre;


public class GenreTableProvider extends DatabaseHelper {

    SQLiteDatabase db;

    public GenreTableProvider(Context context) {
        super(context);
    }

    public void populateRowGenre(String randomString, int foreignKey) {
        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableGenre.TableGenreClass.GENRE_NAME, randomString);
        db.insert(TableGenre.TableGenreClass.TABLE_Genre, null, c);
    }

    public void populateDataGenre() {
        int i = 1;
        while(i <= 50) {
            populateRowGenre(Integer.toString(i), i);
            i=i+1;
        }
    }



}

