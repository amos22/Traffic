package com.sumo.traffic.AlarmCodes;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.sumo.traffic.MainActivity;
import com.sumo.traffic.R;
import com.sumo.traffic.model.ApplicationConstants;


/**
 * Created by alessio on 06/01/17.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        int notificationId = intent.getIntExtra(ApplicationConstants._ID,-1);
        String reminder = intent.getStringExtra(ApplicationConstants.REMINDER);
        String destination= intent.getStringExtra(ApplicationConstants.DESTINATION);
        //we finally create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.test2) //your app icon
                .setContentTitle(destination)
                .setContentText(reminder)
                .setAutoCancel(true); //this is needed to cancel the notification after the user tapped on it
        //choose what activity to show when the user taps on the notification
        Intent toMainActivity = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(toMainActivity);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(notificationId,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId,builder.build());
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[] {500,500},-1);
        /*
        after the notification has been sent, we start AlarmService to disable the notification in the database.
        The notification datas will be kept in the database (so they can be shown in a listview for example) but
        the notification will be set as expired.
         */
        Intent service = new Intent(context, AlarmService.class);
        service.setAction(ApplicationConstants.DISABLED);
        service.putExtra(ApplicationConstants._ID,notificationId);
        context.startService(service);
    }
}
