package com.sumo.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class  DestinationActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listViewContent;
    DestinationItemAdapter arrayAdapter;
    Button  deleteButton;
    int currentMarkerSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

     /*   int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));*/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentMarkerSize = extras.getInt("currentMarker");
        }

        listView = (ListView) findViewById(R.id.listView);
        listViewContent = new ArrayList<String>();

        for (int currentMarker = 0; currentMarker < currentMarkerSize; currentMarker++) {



            listViewContent.add(buildListViewString(currentMarker));
        }


        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewContent);
        //listView.setAdapter(arrayAdapter);


        //instantiate custom adapter
        arrayAdapter = new DestinationItemAdapter(listViewContent, this.getApplicationContext(), this);

        //handle listview and assign adapter
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(109, intent);
                finish();
            }
        });
    }

    private String buildListViewString(int index) {

        String destinationString = "";
        destinationString += "Name: ";
        Marker marker = (Marker) traffic.markers.get(index);
        destinationString += marker.getTitle() + System.getProperty("line.separator");
        destinationString += "   Distance: " + traffic.distances.get(index).toString()  + ":"  + System.getProperty("line.separator");
        destinationString += "   Duration: " + traffic.durations.get(index).toString()  +System.getProperty("line.separator");
/*        destinationString += "   Alarm clock: " + traffic.timestoStay.get(index)  + ":" + traffic.mins.get(index) + System.getProperty("line.separator");*/
        destinationString += "   Reminders: " + traffic.reminders.get(index);

    ;
        return destinationString;
    }



    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        int rc = resultCode;
        int currentMarkerIndex = data.getIntExtra("currentMarkerIndex", -1);


        listViewContent.set(currentMarkerIndex-1, buildListViewString(currentMarkerIndex-1));
        arrayAdapter.notifyDataSetChanged();

    }

}

