package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableUser;

import java.util.Random;

public class UserTableProvider extends DatabaseHelper {

    SQLiteDatabase db;
    String initial = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public UserTableProvider(Context context) {

        super(context);
    }

    public void populateRowUser(String randomString, int dummyInt) {

        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableUser.TableUserClass.USER_EMAIL, randomString);
        c.put(TableUser.TableUserClass.USER_NAME, randomString);
        c.put(TableUser.TableUserClass.USER_PASSWORD, randomString);
        c.put(TableUser.TableUserClass.USER_LOCATION, randomString);
        db.insert(TableUser.TableUserClass.TABLE_Users, null, c);
        db.close();
    }

    public String randomGenerator(String p)
    {
        StringBuilder s = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 7; i++) {
            s.append(p.charAt(r.nextInt(p.length())));
        }

        return s.toString();
    }

    public void populateDataUser() {
        int i = 1;
        while(i <= 50) {
            populateRowUser(randomGenerator(initial), i);
            i=i+1;
        }
    }


}

