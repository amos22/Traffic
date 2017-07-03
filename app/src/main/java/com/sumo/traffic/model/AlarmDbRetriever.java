package com.sumo.traffic.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

/**
 * This class is what you actually use to insert,retrieve,update,delete etc rows in the database
 */

public final class AlarmDbRetriever {
    private static final String DEFAULT_SORT_ORDER = ApplicationConstants.HOUR+", "+ApplicationConstants.MINUTE+" ASC";
    private static final String QUERY_ACTIVE_ALARMS []= {ApplicationConstants._ID,ApplicationConstants.HOUR,ApplicationConstants.MINUTE,
    ApplicationConstants.REMINDER,ApplicationConstants.DESTINATION};
    private AlarmDbRetriever(){}


    public static void deleteAllNotifications(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(ApplicationConstants.CONTENT_URI,null,null);
    }


    public static Uri insertNotification (ContentResolver contentResolver,int hour, int minutes, String reminder,String destination){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ApplicationConstants.HOUR,hour);
        contentValues.put(ApplicationConstants.MINUTE, minutes);
        contentValues.put(ApplicationConstants.REMINDER,reminder);
        contentValues.put(ApplicationConstants.ENABLED,ApplicationConstants.ENABLED);
        contentValues.put(ApplicationConstants.DESTINATION,destination);
        return contentResolver.insert(ApplicationConstants.CONTENT_URI,contentValues);
    }

    public static void deleteNotification(Context context, int notificationId){
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = ContentUris.withAppendedId(ApplicationConstants.CONTENT_URI,notificationId);
        contentResolver.delete(uri,"",null);
    }

    public static void updateNotification(Context context, int notificationId,int hour, int minutes, String reminder,String destination){
        ContentValues contentValues = new ContentValues(3); //3 is the amount of data we're going to insert,you can leave it empty as well
        ContentResolver contentResolver = context.getContentResolver();
        contentValues.put(ApplicationConstants.HOUR,hour);
        contentValues.put(ApplicationConstants.MINUTE,minutes);
        contentValues.put(ApplicationConstants.REMINDER,reminder);
        contentValues.put(ApplicationConstants.ENABLED,ApplicationConstants.ENABLED);
        contentValues.put(ApplicationConstants.DESTINATION,destination);
        contentResolver.update(ContentUris.withAppendedId(ApplicationConstants.CONTENT_URI,notificationId),contentValues,null,null);
    }
    public static Cursor getAllNotifications (ContentResolver contentResolver){
        return contentResolver.query(ApplicationConstants.CONTENT_URI,ApplicationConstants.QUERY_COLUMNS,null,null,DEFAULT_SORT_ORDER);
    }
    public static Cursor getOneNotification(ContentResolver contentResolver, long id){
        Uri request = Uri.parse(ApplicationConstants.CONTENT_URI+"/"+id);
        return contentResolver.query(request, ApplicationConstants.QUERY_COLUMNS,null,null,DEFAULT_SORT_ORDER);
    }
    //this will be mostly needed for restoring notifications when devices reboots
    public static Cursor getActiveNotifications(ContentResolver contentResolver) throws SQLException{
        return contentResolver.query(ApplicationConstants.CONTENT_URI,QUERY_ACTIVE_ALARMS,ApplicationConstants.ENABLED+"=?",
                new String[]{ApplicationConstants.ENABLED},null);
    }
    public static void notificationExpired(Context context, int notificationId){
        ContentValues contentValues = new ContentValues(1);
        ContentResolver contentResolver = context.getContentResolver();
        contentValues.put(ApplicationConstants.ENABLED,ApplicationConstants.DISABLED);
        contentResolver.update(ContentUris.withAppendedId(ApplicationConstants.CONTENT_URI,notificationId),contentValues,null,null);

    }
}
