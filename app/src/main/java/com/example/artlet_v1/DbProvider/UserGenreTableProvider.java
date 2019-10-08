package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableUserGenre;

public class UserGenreTableProvider extends DatabaseHelper {

    SQLiteDatabase db;

    public UserGenreTableProvider(Context context) {
        super(context);
    }

    public void populateRowUserGenre(String randomString, int foreignKey) {
        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableUserGenre.TableUserGenreClass.UG_GENREID, foreignKey);
        c.put(TableUserGenre.TableUserGenreClass.UG_USERID, foreignKey);
        db.insert(TableUserGenre.TableUserGenreClass.TABLE_User_Genre, null, c);
    }

    public void populateDataUserGenre() {
        int i = 0;
        while(i < 50) {
            populateRowUserGenre(String.valueOf(i), i);
            i=i+1;
        }
    }

}

