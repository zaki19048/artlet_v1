package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableContent;
import com.example.artlet_v1.TableUser;

public class ContentTableProvider extends DatabaseHelper {

    SQLiteDatabase db;

    public ContentTableProvider(Context context) {

        super(context);
    }

    public void populateRowContent(String randomString, int foreignKey) {

        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableContent.TableContentClass.CONTENT_AUTHORID, foreignKey);
        c.put(TableContent.TableContentClass.CONTENT_TITLE, randomString);
        c.put(TableContent.TableContentClass.CONTENT_GENREID, foreignKey);
        c.put(TableContent.TableContentClass.CONTENT_AUTHORID, foreignKey);
        c.put(TableContent.TableContentClass.CONTENT_TYPE, randomString);
        c.put(TableContent.TableContentClass.CONTENT_FILE, randomString);
        db.insert(TableContent.TableContentClass.TABLE_Content, null, c);
    }

    public void populateDataContent() {
        int i = 1;
        while(i <= 50) {
            populateRowContent(Integer.toString(i), i);
            i=i+1;
        }
    }



}

