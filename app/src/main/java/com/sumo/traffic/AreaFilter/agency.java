package com.sumo.traffic.AreaFilter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sumo.traffic.R;
import com.sumo.traffic.traffic;

public class agency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);


        Button reals = (Button) findViewById(R.id.real);
        reals.setOnClickListener(new View.OnClickListener() {
            String real_estate_agency = "real_estate_agency";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, real_estate_agency);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
              traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(agency.this,"Nearby Real Estate Agency", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders = 8;
               finish();
            }
        });

        ImageButton insur = (ImageButton) findViewById(R.id.insurance);
        insur.setOnClickListener(new View.OnClickListener() {
            String insurance_agency = "insurance_agency";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, insurance_agency);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(agency.this,"Nearby Insurance Agency", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders = 9;
                finish();
            }
        });

        ImageButton travel = (ImageButton) findViewById(R.id.travel);
        travel.setOnClickListener(new View.OnClickListener() {
            String travel_agency = "travel_agency";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, travel_agency);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(agency.this,"Nearby Travel Agency", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders = 10;
                finish();
            }
        });

    }



    public void prev (View view)
    {
        Intent i = new Intent(agency.this ,  usersneed.class);
        startActivity(i);
        finish();
    }

    public void next (View view)
    {
        Intent i = new Intent(agency.this ,  PlacesFilters.class);
        startActivity(i);
        finish();
    }
}
