package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableUser;

public class UserTableProvider extends DatabaseHelper {

    SQLiteDatabase db;

    public UserTableProvider(Context context) {

        super(context);
    }

    public void populateRowUser(String randomString, int foreignKey) {

        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableUser.TableUserClass.USER_EMAIL, randomString);
        c.put(TableUser.TableUserClass.USER_NAME, randomString);
        c.put(TableUser.TableUserClass.USER_PASSWORD, randomString);
        c.put(TableUser.TableUserClass.USER_LOCATION, randomString);
        db.insert(TableUser.TableUserClass.TABLE_Users, null, c);
    }

    public void populateDataUser() {
        int i = 1;
        while(i <= 50) {
            populateRowUser(Integer.toString(i), i);
            i=i+1;
        }
    }


}

