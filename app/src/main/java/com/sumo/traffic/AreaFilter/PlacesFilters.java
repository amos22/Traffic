package com.sumo.traffic.AreaFilter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sumo.traffic.R;
import com.sumo.traffic.traffic;

public class PlacesFilters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_filters);





        ImageButton btnRestaurant = (ImageButton) findViewById(R.id.air);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            String airport = "airport";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, airport);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
      /*          traffic.conv = traffic.PROXIMITY_RADIUS.getText().toString();

                traffic.rads = Integer.parseInt(traffic.conv);*/

                Toast.makeText(PlacesFilters.this,"Nearby Airport", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 2;


                finish();


            }
        });



        ImageButton btnbank = (ImageButton) findViewById(R.id.bus);
        btnbank.setOnClickListener(new View.OnClickListener() {
            String bus = "bus_station";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, bus );
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(PlacesFilters.this,"Nearby Bus", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 3;
                finish();
            }
        });


        ImageButton btnHospital = (ImageButton) findViewById(R.id.car);
        btnHospital.setOnClickListener(new View.OnClickListener() {
            String car = "car_rental";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, car);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(PlacesFilters.this,"Nearby Car Rental", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 4;
                finish();
            }
        });

        ImageButton taxi = (ImageButton) findViewById(R.id.taxi);
        taxi.setOnClickListener(new View.OnClickListener() {
            String taxi = "taxi_stand";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, taxi);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(PlacesFilters.this,"Nearby Taxi Stand", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 5;
                finish();
            }
        });
        ImageButton transit = (ImageButton) findViewById(R.id.train);
        transit.setOnClickListener(new View.OnClickListener() {
            String train = "train_station";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, train);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(PlacesFilters.this,"Nearby Train Station", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 6;
                finish();
            }
        });
        ImageButton btnSchool = (ImageButton) findViewById(R.id.transit);
        btnSchool.setOnClickListener(new View.OnClickListener() {
            String transit = "transit_station";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, transit);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(PlacesFilters.this,"Nearby Transit", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 7;
                finish();
            }
        });


    }

    public void next (View view)
    {
        Intent i = new Intent(PlacesFilters.this , placestogo.class);
                startActivity(i);
        finish();

    }

    public void prev (View view)
    {
        Intent i = new Intent(PlacesFilters.this , agency.class);
        startActivity(i);
        finish();
    }



}
