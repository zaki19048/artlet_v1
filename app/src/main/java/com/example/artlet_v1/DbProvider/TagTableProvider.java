package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableTag;

public class TagTableProvider extends DatabaseHelper {

    SQLiteDatabase db;

    public TagTableProvider(Context context) {
        super(context);
    }

    public void populateRowTag(String randomString, int foreignKey) {
        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableTag.TableTagClass.TAG_CONTENTID, foreignKey);
        c.put(TableTag.TableTagClass.TAG_ID, foreignKey);
        c.put(TableTag.TableTagClass.TAG_NAME, randomString);
        db.insert(TableTag.TableTagClass.TABLE_Tags, null, c);
    }

    public void populateDataTag() {
        int i = 0;
        while(i < 50) {
            populateRowTag(String.valueOf(i), i);
            i=i+1;
        }
    }

}

