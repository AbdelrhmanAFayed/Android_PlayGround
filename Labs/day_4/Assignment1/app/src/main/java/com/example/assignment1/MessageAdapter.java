package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class MessageAdapter
{

    private Context context ;

    static DataBaseHelper dpHelper ;

    public MessageAdapter(Context context) {
        dpHelper = new DataBaseHelper(context);
        this.context = context;
    }

    public void insertMessage (Message message)
    {
        SQLiteDatabase dp = dpHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.NUMBER,message.getMobile());
        contentValues.put(DataBaseHelper.MESSAGE,message.getMessage());

        dp.insert(DataBaseHelper.MY_TABLE_NAME,null,contentValues);

    }

    public Message findMessage(String Phone)
    {
        Message msg = null ;

        SQLiteDatabase dp = dpHelper.getReadableDatabase();

        String[] args ={Phone};
        Cursor result = dp.query(DataBaseHelper.MY_TABLE_NAME,null,"NUMBER = ? " ,args,null,null,null);

        if(result != null)
        {
            result.moveToNext();
            msg = new Message(result.getString(1),result.getString(0));
        }

        result.close();

            return msg ;
    }




}
