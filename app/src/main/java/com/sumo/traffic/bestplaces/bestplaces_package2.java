package com.sumo.traffic.bestplaces;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.compoundlayout.CompoundLayout;
import com.sumo.traffic.R;

public class bestplaces_package2 extends AppCompatActivity {

    private TextView subtitleTextView,subDesc,reminder;

    private View descriptionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestplaces_package2);

        subtitleTextView = (TextView) findViewById(R.id.subtitle);
        subDesc =(TextView)findViewById(R.id.subdes);
        descriptionLayout = findViewById(R.id.description_layout);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_1), R.string.balara,R.string.desc_balara);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_2), R.string.uptown,R.string.desc_uptown);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_3), R.string.stamaria,R.string.desc_stamaria);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_4), R.string.ateneo,R.string.desc_ateneo);
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
                    final Animation fadeOutAnimation = AnimationUtils.loadAnimation(bestplaces_package2.this, android.R.anim.fade_out);
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            subtitleTextView.setText(getString(subtitle));
                            subDesc.setText(getString(subdes));
                            descriptionLayout.startAnimation(AnimationUtils.loadAnimation(bestplaces_package2.this, android.R.anim.fade_in));
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
