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

public class store extends AppCompatActivity {
   ImageButton bike,book,cloth,dept,elec,furniture,hardware,jew,liq,pet,store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);



        bike=(ImageButton)findViewById(R.id.bike);
        bike.setOnClickListener(new View.OnClickListener() {
            String bicycle_store ="bicycle_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,bicycle_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Bicycle store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=25;
                finish();
            }
        });





        book=(ImageButton)findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            String book_store ="book_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,book_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Bookstore",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=26;
                finish();
            }
        });

        hardware=(ImageButton)findViewById(R.id.hardware);
        hardware.setOnClickListener(new View.OnClickListener() {
            String hardware_store ="hardware_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,hardware_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Hardware store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=27;
                finish();
            }
        });

        furniture=(ImageButton)findViewById(R.id.furniture);
        furniture.setOnClickListener(new View.OnClickListener() {
            String furniture_store ="furniture_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,furniture_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Furniture store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=28;
                finish();
            }
        });

        jew=(ImageButton)findViewById(R.id.jewel);
        jew.setOnClickListener(new View.OnClickListener() {
            String jewelry_store ="jewelry_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,jewelry_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Jewelry store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=29;
                finish();
            }
        });

        liq=(ImageButton)findViewById(R.id.liq);
        liq.setOnClickListener(new View.OnClickListener() {
            String liquor_store ="liquor_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,liquor_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Liquor store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=30;
                finish();
            }
        });

        store=(ImageButton)findViewById(R.id.store);
        store.setOnClickListener(new View.OnClickListener() {
            String store ="store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=31;
                finish();
            }
        });

        cloth=(ImageButton)findViewById(R.id.clothing);
        cloth.setOnClickListener(new View.OnClickListener() {
            String clothing_store ="clothing_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,clothing_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Clothing store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=32;
                finish();
            }
        });

        dept=(ImageButton)findViewById(R.id.dept);
        dept.setOnClickListener(new View.OnClickListener() {
            String department_store="department_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,department_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Department store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=33;
                finish();
            }
        });

        pet=(ImageButton)findViewById(R.id.pet);
        pet.setOnClickListener(new View.OnClickListener() {
            String pet_store ="pet_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,pet_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Pet store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=34;
                finish();
            }
        });

        elec=(ImageButton)findViewById(R.id.elec);
        elec.setOnClickListener(new View.OnClickListener() {
            String electronics_store ="electronics_store";
            @Override
            public void onClick(View v) {
                Log.d("onClick","Button is Clicked");
                traffic.mMap.clear();
                String url = traffic.getUrl(traffic.latitude,traffic.longitude,electronics_store);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0]=traffic.mMap;
                DataTransfer[1]=url;
                Log.d("onClick",url);
                traffic.GetNearbyPlacesData getNearbyPlacesData = new traffic.GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(store.this,"Nearby Electronics store",Toast.LENGTH_LONG).show();
                traffic.GetNearbyPlacesData.reminders=35;
                finish();
            }
        });
    }

    public void prev (View view)
    {
        Intent i = new Intent(store.this , placestogo.class);
        startActivity(i);
           finish();
    }

    public void next (View view)
    {
        Intent i = new Intent(store.this , usersneed.class);
        startActivity(i);
        finish();
    }

}
