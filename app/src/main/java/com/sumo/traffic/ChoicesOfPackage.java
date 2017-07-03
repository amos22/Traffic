package com.sumo.traffic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.kyo.expandablelayout.ExpandableLayout;

public class ChoicesOfPackage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_choices_of_package);

        final ExpandableLayout expandableLayout = (ExpandableLayout) this
                .findViewById(R.id.expandablelayout);


        final ExpandableLayout expandableLayout2 = (ExpandableLayout) this
                .findViewById(R.id.expandablelayout2);

        this.findViewById(R.id.imageview).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandableLayout.toggleExpansion();
                    }
                });


        this.findViewById(R.id.imageview2).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandableLayout2.toggleExpansion();
                    }
                });
    }
}
