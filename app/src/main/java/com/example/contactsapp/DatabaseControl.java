package com.example.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseControl {

    SQLiteDatabase database;
    DatabaseHelper helper;

    public DatabaseControl(Context context) {
        helper = new DatabaseHelper(context);

    }

    public void open() {
        database = helper.getWritableDatabase();

    }
    public void close() {
        helper.close();
    }

    public boolean insert(String song, String artist, String album){
        ContentValues values = new ContentValues();
        values.put("song", song);
        values.put("artist", artist);
        values.put("album", album);
        return database.insert("contact", null, values) > 0;
    }

    public void delete(String name){
        database.delete("contact", "name =\""+name+"\"", null);
    }

    public String getArtist(String song){
        String query = "select artist from contact where song =\""+song+"\"";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String artist = cursor.getString(0);
        cursor.close();
        return artist;
    }
    public String getAlbum(String song){
        String query = "select album from contact where song =\""+song+"\"";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String album = cursor.getString(0);
        cursor.close();
        return album;
    }

    public ArrayList<String> getAllSongsArray(){
        String query = "select song from contact";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> list = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            String song = cursor.getString(0);
            list.add(song);
            cursor.moveToNext();
        }
        cursor.close();
        String[] array = new String [list.size()];
        return list;
    }

    public ArrayList<String> getAllSongs(){
        String query = "select song from contact";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> list = new ArrayList<String>();
        while(!cursor.isAfterLast()){
            String song = cursor.getString(0);
            list.add(song);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}// end of class
