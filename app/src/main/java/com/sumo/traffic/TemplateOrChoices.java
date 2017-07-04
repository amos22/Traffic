package com.sumo.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.vision.text.Line;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import android.animation.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class TemplateOrChoices extends AppCompatActivity {

ImageButton pack;
ImageButton creator;
    static int pop = 0;
    static int scroll = 0;

LinearLayout lay1 , lay2;
    static int renew = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_or_choices);

     pack = (ImageButton) findViewById(R.id.packages);

        pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop = 0;
                scroll = 1;
                traffic.button1 =2;
                traffic.button3 =2;
                Intent i = new Intent(TemplateOrChoices.this, traffic.class);
                startActivity(i);
                finish();
            }

        });
    creator = (ImageButton) findViewById(R.id.creator);
        creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll = 0;
                pop = 1;
                renew = 1;
                traffic.checklist = 1;
                traffic.button1 =1;
                traffic.button3 =1;
                Intent i = new Intent(TemplateOrChoices.this, ChoicesOfPlace.class);
                startActivity(i);
                finish();


            }
        });/*


*/

    }

}
