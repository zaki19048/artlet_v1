package com.example.artlet_v1.DbProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.artlet_v1.DatabaseHelper;
import com.example.artlet_v1.TableContent;
import com.example.artlet_v1.TableUser;

import java.util.Random;

public class ContentTableProvider extends DatabaseHelper {

    SQLiteDatabase db;
    String initial = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    public ContentTableProvider(Context context) {

        super(context);
    }

    public void populateRowContent(String randomString, int dummyInt) {

        db = getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(TableContent.TableContentClass.CONTENT_AUTHORID, dummyInt);
        c.put(TableContent.TableContentClass.CONTENT_TITLE, randomString);
        c.put(TableContent.TableContentClass.CONTENT_GENREID, dummyInt);
        c.put(TableContent.TableContentClass.CONTENT_AUTHORID, dummyInt);
        c.put(TableContent.TableContentClass.CONTENT_TYPE, randomString);
        c.put(TableContent.TableContentClass.CONTENT_FILE, randomString);
        db.insert(TableContent.TableContentClass.TABLE_Content, null, c);
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

    public void populateDataContent() {
        int i = 1;
        String[] rep = new String[10];
        for (int j=0;j<rep.length;j++)
        {
            rep[j]=randomGenerator(initial);
            System.out.println(rep[j]);
        }

        while(i <= 100) {
            if(i<=50)
            {
                populateRowContent(randomGenerator(initial), i);
            }

            if(i>50)
            {
                int index = new Random().nextInt(rep.length);
                populateRowContent(rep[index], i);

            }
            i=i+1;
            }

        }
    }





