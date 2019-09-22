package com.example.artlet_v1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Name
    private static final String DATABASE_NAME = "artlet";

    // Table Names
    private static final String TABLE_Users= "user";
    private static final String TABLE_Genre = "genre";
    private static final String TABLE_Content = "content";
    private static final String TABLE_Tags = "tag";
    private static final String TABLE_User_Genre = "user_genre";


    private static final String CREATE_TABLE_User = "CREATE TABLE user (id INT(11) NOT NULL, name VARCHAR(255),email VARCHAR(255),password VARCHAR(255),location VARCHAR,created_at TIMESTAMP ,PRIMARY KEY (id)\n)";

    // genre table create statement
    private static final String CREATE_TABLE_Genre = "CREATE TABLE `genre` (id INT(11) NOT NULL ,name VARCHAR(255),created_at TIMESTAMP ,PRIMARY KEY (id))";

    // content table create statement
    private static final String CREATE_TABLE_Content = "CREATE TABLE content (id INT(11)  NOT NULL,title VARCHAR(255),author_id INT(11) NOT NULL,genre_id INT(11),type VARCHAR(255),file VARCHAR(255),created_at TIMESTAMP ,PRIMARY KEY (id)\n)";

    private static final String CREATE_TABLE_Tag = "CREATE TABLE tag (id INT(11) NOT NULL,content_id INT(11) ,name VARCHAR(255) NOT NULL,created_at TIMESTAMP,PRIMARY KEY (id))";

    private static final String CREATE_TABLE_User_Genre = "CREATE TABLE user_genre (id INT(11) NOT NULL,genre_id INT(11) ,user_id INT(11),created_at TIMESTAMP ,PRIMARY KEY (id))";
    private SQLiteDatabase db;

    public  DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_User);
        db.execSQL(CREATE_TABLE_Genre);
        db.execSQL(CREATE_TABLE_Tag);
        db.execSQL(CREATE_TABLE_Content);
        db.execSQL(CREATE_TABLE_User_Genre);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Tags);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Genre);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Content);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User_Genre);

        // create new tables
        onCreate(db);
    }
}