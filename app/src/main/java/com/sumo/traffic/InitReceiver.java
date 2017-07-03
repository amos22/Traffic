package com.sumo.traffic;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.sumo.traffic.AlarmCodes.AlarmService;
import com.sumo.traffic.model.ApplicationConstants;


/**
 * Created by alessio on 06/01/17.
 */

public class InitReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent service = new Intent (context,AlarmService.class);
            service.setAction(ApplicationConstants.RECREATE);
            context.startService(service);
        }
    }
}
