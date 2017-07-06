package com.sumo.traffic;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.example.compoundlayout.CompoundLayout;
import com.kyo.expandablelayout.ExpandableLayout;

import org.w3c.dom.Text;

public class ChoicesOfPackage extends Activity {
    private TextView subtitleTextView,subDesc,subtitleTextView_1,subDesc_1,subtitleTextView_2,subDesc_2,subtitleTextView_3,subDesc_3,subtitleTextView_4,subDesc_4;
    private View descriptionLayout,descriptionLayout_1,descriptionLayout_2,descriptionLayout_3,descriptionLayout_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_choices_of_package);

        subtitleTextView = (TextView) findViewById(R.id.subtitle);
        subDesc =(TextView)findViewById(R.id.subdes);
        descriptionLayout = findViewById(R.id.description_layout);

        subtitleTextView_1 = (TextView) findViewById(R.id.subtitle1);
        subDesc_1 = (TextView)findViewById(R.id.subdes1);
        descriptionLayout_1 = findViewById(R.id.description_layout1);

        subtitleTextView_2 = (TextView) findViewById(R.id.subtitle2);
        subDesc_2 = (TextView)findViewById(R.id.subdes2);
        descriptionLayout_2 = findViewById(R.id.description_layout2);

        subtitleTextView_3 = (TextView) findViewById(R.id.subtitle3);
        subDesc_3 = (TextView)findViewById(R.id.subdes3);
        descriptionLayout_3 = findViewById(R.id.description_layout3);

        subtitleTextView_4 = (TextView) findViewById(R.id.subtitle4);
        subDesc_4 = (TextView)findViewById(R.id.subdes4);
        descriptionLayout_4 = findViewById(R.id.description_layout4);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_1), R.string.artin,R.string.desc_artin,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_2), R.string.qch,R.string.desc_heritage,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_3), R.string.qmx,R.string.desc_qmx,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_4), R.string.cof,R.string.desc_cof,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_5),R.string._,R.string._ ,R.string.balara,R.string.desc_balara,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_6),R.string._,R.string._ ,R.string.uptown,R.string.desc_uptown,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_7),R.string._,R.string._ ,R.string.stamaria,R.string.desc_stamaria,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_8),R.string._,R.string._ ,R.string.ateneo,R.string.desc_ateneo,R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_9),R.string._,R.string._ ,R.string._,R.string._ ,R.string.edsa,R.string.desc_edsa,R.string._,R.string._,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_10),R.string._,R.string._,R.string._,R.string._ ,R.string.maginhwa,R.string.desc_maginhwa,R.string._,R.string._,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_11),R.string._,R.string._,R.string._,R.string._ ,R.string.church,R.string.desc_church,R.string._,R.string._,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_12),R.string._,R.string._,R.string._,R.string._ ,R.string.up,R.string.desc_up,R.string._,R.string._,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_13),R.string._,R.string._,R.string._,R.string._ ,R.string.east,R.string.desc_east,R.string._,R.string._,R.string._,R.string._);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_14),R.string._,R.string._ ,R.string._,R.string._ ,R.string._,R.string._,R.string.bayani,R.string.desc_bayani,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_15),R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._ ,R.string.zoo,R.string.desc_zoo,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_16),R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._,R.string.rita,R.string.desc_ritaa,R.string._,R.string._);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_17),R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._,R.string.pagasa,R.string.desc_pagasa,R.string._,R.string._);

        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_18),R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string.sining,R.string.desc_sining);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_19),R.string._,R.string._ ,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string.fernwood,R.string.desc_fernwood);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_20),R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string.armed,R.string.desc_armed);
        bindCompoundListener((CompoundLayout) findViewById(R.id.profile_21),R.string._,R.string._,R.string._,R.string._ ,R.string._,R.string._,R.string._,R.string._ ,R.string.mystery,R.string.desc_mystery);

        final ExpandableLayout expandableLayout = (ExpandableLayout) this.findViewById(R.id.expandablelayout);
        final ExpandableLayout expandableLayout2 = (ExpandableLayout) this.findViewById(R.id.expandablelayout2);
        final ExpandableLayout expandableLayout3 = (ExpandableLayout) this.findViewById(R.id.expandablelayout3);
        final ExpandableLayout expandableLayout4 = (ExpandableLayout) this.findViewById(R.id.expandablelayout4);
        final ExpandableLayout expandableLayout5 = (ExpandableLayout) this.findViewById(R.id.expandablelayout5);


        this.findViewById(R.id.imageview).setOnClickListener(new OnClickListener() {
            @Override
                    public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(ChoicesOfPackage.this, R.anim.button_bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
               expandableLayout.startAnimation(myAnim);
                        expandableLayout.toggleExpansion();
                    }
                });

        this.findViewById(R.id.imageview2).setOnClickListener(new OnClickListener() {
            @Override
                    public void onClick(View v) {
             Animation myAnim = AnimationUtils.loadAnimation(ChoicesOfPackage.this, R.anim.button_bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                expandableLayout2.startAnimation(myAnim);
                        expandableLayout2.toggleExpansion();
                    }
                });

        this.findViewById(R.id.imageview3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(ChoicesOfPackage.this, R.anim.button_bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                expandableLayout3.startAnimation(myAnim);
                expandableLayout3.toggleExpansion();
            }
        });

        this.findViewById(R.id.imageview4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(ChoicesOfPackage.this, R.anim.button_bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                expandableLayout4.startAnimation(myAnim);
                expandableLayout4.toggleExpansion();
            }
        });

        this.findViewById(R.id.imageview5).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(ChoicesOfPackage.this, R.anim.button_bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                expandableLayout5.startAnimation(myAnim);
                expandableLayout5.toggleExpansion();
            }
        });




    }



    /**
     * Bind compound listener.
     *
     * @param compoundLayout Compound layout.
     * @param subtitle       Subtitle to set.
     * @param subdes         Subdes to set.
     * @param subtitle_1     Subtitle_1 to set.
     * @param subdes_1       subdes_1 to set.
     * @param subtitle_2     Subtitle_1 to set.
     * @param subdes_2       subdes_1 to set.
     * @param subtitle_3     Subtitle_1 to set.
     * @param subdes_3       subdes_1 to set.
     * @param subtitle_4     Subtitle_1 to set.
     * @param subdes_4       subdes_1 to set.

     */
    private void bindCompoundListener(final CompoundLayout compoundLayout, @StringRes final int subtitle , @StringRes final int subdes,@StringRes final int subtitle_1,@StringRes final int subdes_1,@StringRes final int subtitle_2,@StringRes final int subdes_2,@StringRes final int subtitle_3,@StringRes final int subdes_3,@StringRes final int subtitle_4,@StringRes final int subdes_4) {
        compoundLayout.setOnCheckedChangeListener(new CompoundLayout.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundLayout compoundLayout, boolean checked) {
                if (checked) {
                    final Animation fadeOutAnimation = AnimationUtils.loadAnimation(ChoicesOfPackage.this, android.R.anim.fade_out);
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            subtitleTextView.setText(getString(subtitle));
                            subDesc.setText(getString(subdes));
                            subtitleTextView_1.setText(getString(subtitle_1));
                            subDesc_1.setText(getString(subdes_1));
                            subtitleTextView_2.setText(getString(subtitle_2));
                            subDesc_2.setText(getString(subdes_2));
                            subtitleTextView_3.setText(getString(subtitle_3));
                            subDesc_3.setText(getString(subdes_3));
                            subtitleTextView_4.setText(getString(subtitle_4));
                            subDesc_4.setText(getString(subdes_4));

                            descriptionLayout.startAnimation(AnimationUtils.loadAnimation(ChoicesOfPackage.this, android.R.anim.fade_in));
                            descriptionLayout_1.startAnimation(AnimationUtils.loadAnimation(ChoicesOfPackage.this, android.R.anim.fade_in));
                            descriptionLayout_2.startAnimation(AnimationUtils.loadAnimation(ChoicesOfPackage.this, android.R.anim.fade_in));
                            descriptionLayout_3.startAnimation(AnimationUtils.loadAnimation(ChoicesOfPackage.this, android.R.anim.fade_in));
                            descriptionLayout_4.startAnimation(AnimationUtils.loadAnimation(ChoicesOfPackage.this, android.R.anim.fade_in));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    descriptionLayout.startAnimation(fadeOutAnimation);
                    descriptionLayout_1.startAnimation(fadeOutAnimation);
                    descriptionLayout_2.startAnimation(fadeOutAnimation);
                    descriptionLayout_3.startAnimation(fadeOutAnimation);
                    descriptionLayout_4.startAnimation(fadeOutAnimation);
                }
            }
        });
    }






}
