package com.sumo.traffic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;




public class SummaryOfTour extends AppCompatActivity {
    Context context;

    ImageView imageView ,imageView1,imageView2,imageView3,imageView4,imageView5;


    static int ms1,ms2,ms3,ms4,ms5;
    static int qs1,qs2,qs3,qs4,qs5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_of_tour);

        imageView = (ImageView) findViewById(R.id.img1);
        imageView1 = (ImageView) findViewById(R.id.img2);
        imageView2 = (ImageView) findViewById(R.id.img3);
        imageView3 = (ImageView) findViewById(R.id.img4);
        imageView4 = (ImageView) findViewById(R.id.img5);
        imageView5 = (ImageView) findViewById(R.id.img6);



if(ms1 ==1) {
    Glide.with(this)
            .load(R.drawable.mp1)
            .override(1000,1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.mp2)
            .override(1000,1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.mp3)
            .override(1000,1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.mp4)
            .override(1000,1690)

            .into(imageView3);
    Glide.with(this)
            .load(R.drawable.bg)
            .override(1000,1800)

            .into(imageView4);

/*    Picasso.with(this)
            .load(R.drawable.mp1)
            .resize(1000, 1690)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView);


    Picasso.with(this)
            .load(R.drawable.mp2)
            .resize(1000, 1690)
            .into(imageView1);

    Picasso.with(this)
            .load(R.drawable.mp3)
            .resize(1000, 1690)
            .into(imageView2);

    Picasso.with(this)
            .load(R.drawable.mp4)
            .resize(1000, 1690)
            .into(imageView3);

    Picasso.with(this)
            .load(R.drawable.bg)
            .resize(1000, 1700)
            .into(imageView4);*/

}
        else if (ms2 ==1) {
    Glide.with(this)
            .load(R.drawable.mp11)
            .override(1000,1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.mp22)
            .override(1000,1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.mp33)
            .override(1000,1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.mp44)
            .override(1000,1690)

            .into(imageView3);
    Glide.with(this)
            .load(R.drawable.bg)
            .override(1000,1800)

            .into(imageView4);
          /*  Picasso.with(this)
                    .load(R.drawable.mp11)
                    .resize(1000, 1690)
                    .into(imageView);

            Picasso.with(this)
                    .load(R.drawable.mp22)
                    .resize(1000, 1690)
                    .into(imageView1);

            Picasso.with(this)
                    .load(R.drawable.mp33)
                    .resize(1000, 1690)
                    .into(imageView2);

            Picasso.with(this)
                    .load(R.drawable.mp44)
                    .resize(1000, 1690)
                    .into(imageView3);

            Picasso.with(this)
                    .load(R.drawable.bg)
                    .resize(1000, 1700)
                    .into(imageView4);*/

        }

else if (ms3 ==1) {
    Glide.with(this)
            .load(R.drawable.mp111)
            .override(1000,1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.mp222)
            .override(1000,1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.mp333)
            .override(1000,1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.mp444)
            .override(1000,1690)

            .into(imageView3);
    Glide.with(this)
            .load(R.drawable.bg)
            .override(1000,1800)

            .into(imageView4);

}

else if (ms4 ==1) {
    Glide.with(this)
            .load(R.drawable.mp1111)
            .override(1000,1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.mp2222)
            .override(1000,1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.mp5555)
            .override(1000,1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.mp7777)
            .override(1000,1690)

            .into(imageView3);
    Glide.with(this)
            .load(R.drawable.mp66666)
            .override(1000,1690)

            .into(imageView4);



}

else if (qs2 ==1) {
    Glide.with(this)
            .load(R.drawable.sum_balara)
            .override(1000, 1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.sum_uptown)
            .override(1000, 1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.sum_strada)
            .override(1000, 1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.sum_ateneo)
            .override(1000, 1690)
            .into(imageView3);

}

else if (qs1 ==1) {
    Glide.with(this)
            .load(R.drawable.sum_heritage)
            .override(1000, 1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.sum_art)
            .override(1000, 1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.sum_qcx)
            .override(1000, 1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.sum_qmc)
            .override(1000, 1690)
            .into(imageView3);
    Glide.with(this)
            .load(R.drawable.sum_circle)
            .override(1000, 1690)
            .into(imageView4);
    Glide.with(this)
            .load(R.drawable.sum_mag)
            .override(1000, 1690)
            .into(imageView5);

}
else if (qs3 ==1) {
    Glide.with(this)
            .load(R.drawable.sum_edsa)
            .override(1000, 1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.sum_maginhawa)
            .override(1000, 1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.sum_gesu)
            .override(1000, 1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.sum_up)
            .override(1000, 1690)
            .into(imageView3);
    Glide.with(this)
            .load(R.drawable.eastwood)
            .override(1000, 1690)
            .into(imageView3);
}
else if (qs4 ==1) {
    Glide.with(this)
            .load(R.drawable.sum_bayani)
            .override(1000, 1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.sum_wildlife)
            .override(1000, 1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.sum_sta)
            .override(1000, 1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.sum_pagasa)
            .override(1000, 1690)
            .into(imageView3);

}

else if (qs5 ==1) {
    Glide.with(this)
            .load(R.drawable.sum_fernwood)
            .override(1000, 1690)

            .into(imageView);
    Glide.with(this)
            .load(R.drawable.sum_armed)
            .override(1000, 1690)

            .into(imageView1);
    Glide.with(this)
            .load(R.drawable.sum_sining)
            .override(1000, 1690)

            .into(imageView2);
    Glide.with(this)
            .load(R.drawable.sum_mystery)
            .override(1000, 1690)
            .into(imageView3);

}




    }
}
