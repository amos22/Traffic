package com.sumo.traffic.bestplaces;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.compoundlayout.CompoundLayout;
import com.sumo.traffic.R;

/**
 * Created by Amos on 1/21/2017.
 */
public class bestplaces_package5 extends AppCompatActivity {

    private TextView subtitleTextView,subDesc;

    private View descriptionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestplaces_package5);

        subtitleTextView = (TextView) findViewById(R.id.subtitle);
        subDesc =(TextView)findViewById(R.id.subdes);
        descriptionLayout = findViewById(R.id.description_layout);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_1), R.string.sining,R.string.desc_sining);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_2), R.string.fernwood,R.string.desc_fernwood);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_3), R.string.armed,R.string.desc_armed);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_4), R.string.mystery,R.string.desc_mystery);
        //  bindCompoundListener((CompoundLayout) findViewById(R.id.profile_5), R.string.jaouan,R.string.hihi);
    }

    /**
     * Bind compound listener.
     *
     * @param compoundLayout Compound layout.
     * @param subtitle       Subtitle to set.
     * @param subdes         Subdes to set.

     */
    private void bindCompoundListener(final CompoundLayout compoundLayout, @StringRes final int subtitle , @StringRes final int subdes) {
        compoundLayout.setOnCheckedChangeListener(new CompoundLayout.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundLayout compoundLayout, boolean checked) {
                if (checked) {
                    final Animation fadeOutAnimation = AnimationUtils.loadAnimation(bestplaces_package5.this, android.R.anim.fade_out);
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            subtitleTextView.setText(getString(subtitle));
                            subDesc.setText(getString(subdes));
                            descriptionLayout.startAnimation(AnimationUtils.loadAnimation(bestplaces_package5.this, android.R.anim.fade_in));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    descriptionLayout.startAnimation(fadeOutAnimation);
                }
            }
        });
    }

}
