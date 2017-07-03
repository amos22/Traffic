package com.sumo.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sumo.traffic.QcPackage.welcome_qcpackage1;

public class PackageInfo extends AppCompatActivity {
TextView packagenumber,p1,p2,p3,p4,p5,aduration,acost,goodfor;
    Button vplaces;
    int vp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_info);
        packagenumber = (TextView)findViewById(R.id.packagenumber);
        p1 = (TextView) findViewById(R.id.p1);
        p2 = (TextView) findViewById(R.id.p2);
        p3 = (TextView) findViewById(R.id.p3);
        p4 = (TextView) findViewById(R.id.p4);
        p5 = (TextView) findViewById(R.id.p5);
        vplaces = (Button) findViewById(R.id.vplaces);

        aduration = (TextView) findViewById(R.id.duration);
        acost = (TextView) findViewById(R.id.cost);
        goodfor = (TextView) findViewById(R.id.goodfor);


 /*       if(traffic.qp1 == 1)
        {
            packagenumber.setText("1");
            p1.setText("Art In Island");
            p2.setText("Quezon City Heritage");
            p3.setText("Quezon City Xperience");
            p4.setText("Circle Of Fun");
            p5.setText("Maginhawa STREAT");

            aduration.setText("10 Hours/day(From 10am-8pm)");
            acost.setText("₱4500 - ₱7500");
            goodfor.setText("4 - 6 Person");
            vp = 1;

        }

        if(traffic.qp1 == 2)
        {


        }
*/


    }

    public void vplaces(View view)
    {
        if(vp ==1)
        {
            Intent i = new Intent(PackageInfo.this , welcome_qcpackage1.class);
            startActivity(i);
        }

    }





}
