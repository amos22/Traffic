package com.sumo.traffic.AlarmCodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sumo.traffic.R;
import com.sumo.traffic.model.ApplicationConstants;


/*you need to extend FragmentActivity to use the TimePickerDialog. If this is not possible for you,
 * I'll show you how to create a custom dialog fragment for the timepicker
 */
public class AlarmPicker extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poppers);
    }

    //this method is called when the button is pushed. You can do it with an onclicklistener as well, it's up to you


    //method called when the user wants to confirm and set the alarm
    public void onConfirmClicked(View view){
        /* using the static int we can get the hour and minute picked by the user in this activity,
           it might not be the best solution, but it's definetly the quickest and it works really well
           (a better one would be to create a custom dialog with a custom interface attached to this activity).
         */
        int hourPicked = AlarmPickerFragment.hourPicked;
        int minutePicked = AlarmPickerFragment.minutePicked;
        EditText destinationEdit = (EditText) findViewById(R.id.editText);
        EditText reminderEdit = (EditText) findViewById(R.id.editText2);
        /*
        you should set a default destination and reminder for the notification and database if the
        user doesn't write anything. TODO if you don't do it, app is probably going to crash. Example
         */
        String destination = destinationEdit.getText().toString();
        if(destination.equals("")) destination = "Notification"; //this will be the title of the notification
        String reminder = reminderEdit.getText().toString();
        if(reminder.equals("")) reminder = "You got a new notification"; //notification's content
        Intent service = new Intent(this, AlarmService.class);
        //we set the action to create the notification in AlarmService, it's just a final String defined in MainActivity
        service.setAction(ApplicationConstants.CREATENOTIFICATION);
        service.putExtra(ApplicationConstants.HOUR, hourPicked);
        service.putExtra(ApplicationConstants.MINUTE,minutePicked);
        service.putExtra(ApplicationConstants.REMINDER,reminder);
        service.putExtra(ApplicationConstants.DESTINATION,destination);

        startService(service);
        Toast.makeText(this,R.string.notification_set,Toast.LENGTH_SHORT).show();
        finish();
    }


    public void onBackClicked(View view){
        //if user clicks on back, do nothing
        finish();
    }
}
