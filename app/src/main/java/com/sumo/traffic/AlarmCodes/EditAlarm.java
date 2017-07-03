package com.sumo.traffic.AlarmCodes;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sumo.traffic.AlarmCodes.AlarmPickerFragment;
import com.sumo.traffic.AlarmCodes.AlarmService;
import com.sumo.traffic.R;
import com.sumo.traffic.model.AlarmDbRetriever;
import com.sumo.traffic.model.ApplicationConstants;

/*
This is basically a copy/paste of AlarmPicker, the only difference is it sends the EDIT action to the service plus
the Id used to retrieve the notification from the db
 */
public class EditAlarm extends AppCompatActivity {
    long notificationId;
    TextView distance,duration;
    EditText destinationEdit,reminderEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poppers);
        Intent intent = getIntent();
        notificationId = intent.getLongExtra(ApplicationConstants._ID,-1);
        //you should do something if notificationid is -1
        if (notificationId == -1) {
            //maybe show an alert dialog
        }
        Cursor cursor = AlarmDbRetriever.getOneNotification(getContentResolver(),notificationId);
        if(!cursor.moveToFirst()){
            //empty cursor, do something to inform the user, for example
            Toast.makeText(this,"Error retrieving information from database",Toast.LENGTH_LONG).show();
            finish();
        }

        EditText destinationEdit = (EditText)findViewById(R.id.editText);
        EditText reminderEdit = (EditText) findViewById(R.id.editText2);
        TextView hourText = (TextView) findViewById(R.id.hour_picked);
        TextView minuteText = (TextView)findViewById(R.id.minutes_picked);
        int hour = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.HOUR));
        int minutes = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.MINUTE));
        String destination = cursor.getString(cursor.getColumnIndex(ApplicationConstants.DESTINATION));
        String reminder = cursor.getString(cursor.getColumnIndex(ApplicationConstants.REMINDER));
        if(hour < 10) hourText.setText(String.format("0%d",hour));
        else hourText.setText(String.format("%d",hour));
        if(minutes < 10) minuteText.setText(String.format("0%d",minutes));
        else minuteText.setText(String.format("%d",minutes));
        destinationEdit.setText(destination);
        reminderEdit.setText(reminder);
        cursor.close();
    }



    //this method is called when the button is pushed. You can do it with an onclicklistener as well, it's up to you
    public void onTimePickClicked(View view){
        DialogFragment fragment = new AlarmPickerFragment();
        fragment.show(getFragmentManager(),"Time picker");
    }

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
        String destination = destinationEdit.getText().toString();
        String reminder = reminderEdit.getText().toString();
        Intent service = new Intent(this, AlarmService.class);
        //we set the action to create the notification in AlarmService, it's just a final String defined in MainActivity
        service.setAction(ApplicationConstants.EDIT);
        service.putExtra(ApplicationConstants._ID, notificationId);
        service.putExtra(ApplicationConstants.HOUR, hourPicked);
        service.putExtra(ApplicationConstants.MINUTE,minutePicked);
        service.putExtra(ApplicationConstants.REMINDER,reminder);
        service.putExtra(ApplicationConstants.DESTINATION,destination);
        startService(service);
        Toast.makeText(this,R.string.notification_modified,Toast.LENGTH_SHORT).show();
        finish();
    }
    public void onBackClicked(View view){
        //if user clicks on back, do nothing
        finish();
    }
}

