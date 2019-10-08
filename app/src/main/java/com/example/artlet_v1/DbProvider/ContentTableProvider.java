package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableContent;

public class ContentTableProvider extends DatabaseHelper {

    SQLiteDatabase db;

    public ContentTableProvider(Context context) {

        super(context);
        Log.d("Inside constructor", "Check");
    }

    public void populateRowContent(String randomString, int foreignKey) {

        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableContent.TableContentClass.CONTENT_AUTHORID, foreignKey);
        c.put(TableContent.TableContentClass.CONTENT_TITLE, randomString);
        c.put(TableContent.TableContentClass.CONTENT_GENREID, foreignKey);
        c.put(TableContent.TableContentClass.CONTENT_AUTHORID, foreignKey);
        c.put(TableContent.TableContentClass.CONTENT_TYPE, randomString);
        db.insert(TableContent.TableContentClass.TABLE_Content, null, c);
    }

    public void populateDataContent() {
        int i = 0;
        while(i < 50) {
            populateRowContent(String.valueOf(i), i);
            i=i+1;
        }
    }

}

