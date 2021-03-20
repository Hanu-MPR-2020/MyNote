package com.mpr.mynote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.mpr.mynote.MainActivity;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "notes.db";

    public static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("CREATE TABLE " + DbSchema.NoteTable.NAME + "( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DbSchema.NoteTable.Cols.TEXT + " TEXT " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop existing table
        Log.w("MyNote", "onUpgrade: dropping and recreating note table");
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.NoteTable.NAME);

        //Recreating table
        onCreate(db);
    }

}
