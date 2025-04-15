package com.example.assignment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VER = 1 ;

    public static final String DATABASE_NAME = "my.db" ;
    public static final String MY_TABLE_NAME = "messages" ;

    public static final String NUMBER = "number" ;
    public static final String MESSAGE = "message" ;

    public static final String ID = "id";


    public static final String CREATE_USER_MESSAGE_TABLE =
            "CREATE TABLE " + MY_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMBER + " TEXT, " + MESSAGE + " TEXT);";


    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME , null, DATABASE_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
