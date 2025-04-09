package com.example.assignment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1 ;

    private static final String DATABASE_NAME = "my.db" ;
    private static final String MY_TABLE_NAME = "messages" ;

    private static final String NUMBER = "number" ;
    private static final String MESSAGE = "message" ;
    private static final String TEXT = "TEXT" ;
    private static final String SPACE = " "  ;
    private static final String RIGHT_BRACKET = "(" ;
    private static final String LEFT_BRACKET = ")" ;
    private static final String semicolon = ";";

    private static final String CREATE_USER_MESSAGE_TABLE = "CREATE TABLE" + SPACE + MY_TABLE_NAME + SPACE + RIGHT_BRACKET + NUMBER + SPACE + MESSAGE + SPACE + TEXT + LEFT_BRACKET + semicolon ;



    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME , null, DATABASE_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
