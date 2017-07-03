package com.sumo.traffic.AreaFilter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sumo.traffic.R;
import com.sumo.traffic.traffic;

public class usersneed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersneed);








      Button atm   = (Button) findViewById(R.id.atmachine);
        atm.setOnClickListener(new View.OnClickListener() {
            String atm = "atm";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, atm );
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(usersneed.this,"Nearby Bus", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 11;
                finish();
            }
        });


        Button police = (Button) findViewById(R.id.pol);
        police.setOnClickListener(new View.OnClickListener() {
            String pol = "police";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude, traffic.longitude, pol);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Police Station", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 12;
                finish();
            }
        });

        Button firestation = (Button) findViewById(R.id.fire);
        firestation.setOnClickListener(new View.OnClickListener() {
            String fire_station = "fire_station";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, fire_station);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Fire Station", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 13;
                finish();
            }
        });

        Button hos = (Button) findViewById(R.id.hos);
        hos.setOnClickListener(new View.OnClickListener() {
            String hospital = "hospital";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, hospital);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Hospital", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 14;
                finish();
            }
        });

        Button phar = (Button) findViewById(R.id.phar);
        phar.setOnClickListener(new View.OnClickListener() {
            String pharmacy = "pharmacy";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, pharmacy);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Hospital", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 15;
                finish();
            }
        });

        Button parkings = (Button) findViewById(R.id.parkings);
        parkings.setOnClickListener(new View.OnClickListener() {
            String parking = "parking";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, parking);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Parking ", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 16;
                finish();
            }
        });

        Button delivery = (Button) findViewById(R.id.del);
        delivery.setOnClickListener(new View.OnClickListener() {
            String meal_delivery = "meal_delivery";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, meal_delivery);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Meal Delivery ", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 17;
                finish();
            }
        });

        Button salon = (Button) findViewById(R.id.hair);
        salon.setOnClickListener(new View.OnClickListener() {
            String hair_care = "hair_care";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, hair_care);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Salon Hair Care ", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 18;
                finish();
            }
        });

        Button gym = (Button) findViewById(R.id.gym);
        gym.setOnClickListener(new View.OnClickListener() {
            String gym = "gym";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, gym);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Gym ", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 19;
                finish();
            }
        });

        Button carwash = (Button) findViewById(R.id.carwash);
        carwash.setOnClickListener(new View.OnClickListener() {
            String car_wash = "car_wash";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, car_wash);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Car Wash ", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 20;
                finish();

            }
        });

        Button carpr = (Button) findViewById(R.id.carepr);
        carpr.setOnClickListener(new View.OnClickListener() {
            String car_repair = "car_repair";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, car_repair);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Car Repair ", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 21;
                finish();
            }
        });



        Button laundry = (Button) findViewById(R.id.laundry);
        laundry.setOnClickListener(new View.OnClickListener() {
            String laundry= "laundry";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, laundry);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Laundry", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 23;
                finish();
            }
        });

        Button gas = (Button) findViewById(R.id.gas);
        gas.setOnClickListener(new View.OnClickListener() {
            String gas= "gas_station";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                traffic.mMap.clear();

                String url = traffic.getUrl(traffic.latitude, traffic.longitude, gas);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = traffic.mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(usersneed.this,"Nearby Gas Station", Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders  = 24;
                finish();
            }
        });





    }

public void next (View view)
{
    Intent i = new Intent(usersneed.this , agency.class);
    startActivity(i);
    finish();
}

    public void prev (View view)
    {
        Intent i = new Intent(usersneed.this , store.class);
        startActivity(i);
        finish();
    }
}
