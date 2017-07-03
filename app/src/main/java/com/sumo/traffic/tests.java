package com.sumo.traffic;
/**
 * Created by kixkikx on 12/20/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Amos on 12/17/2016.
 */
public class tests extends AppCompatActivity{
    static ImageView img;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testt);
        img = (ImageView)findViewById(R.id.imageView);


    }
}
