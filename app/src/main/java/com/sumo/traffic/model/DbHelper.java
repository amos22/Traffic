package com.sumo.traffic.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alessio on 06/01/17.
 */

class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "amoslawrence.db";
    private static final int DB_VERSION = 2;
    private static final String TEXT_TYPE=" TEXT";
    private static final String INT_TYPE=" INTEGER";
    private static final String COMMA_SEP=",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ ApplicationConstants.TABLE_NAME+" ("+ApplicationConstants._ID+
                    " INTEGER PRIMARY KEY,"+ApplicationConstants.HOUR+INT_TYPE+COMMA_SEP+ApplicationConstants.MINUTE
            +INT_TYPE+COMMA_SEP+ApplicationConstants.REMINDER+TEXT_TYPE+COMMA_SEP+ApplicationConstants.ENABLED+TEXT_TYPE+COMMA_SEP
    +ApplicationConstants.DESTINATION+TEXT_TYPE+" )";
    private static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS "+ApplicationConstants.TABLE_NAME;


    DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
