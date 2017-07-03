package com.sumo.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;

import java.util.ArrayList;

/**
 * Created by kixkikx on 12/20/2016.
 */
public class ChoicesOfPlace  extends AppCompatActivity {
    SearchView sv;
    RatingBar rb;
    public static int open = 0;
    FloatingActionButton fab;
    static int hidethis = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofactivity);
        // rb= (RatingBar)findViewById(R.id.ratingBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         fab = (FloatingActionButton) findViewById(R.id.fab);

        if (hidethis == 1)
        {
            fab.setVisibility(View.INVISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               Intent i = new Intent (ChoicesOfPlace.this, traffic.class);
                startActivity(i);
                hidethis = 1;

        }
        });
        sv = (SearchView) findViewById(R.id.mSearch);
        RecyclerView rv = (RecyclerView) findViewById(R.id.myRecycler);
        //SET ITS PROPETRIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        //ADAPTER
        final MyAdapter adapter = new MyAdapter(this, getPlayers());
        rv.setAdapter(adapter);
        //SEARCH
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });

/*
        if (open == 1) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int)(width*.8),(int)(height*.8));
        }*/

    }

    //ADD PLAYERS TO ARRAYLIST
    private ArrayList<Places> getPlayers() {
        ArrayList<Places> movies = new ArrayList<>();
        Places p = new Places();
        p.setName("Quezon Memorial Circle");
        p.setPos("R-7 Diliman, Quezon City, Metro Manila    ");
        p.setImg(R.drawable.qc);
        movies.add(p);
        p=new Places();
        p.setName("Ninoy Aquino Parks and Wildlife Center");
        p.setPos("Elliptical Road,Diliman,Quezon City,Metro Manila");
        p.setImg(R.drawable.wildlife);
        movies.add(p);
        p=new Places();
        p.setName("La Mesa Dam and Reservoir");
        p.setPos("Greater Lagro, Quezon City, Philippines    ");
        p.setImg(R.drawable.lamesa);
        movies.add(p);
        p=new Places();
        p.setName("Jorge B.Vargas Museum and Filipiniana Research Center");
        p.setPos("Roxas Ave,Diliman,Quezon City,Metro Manila");
        p.setImg(R.drawable.vargas);
        movies.add(p);
        p=new Places();
        p.setName("EDSA Shrine");
        p.setPos("Ortigas Center, Quezon City, Metro Manila");
        p.setImg(R.drawable.edsashrine);
        movies.add(p);
        p=new Places();
        p.setName("Art In Island");
        p.setPos("175 15th Ave.,Brgy.Socorro,Cubao,Quezon City,1109 Metro Manila");
        p.setImg(R.drawable.artin);
        movies.add(p);
        p=new Places();
        p.setName("Circle of Fun");
        p.setPos("Diliman, Quezon City, Metro Manila            ");
        p.setImg(R.drawable.foc);
        movies.add(p);
        p=new Places();
        p.setName("Bantayog ng mga Bayani Center");
        p.setPos("Bantayog Rd,Diliman,Quezon City,Metro Manila");
        p.setImg(R.drawable.bay);
        movies.add(p);
        p=new Places();
        p.setName("People Power Monument");
        p.setPos("White Plains Ave,Quezon City,Metro Manila");
        p.setImg(R.drawable.monument);
        movies.add(p);
        p=new Places();
        p.setName("Ateneo Art Gallery");
        p.setPos("Ground Floor,Rizal Library,University Rd,Diliman,Quezon City,Metro Manila");
        p.setImg(R.drawable.ateneo);
        movies.add(p);
        p=new Places();
        p.setName("La Mesa Watershed Reservation");
        p.setPos("4332, Quezon City Dr, Novaliches, Quezon City, Metro Manila");
        p.setImg(R.drawable.lamwat);
        movies.add(p);
        p=new Places();
        p.setName("Parish of the Holy Sacrifice");
        p.setPos("Laurel Ave,UP Campus Diliman,Quezon City,1101 Metro Manila");
        p.setImg(R.drawable.parish);
        movies.add(p);
        p=new Places();
        p.setName("Eastwood City");
        p.setPos("E. Rodriguez Jr. Ave, Quezon City, Manila, 1110 Metro Manila");
        p.setImg(R.drawable.eastwood);
        movies.add(p);
        p=new Places();
        p.setName("Maginhawa Food Park");
        p.setPos("91 Maginhawa, Diliman, Lungsod Quezon, 1101 Kalakhang Maynila");
        p.setImg(R.drawable.maginhawa);
        movies.add(p);
        p=new Places();
        p.setName("UP-Ayala Land Technohub");
        p.setPos("Commonwealth Avenue, Barangay U.P. Campus, Quezon City.");
        p.setImg(R.drawable.ayala);
        movies.add(p);


        return movies;

    }
}
