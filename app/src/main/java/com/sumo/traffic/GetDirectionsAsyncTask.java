package com.sumo.traffic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;

/**
 * Created by kkyb
 */


/**
 * The role of this AsyncTask is to request for routes in a background thread.
 * To do this I use the Java Client for Google Maps Services
 * (reference: https://github.com/googlemaps/google-maps-services-java)
 * Imported in the project by adding "compile 'com.google.maps:google-maps-services:0.1.17'"
 * in app module gradle build
 */

public class GetDirectionsAsyncTask extends AsyncTask<LatLng, Void, DirectionsResult> {

    //Declaration of a GeoApiContext used in formulating requests using the DirectionApi client
    //library
    private GeoApiContext geoApiContext;
    private Context context;
    private ProgressDialog progressDialog;
    private boolean isReloading = false;

    //Instantiation of the GeoApiContext in the constructor
    public GetDirectionsAsyncTask(GeoApiContext geoApiContext, Context context, boolean isReloading){
        this.geoApiContext = geoApiContext;
        this.context = context;
        this.isReloading = isReloading;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!isReloading){
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Fetching route, Please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    //Do in background execute a DirectionAPi request for routes.
    //Has two argument, the LatLng location of the origin and destination
    @Override
    protected DirectionsResult doInBackground(LatLng... params) {
        try {
            return DirectionsApi.newRequest(geoApiContext)
                    .origin(params[0])
                    .destination(params[1])
                    .alternatives(true)
                    .optimizeWaypoints(true)
                    .await();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProgressDialog getProgressDialog(){
        return this.progressDialog;
    }

    public boolean getIsReloading(){
        return this.isReloading;
    }
}
