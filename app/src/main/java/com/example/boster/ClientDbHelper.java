package com.example.boster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "maBD.db";

    public final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS Deplacements (id INTEGER PRIMARY KEY, mode TEXT, distance INT, ville TEXT, date DATE);";
    final String SQL_DELETE = "DROP TABLE IF EXISTS Deplacements;";

    public ClientDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
