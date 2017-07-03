package com.sumo.traffic.AlarmCodes;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sumo.traffic.R;

import java.util.Calendar;

/**
 * Created by alessio on 06/01/17.
 */

public class AlarmPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    public static int hourPicked;
    public static int minutePicked;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar =Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));
    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        /* here you can choose what to do  with the hour and minute picked by the user, for example
         * you can show hour and minute picked to the user through a textview
         */
        hourPicked = hourOfDay;
        minutePicked = minute;
        TextView hourText = (TextView) getActivity().findViewById(R.id.hour_picked);
        TextView minuteText= (TextView)getActivity().findViewById(R.id.minutes_picked);
        //the if/else below are needed to show the hour and minutes as "09:04", else you'd see "9:4"
        if(hourPicked < 10) hourText.setText(String.format("0%d",hourPicked));
        else hourText.setText(String.format("%d",hourPicked));
        if(minutePicked < 10) minuteText.setText(String.format("0%d",minutePicked));
        else minuteText.setText(String.format("%d",minutePicked));
    }

}
