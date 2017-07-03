package com.sumo.traffic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PlacesFilters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_filters);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int w = dm.widthPixels;
        int h = dm.heightPixels;

        getWindow().setLayout((int)(w * .8) , (int)(h * .8));

        ImageButton btnRestaurant = (ImageButton) findViewById(R.id.air);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            String Restaurant = "airport";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, Restaurant);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
      /*          traffic.conv = traffic.PROXIMITY_RADIUS.getText().toString();

                traffic.rads = Integer.parseInt(traffic.conv);*/

                Toast.makeText(PlacesFilters.this,"Nearby Airport", Toast.LENGTH_LONG).show();
                traffic.reminder = 2;

            }
        });



        ImageButton btnbank = (ImageButton) findViewById(R.id.bus);
        btnbank.setOnClickListener(new View.OnClickListener() {
            String Bank = "bus";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, Bank );
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(PlacesFilters.this,"Nearby Bus", Toast.LENGTH_LONG).show();
                traffic.reminder = 3;
            }
        });


        ImageButton btnHospital = (ImageButton) findViewById(R.id.car);
        btnHospital.setOnClickListener(new View.OnClickListener() {
            String Hospital = "car";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, Hospital);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(PlacesFilters.this,"Nearby Car Rental", Toast.LENGTH_LONG).show();
                traffic.reminder = 4;
            }
        });

        ImageButton train = (ImageButton) findViewById(R.id.train);
        train.setOnClickListener(new View.OnClickListener() {
            String School = "taxi";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, School);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(PlacesFilters.this,"Nearby Train Station", Toast.LENGTH_LONG).show();
                traffic.reminder = 5;
            }
        });
        ImageButton transit = (ImageButton) findViewById(R.id.transit);
        transit.setOnClickListener(new View.OnClickListener() {
            String train = "taxi";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, train);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(PlacesFilters.this,"Nearby Transit", Toast.LENGTH_LONG).show();
                traffic.reminder = 6;
            }
        });
        ImageButton btnSchool = (ImageButton) findViewById(R.id.taxi);
        btnSchool.setOnClickListener(new View.OnClickListener() {
            String transit = "taxi";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, transit);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(PlacesFilters.this,"Nearby Taxi Stand", Toast.LENGTH_LONG).show();
                traffic.reminder = 7;
            }
        });
    }
}
