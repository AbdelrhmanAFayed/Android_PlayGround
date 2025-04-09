package com.example.assignment1;

import android.content.Context;

public class MessageAdapter
{

    private Context context ;

    static DataBaseHelper dpHelper ;

    public MessageAdapter(Context context) {

        dpHelper = new DataBaseHelper(context);
        this.context = context;
    }


}
