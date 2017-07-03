package com.sumo.traffic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class splashs extends Activity  implements Animation.AnimationListener {
    ProgressBar progressBar;
    int progress = 0;
    Handler h = new Handler();
    ImageView imagers;
    ImageView tara;
    ImageView imagers3;
    // Animation
    Animation animFadein,bounce,bouncers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screenz);


        imagers = (ImageView) findViewById(R.id.imageView3);
        tara= (ImageView) findViewById(R.id.imageView2);

        imagers3 = (ImageView) findViewById(R.id.imageView4);




        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);

        bounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    progress += 20;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            if (progress == progressBar.getMax()) {
                                /*progressBar.setVisibility(View.VISIBLE);*/
                                Intent i = new Intent(getApplicationContext(),TemplateOrChoices.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();

        animFadein.setAnimationListener(this);
        imagers.startAnimation(animFadein);
        tara.startAnimation(bounce);
        imagers3.startAnimation(bounce);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
