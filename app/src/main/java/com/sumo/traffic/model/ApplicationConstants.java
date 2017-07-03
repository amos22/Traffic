package com.sumo.traffic.model;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alessio on 06/01/17.
 */

public class ApplicationConstants implements BaseColumns {
    //this class simply define constants. Implementing BaseColumns means that we get another constant _ID
    public static final String CREATENOTIFICATION = "create notification";
    public static final String RECREATE = "recreate";
    public static final String DELETE = "delete";
    public static final String EDIT = "edit";
    public static final String  CREATEMULTIPLENOTIFICATIONS = "multiple";
    public static final String  DELETEALL = "deleteall";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String REMINDER = "reminder";
    public static final String DESTINATION = "destination";
    public static final String ENABLED = "enabled";
    public static final String DISABLED = "disabled";
    //choose name for your database, usually it's the app's name
    public static final String TABLE_NAME = "amoslawrence";
    //modify this corrispondingly to your package and app name
    public static final Uri CONTENT_URI=Uri.parse("content://com.sumo.traffic/"+TABLE_NAME);
    public static final String QUERY_COLUMNS[] = {_ID,HOUR,MINUTE,REMINDER,ENABLED,DESTINATION};

}
