package com.sumo.traffic.AlarmCodes;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import com.sumo.traffic.model.AlarmDbRetriever;
import com.sumo.traffic.model.ApplicationConstants;

import java.util.Calendar;

/**
 * Created by alessio on 06/01/17.
 */

public class AlarmService extends IntentService {
    private static final String TAG = "AlarmService";
    private IntentFilter matcher;
    public AlarmService(){
        super(TAG);
        matcher = new IntentFilter();
        matcher.addAction(ApplicationConstants.CREATENOTIFICATION);
        matcher.addAction(ApplicationConstants.DISABLED);
        matcher.addAction(ApplicationConstants.RECREATE);
        matcher.addAction(ApplicationConstants.EDIT);
        matcher.addAction(ApplicationConstants.DELETE);
        //here you can add action to cancel notifications, modify them and so on like this
        //matcher.addAction(MainActivity.CANCELNOTIFICATION);
        matcher.addAction(ApplicationConstants.CREATEMULTIPLENOTIFICATIONS);
        matcher.addAction(ApplicationConstants.DELETEALL);
    }
    @Override
    protected void onHandleIntent(Intent intent){
        int hour= intent.getIntExtra(ApplicationConstants.HOUR,-1); /* -1 is the default value, if it doesnt find the hour. You should
        do something in that case, because it means the notification is not going to be set properly
        */
        int minute = intent.getIntExtra(ApplicationConstants.MINUTE,-1);
        String reminder = intent.getStringExtra(ApplicationConstants.REMINDER);
        String destination = intent.getStringExtra(ApplicationConstants.DESTINATION);
        String action = intent.getAction();
        //we check if this service has been started from one of the constants defined before, if yes we check what action has been passed to the service
        if(matcher.matchAction(action)){
            if(ApplicationConstants.CREATENOTIFICATION.equals(action)){
                //first thing to do, we store the notification data in the database
                Uri uri = AlarmDbRetriever.insertNotification(getContentResolver(),hour,minute,reminder,destination);
                String segment = uri.getPathSegments().get(1); // we get the id of the notification created
                int notificationId = Integer.parseInt(segment);
                execute(ApplicationConstants.CREATENOTIFICATION,hour,minute,notificationId,reminder,destination);
            }
            else if (ApplicationConstants.DISABLED.equals(action)){
                int id = intent.getIntExtra(ApplicationConstants._ID,-1);
                /*
                we set the notification as expired (see AlarmReceiver). If you don't care, you can
                call method to delete notification from database instead of this one
                 */
                AlarmDbRetriever.notificationExpired(this,id);
            }
            else if(ApplicationConstants.EDIT.equals(action)){
                int notificationId = (int)intent.getLongExtra(ApplicationConstants._ID,-1);
                AlarmDbRetriever.updateNotification(this,notificationId,hour,minute,reminder
                ,destination);
                execute(ApplicationConstants.CREATENOTIFICATION,hour,minute,notificationId,reminder,destination);
            }
            else if(ApplicationConstants.DELETE.equals(action)){
                long id = intent.getLongExtra(ApplicationConstants._ID,-1);
                int notificationId = (int)id;
                //the cursor below is needed to cancel the pending intent in execute method, feel free to
                //improve it
                Cursor cursor = AlarmDbRetriever.getOneNotification(getContentResolver(),id);
                if(!cursor.moveToFirst()){
                    //cursor is empty, do something to inform the user
                }
                hour = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.HOUR));
                minute = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.MINUTE));
                reminder = cursor.getString(cursor.getColumnIndex(ApplicationConstants.REMINDER));
                destination = cursor.getString(cursor.getColumnIndex(ApplicationConstants.DESTINATION));
                execute(ApplicationConstants.DELETE,hour,minute,notificationId,reminder,destination);
                AlarmDbRetriever.deleteNotification(this,notificationId);
                cursor.close();
            }
            //this is to recreate the notifications for when the user reboot the device
            else if (ApplicationConstants.RECREATE.equals(action)){
                Cursor cursor;
                try{
                    cursor = AlarmDbRetriever.getActiveNotifications(getContentResolver());
                } catch (SQLException e){
                    //if cursor is empty, we just return
                    return;
                }
                cursor.moveToFirst();
                do{
                    hour = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.HOUR));
                    minute = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.MINUTE));
                    reminder = cursor.getString(cursor.getColumnIndex(ApplicationConstants.REMINDER));
                    destination = cursor.getString(cursor.getColumnIndex(ApplicationConstants.DESTINATION));
                    int notificationId = (int) cursor.getLong(cursor.getColumnIndex(ApplicationConstants._ID));
                    execute(ApplicationConstants.CREATENOTIFICATION,hour,minute,notificationId,reminder,destination);
                } while(cursor.moveToNext());
                cursor.close();
            }

            else if (ApplicationConstants.CREATEMULTIPLENOTIFICATIONS.equals(action)){
                int[]multipleHours = intent.getIntArrayExtra(ApplicationConstants.HOUR);
                int[]multipleMinutes = intent.getIntArrayExtra(ApplicationConstants.MINUTE);
                String[]multipleDestinations = intent.getStringArrayExtra(ApplicationConstants.DESTINATION);
                String[]multipleReminders = intent.getStringArrayExtra(ApplicationConstants.REMINDER);
                //Now I will consider that each of these arrays have the same length
                for (int i =0;i<multipleHours.length;i++){
                    Uri uri = AlarmDbRetriever.insertNotification(getContentResolver(),multipleHours[i],
                            multipleMinutes[i],multipleReminders[i],multipleDestinations[i]);
                    String segment = uri.getPathSegments().get(1);
                    int notificationId = Integer.parseInt(segment);
                    //we inserted the notification in the database, now we set it to be fired with execute
                    execute(ApplicationConstants.CREATENOTIFICATION,multipleHours[i],multipleMinutes[i],notificationId,
                            multipleReminders[i],multipleDestinations[i]);
                }
            }
            else if(ApplicationConstants.DELETEALL.equals(action)){
                Cursor cursor;
                cursor = AlarmDbRetriever.getAllNotifications(getContentResolver());
                if (!cursor.moveToFirst()) return;
                do{
                    hour = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.HOUR));
                    minute = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.MINUTE));
                    reminder = cursor.getString(cursor.getColumnIndex(ApplicationConstants.REMINDER));
                    destination = cursor.getString(cursor.getColumnIndex(ApplicationConstants.DESTINATION));
                    int notificationId = (int) cursor.getLong(cursor.getColumnIndex(ApplicationConstants._ID));
                    execute(ApplicationConstants.DELETE,hour,minute,notificationId,reminder,destination);
                    AlarmDbRetriever.deleteAllNotifications(this);
                }while (cursor.moveToNext());
                cursor.close();
            }
/*
            else if(ApplicationConstants.DELETEALL.equals(action)){
                Cursor cursor;
                try{
                    cursor = AlarmDbRetriever.getAllNotifications(getContentResolver());
                } catch (SQLException |NullPointerException  exception){
                    //you might choose to do something, it might also be the database is empty, so I'll just do nothing
                    return;
                }
                cursor.moveToFirst();
                do{
                    hour = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.HOUR));
                    minute = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.MINUTE));
                    reminder = cursor.getString(cursor.getColumnIndex(ApplicationConstants.REMINDER));
                    destination = cursor.getString(cursor.getColumnIndex(ApplicationConstants.DESTINATION));
                    int notificationId = (int) cursor.getLong(cursor.getColumnIndex(ApplicationConstants._ID));
                    execute(ApplicationConstants.DELETE,hour,minute,notificationId,reminder,destination);
                    AlarmDbRetriever.deleteAllNotifications(this);
                }while (cursor.moveToNext());
                cursor.close();
            }
*/



        }
    }

    private void execute(String action, int hour, int minute, int notificationId,String reminder,String destination){
        Intent i;
        PendingIntent pi;
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int monthDay = calendar.get(Calendar.DAY_OF_YEAR);
        i = new Intent(this,AlarmReceiver.class);
        i.putExtra(ApplicationConstants._ID, notificationId);
        i.putExtra(ApplicationConstants.REMINDER,reminder);
        i.putExtra(ApplicationConstants.DESTINATION,destination);
        pi = PendingIntent.getBroadcast(this,notificationId,i,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(action.equals(ApplicationConstants.CREATENOTIFICATION)) {
            //if it's 10pm and the user set the notification for 10 am, android will fire the notification immediately, this solves this problem
            if (currentHour > hour) {
                calendar.set(Calendar.DAY_OF_YEAR, (monthDay + 1));
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
            } else if (currentHour <= hour) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
            }
        }
        else if (action.equals(ApplicationConstants.DELETE)){
            alarmManager.cancel(pi);
        }
    }
}
