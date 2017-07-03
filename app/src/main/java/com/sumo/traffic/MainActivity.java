package com.sumo.traffic;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sumo.traffic.AlarmCodes.AlarmService;
import com.sumo.traffic.AlarmCodes.EditAlarm;
import com.sumo.traffic.model.AlarmDbRetriever;
import com.sumo.traffic.model.ApplicationConstants;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {
    static final String[] PROJECTION = {ApplicationConstants._ID, ApplicationConstants.HOUR, ApplicationConstants.MINUTE
            ,ApplicationConstants.DESTINATION};
    CustomAdapter customAdapter;
    long notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
    }

    //let's handle database datas in onResume rather than in onCreate
    @Override
    protected void onResume() {
        super.onResume();
        //instantiate here the layout to be shown when no notifications are set(probably the user has opened the app for the first time)
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content_main);
        final ListView listView = (ListView) findViewById(R.id.list_view);
        Cursor cursor;
        boolean notificationExist = false;
        try {
            cursor = AlarmDbRetriever.getAllNotifications(getContentResolver());
            //if cursor is empty, we show the "first time" layout, otherwise we show the listview
           /* if (cursor.getCount() == 0) {
                listView.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                cursor.close();
                notificationExist = false;
            } else notificationExist = true;*/
            if (!cursor.moveToFirst()){
                listView.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                notificationExist = false;
            } else notificationExist = true;
        } catch (NullPointerException e) {
            //something went wrong, decide what to do. In my app i created an alert dialog like this
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Error retrieving information from database, please contact the app developer")
                    .setTitle("Application Error");
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (IllegalArgumentException e) {
            //same as before
        }
        //if notification exists, we show the listview
        if (notificationExist) {
            cursor = AlarmDbRetriever.getAllNotifications(getContentResolver());
            relativeLayout.setVisibility(View.GONE);
            customAdapter = new CustomAdapter(this, cursor);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(customAdapter);
            getLoaderManager().initLoader(0, null, this);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    notificationId = id;
                    Intent intent = new Intent(getApplicationContext(),EditAlarm.class);
                    intent.putExtra(ApplicationConstants._ID,notificationId);
                    startActivity(intent);
                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    notificationId = id;
                    registerForContextMenu(listView);
                    openContextMenu(listView);
                    return true;
                }
            });

        }
    }
/*
Don't worry about the loader methods here, they're needed to automatically update the listview when the database changes
 */
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, ApplicationConstants.CONTENT_URI, PROJECTION, null, null, null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        customAdapter.swapCursor(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        customAdapter.swapCursor(null);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        menu.setHeaderTitle("Edit or cancel this notification");
        menu.add(0,view.getId(),0,"Change it");
        menu.add(0,view.getId(),0,"Delete it");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("Delete it")){
            Intent intent = new Intent(this,AlarmService.class);
            intent.setAction(ApplicationConstants.DELETE);
            intent.putExtra(ApplicationConstants._ID,notificationId);
            startService(intent);
            Toast.makeText(this,R.string.notification_deleted,Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle().equals("Change it")){
            Intent intent = new Intent(this, poppers.class);
            intent.putExtra(ApplicationConstants._ID,notificationId);
            startActivity(intent);
        }else return false;
        return true;
    }

    //send the user to the alarm picker activity when he taps on a button
    public void onPlusButtonClicked(View view) {
     /*   Intent intent = new Intent(this, AlarmPicker.class);
        startActivity(intent);*/
        Intent service = new Intent(this,AlarmService.class);
        service.setAction(ApplicationConstants.DELETEALL);
        startService(service);


    }


    /*
    we create a customadapter that binds the database datas to the mainactivity layout
     */
    class CustomAdapter extends CursorAdapter {
        CustomAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // put the layout to show information for notifications here
            return LayoutInflater.from(context).inflate(R.layout.notifications_layout, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            //Here you bind datas from database to textviews etc. example:
            TextView hourText = (TextView) view.findViewById(R.id.hour_main);
            TextView minuteText = (TextView) view.findViewById(R.id.minute_main);
            TextView destinationText = (TextView) view.findViewById(R.id.destination_main);
            int hour = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.HOUR));
            int minutes = cursor.getInt(cursor.getColumnIndex(ApplicationConstants.MINUTE));
            String destination = cursor.getString(cursor.getColumnIndex(ApplicationConstants.DESTINATION));
            if(hour < 10) hourText.setText(String.format("0%d",hour));
            else hourText.setText(String.format("%d",hour));
            if(minutes < 10) minuteText.setText(String.format("0%d",minutes));
            else minuteText.setText(String.format("%d",minutes));
            destinationText.setText(destination);
        }

    }
}
