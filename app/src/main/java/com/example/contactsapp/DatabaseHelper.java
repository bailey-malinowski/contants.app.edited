package com.example.contactsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) { super(context, "contacts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table contact (_id integer primary key autoincrement, " +
                "song text, artist text, album text);";
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists contact");
        onCreate(sqLiteDatabase);
    }
}
