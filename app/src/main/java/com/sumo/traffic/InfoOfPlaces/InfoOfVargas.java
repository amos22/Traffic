package com.sumo.traffic.InfoOfPlaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sumo.traffic.R;

/**
 * Created by Amos on 12/22/2016.
 */
public class InfoOfVargas extends AppCompatActivity {
    public static int select = 0;
    RelativeLayout wat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_vargas);
 /*       if (ChoicesOfPlace.open == 1) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int)(width*.8),(int)(height*.8));
        }
*/
        wat = (RelativeLayout) findViewById(R.id.hiders);
        wat.setVisibility(View.INVISIBLE);

    }
    public void artadd(View view)
    {
        select = 1;
        Toast.makeText(getApplicationContext(), "You added this as One of your Destination", Toast.LENGTH_LONG).show();
    }


    public void p1(View view)
    {
        Intent i = new Intent(InfoOfVargas.this,InfoOfMaginhawa.class);
        startActivity(i);

    }

    public void p2(View view)
    {
        Intent i = new Intent(InfoOfVargas.this,InfoOfAteneo.class);
        startActivity(i);


    }

    public void p3(View view)
    {
        Intent i = new Intent(InfoOfVargas.this,InfoOfCOF.class);
        startActivity(i);



    }








}