package com.sumo.traffic.InfoOfPlaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sumo.traffic.R;

/**
 * Created by Amos on 12/22/2016.
 */
public class InfoOfAteneo extends AppCompatActivity {
static int open = 0;

    Button add;
    public static int select = 0;
    RelativeLayout wat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_ateneo);




        add = (Button) findViewById(R.id.ad);
 /*       if (traffic.ateneo == 1)
        {
            select = 0;
        }*/


      wat = (RelativeLayout) findViewById(R.id.hiders);
        wat.setVisibility(View.INVISIBLE);

/*
        if (ChoicesOfPlace.open == 1) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int)(width*.8),(int)(height*.8));
        }
*/

    }






    public void artadd(View view)
    {

        select = 1;
        Toast.makeText(getApplicationContext(), "You added Ateneo as One of your Destination", Toast.LENGTH_LONG).show();

        wat.setVisibility(View.VISIBLE);

    }

    public void p1(View view)
    {
        Intent i = new Intent(InfoOfAteneo.this,InfoOfMaginhawa.class);
        startActivity(i);
        finish();
    }

    public void p2(View view)
    {
        Intent i = new Intent(InfoOfAteneo.this,InfoOfQmc.class);
        startActivity(i);
        finish();

    }

    public void p3(View view)
    {
        Intent i = new Intent(InfoOfAteneo.this,InfoOfUp.class);
        startActivity(i);
        finish();


    }
}