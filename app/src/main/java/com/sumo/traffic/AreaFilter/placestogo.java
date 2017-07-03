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

public class placestogo extends AppCompatActivity {
     ImageButton amusement, bar,bowling,cafe,camp,casino,club,gallery,mall,museum,park,spa,zoo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placestogo);

        amusement = (ImageButton)findViewById(R.id.amuse);
        amusement.setOnClickListener(new View.OnClickListener() {
            String amusement_park ="amusement_park";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,amusement_park);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Amusement Park",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=36;
                finish();
/*                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,amusement_park);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Amusement Park",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=36;
                finish();*/
            }
        });


        bar=(ImageButton)findViewById(R.id.bar);
        bar.setOnClickListener(new View.OnClickListener() {
            String bar ="bar";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,bar);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Bar",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=37;
                finish();
            }
        });

        bowling=(ImageButton)findViewById(R.id.bowl);
        bowling.setOnClickListener(new View.OnClickListener() {
            String bowling_alley ="bowling_alley";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,bowling_alley);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Bowling Alley",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=38;
                finish();
            }
        });

        cafe=(ImageButton)findViewById(R.id.cafe);
        cafe.setOnClickListener(new View.OnClickListener() {
            String cafe ="cafe";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,cafe);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Cafe",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=39;
                finish();
            }
        });

        camp=(ImageButton)findViewById(R.id.camp);
        camp.setOnClickListener(new View.OnClickListener() {
            String campground ="campground";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,campground);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Campground",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=40;
                finish();
            }
        });

        casino=(ImageButton)findViewById(R.id.casino);
        casino.setOnClickListener(new View.OnClickListener() {
            String casino ="casino";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,casino);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Casino",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=41;
                finish();
            }
        });

        club=(ImageButton)findViewById(R.id.club);
        club.setOnClickListener(new View.OnClickListener() {
            String night_club ="night_club";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,night_club);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Night club",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=42;
                finish();
            }
        });

        gallery=(ImageButton)findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            String art_gallery ="art_gallery";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,art_gallery);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Art gallery",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=43;
                finish();
            }
        });

        mall=(ImageButton)findViewById(R.id.mall);
        mall.setOnClickListener(new View.OnClickListener() {
            String shopping_mall ="shopping_mall";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,shopping_mall);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Shopping mall",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=44;
                finish();
            }
        });

        museum=(ImageButton)findViewById(R.id.museum);
        museum.setOnClickListener(new View.OnClickListener() {
            String museum ="museum";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,museum);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
              traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Museum",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=45;
finish();
            }
        });
        park=(ImageButton)findViewById(R.id.park);
        park.setOnClickListener(new View.OnClickListener() {
            String park ="park";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,park);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Park",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=46;
                finish();
            }
        });

        spa=(ImageButton)findViewById(R.id.spa);
        spa.setOnClickListener(new View.OnClickListener() {
            String spa ="spa";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,spa);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(placestogo.this,"Nearby Spa",Toast.LENGTH_LONG).show();
               traffic.GetNearbyPlacesData.reminders=47;
                finish();
            }
        });

        zoo=(ImageButton)findViewById(R.id.zoo);
        zoo.setOnClickListener(new View.OnClickListener() {
            String zoo ="zoo";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,zoo);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

                Toast.makeText(placestogo.this,"Nearby Zoo",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=47;
                finish();
            }
        });

    }



    public void prev (View view)
    {
        Intent i = new Intent (placestogo.this , PlacesFilters.class);
        startActivity(i);
        finish();

    }

    public void next (View view)
    {
        Intent i = new Intent (placestogo.this , store.class);
        startActivity(i);
        finish();
    }


}
