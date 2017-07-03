package com.sumo.traffic.model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * This creates a ContentProvider which is really useful if you want your listview to be automatically
 * updated when the user set a new notification, cancel it, modify it and so on
 */

public class DbProvider extends ContentProvider {
    private DbHelper dbHelper;
    private static final UriMatcher sUrlMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ALARMS=1;
    private static final int ALARMS_ID=2;
    static {
        //insert your app package in the string below
        sUrlMatcher.addURI("com.sumo.traffic", ApplicationConstants.TABLE_NAME,ALARMS);
        sUrlMatcher.addURI("com.sumo.traffic", ApplicationConstants.TABLE_NAME+"/#", ALARMS_ID);
    }
    public DbProvider(){}
    @Override
    public boolean onCreate(){
        dbHelper = new DbHelper(getContext());
        return true;
    }
// here we set all the methods necessary to query, edit etc the database. You don't really need to worry about this
    @Override
    public Cursor query(Uri ur1, String[] projectionIn, String selection, String[] selectionArgs,
                        String sort){
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        int match = sUrlMatcher.match(ur1);
        switch (match){
            case ALARMS:
                qb.setTables(ApplicationConstants.TABLE_NAME);
                break;
            case ALARMS_ID:
                qb.setTables(ApplicationConstants.TABLE_NAME);
                qb.appendWhere("_id=");
                qb.appendWhere(ur1.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown Url"+ur1);
        }
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor = qb.query(db, projectionIn,selection,selectionArgs,null,null,sort);
        if (cursor == null){
            throw new SQLException("cursor is empty");
        }
        cursor.setNotificationUri(getContext().getContentResolver(), ur1);
        return cursor;
    }
    @Override
    public String getType(Uri ur1){
        int match=sUrlMatcher.match(ur1);
        switch (match){
            case ALARMS:
                return "vnd.android.cursor.dir/"+ ApplicationConstants.TABLE_NAME;
            case ALARMS_ID:
                return "vnd.android.cursor.item/"+ ApplicationConstants.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URL");
        }
    }
    @Override
    public int update(Uri ur1, ContentValues values, String where, String[]whereArgs){
        int count;
        long rowId=0;
        int match=sUrlMatcher.match(ur1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        switch(match){
            case ALARMS_ID:{
                String segment=ur1.getPathSegments().get(1);
                rowId=Long.parseLong(segment);
                count = db.update(ApplicationConstants.TABLE_NAME,values, ApplicationConstants._ID+"="
                        +rowId,null);
                break;
            }
            default:{
                throw new UnsupportedOperationException("Cannot update URL:"+ur1);
            }
        }
        getContext().getContentResolver().notifyChange(ur1,null);
        return count;
    }

    @Override
    public Uri insert(Uri ur1,ContentValues initialValues){
        if(sUrlMatcher.match(ur1)!=ALARMS){
            throw new IllegalArgumentException("insert request not formulated correctly, URL:"+ur1);
        }
        ContentValues values;
        if(initialValues!=null){
            values = new ContentValues(initialValues);
        }
        else {
            throw new IllegalArgumentException("Values to add to DB were not entered");
        }
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long rowId=db.insert(ApplicationConstants.TABLE_NAME,null,values);
        if(rowId<0){
            throw new SQLException("Cannot insert row in "+ur1);
        }
        Uri newUr1 = ContentUris.withAppendedId(ApplicationConstants.CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(newUr1,null);
        return newUr1;
    }

    public int delete(Uri ur1, String where, String[]whereArgs){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        int count;
        long rowId=0;
        switch(sUrlMatcher.match(ur1)){
            case ALARMS:
                count=db.delete(ApplicationConstants.TABLE_NAME, where,whereArgs);
                break;
            case ALARMS_ID:
                String segment=ur1.getPathSegments().get(1);
                rowId=Long.parseLong(segment);
                if (TextUtils.isEmpty(where)){
                    where ="_id="+segment;
                } else {
                    where="_id="+segment+" AND ("+ where+")";
                }
                count=db.delete(ApplicationConstants.TABLE_NAME,where,whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Cannot delete from the URL: "+ur1);
        }
        getContext().getContentResolver().notifyChange(ur1,null);
        return count;
    }
}
