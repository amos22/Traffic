package com.sumo.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class StreetMapYesOrNo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_map_yes_or_no);     DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.55));
    }

    public void yes(View view)
    {
        Intent i = new Intent(StreetMapYesOrNo.this , StreetMap.class);
        startActivity(i);
    }

    public void no(View view)
    {
        finish();
    }
}
