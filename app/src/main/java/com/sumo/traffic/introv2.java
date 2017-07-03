package com.sumo.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Amos on 1/6/2017.
 */
public class introv2  extends AppCompatActivity{
        private static final long SPLASH_TIME=6000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.introv2);
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    Intent mainIntent=new Intent().setClass(introv2.this,TemplateOrChoices.class);
                    startActivity(mainIntent);
                    finish();
                }
            };
            Timer timer=new Timer();
            timer.schedule(task,SPLASH_TIME);
        }
}


