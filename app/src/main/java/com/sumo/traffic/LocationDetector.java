package com.sumo.traffic;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by kkyb.
 */

/**
 * The role of this class is request for user location updates
 * In short when the google api client connects, a check for location settings is done. If anything
 * needs to be handle, the user is being asked to handle that. When all location settings are ok,
 * a request for location update is done using a location request with specific criteria. On location
 * update, a call back function is called at the level of the activity using the location detector
 * (this activity is also a LocationListener)
 * Most of the work done here is with the help of the google play services location library
 * (imported in module app gradle by "compile 'com.google.android.gms:play-services-location:9.6.1'"
 */
public class LocationDetector implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final int REQUEST_CHECK_SETTINGS = 101;
    public static final int REQUEST_CHECK_PERMISSIONS = 102;


    private GoogleApiClient googleApiClient = null;

    //The activity which uses the location update
    private Activity activity;

    //The activity which uses the location update which is also the location listener
    private LocationListener locationListener;

    public LocationDetector(Activity activity, LocationListener locationListener){

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        this.activity = activity;
        this.locationListener = locationListener;
    }

    //Called when the connection to the GoogleApiClient is suspended
    @Override
    public void onConnectionSuspended(int i) {
        Log.d("testing", "connection suspended");
        Toast.makeText(activity, "Connection suspended. Unable to connect to" +
                "Google Play Services", Toast.LENGTH_LONG).show();
    }

    //Called when the connection to the GoogleApiClient fails
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("testing", "connection failed");
        Toast.makeText(activity, "Connection Failed. Unable to connect to Google" +
                "Play Services", Toast.LENGTH_LONG).show();
    }

    //Used in the creation of location requests using specific criteria. returns a LocationRequest
    private LocationRequest createLocationRequest() {
        Log.d("testing", "creating location request");
        LocationRequest locationRequest = new LocationRequest();

        //Defines the accuracy of th localisation
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //Defines the interval between each location requests
        locationRequest.setInterval(20000);

        //Defines the fastest interval to accept localisation results
        locationRequest.setFastestInterval(10000);
        return locationRequest;
    }

    //Used in requesting for location updates using a LocationRequest
    private void requestLocationUpdate(LocationRequest locationRequest) {

        Log.d("testing", "requesting location update");

        //Check is necessary location permissions have been granted by the user
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //Request for location updates using the FusedLocationApi
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, locationListener).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Log.e("location test", status.toString());
            }
        });
    }

    //Handle the location settings results
    private void handleLocationSettings(LocationSettingsResult result) {
        Log.d("testing", "handling location settings");
        final Status status = result.getStatus();
        switch (status.getStatusCode()) {

            case LocationSettingsStatusCodes.SUCCESS:
                Log.e("testing", "no settings resolution required");
                requestLocationUpdate(createLocationRequest());
                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    Toast.makeText(activity, "Connection suspended. Will not be" +
                            "able to auto-detect location", Toast.LENGTH_LONG).show();
                }
                break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                Toast.makeText(activity, "Please check your GPS or internet settings," +
                        "then try again", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //Build a location setting request dialog using a location settings request builder and display
    //it to the user
    private void checkLocationSettings(LocationRequest locationRequest) {
        Log.d("testing", "checking location settings");
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                handleLocationSettings(locationSettingsResult);
            }
        });
    }

    //Called by the activity using the location detector to receive location settings resolution
    // results
    public void onSettingsResolutionResult(int resultCode) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                requestLocationUpdate(createLocationRequest());
                break;
            case Activity.RESULT_CANCELED:
                googleApiClient.disconnect();
                Toast.makeText(activity, "Traffic Will not be able to get your location and will" +
                        " not be able to function properly", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //Called when the GoogleClientApi connects successfully
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("testing", "connected to google api");
        checkLocationSettings(createLocationRequest());
    }

    //Start location detection process
    public void startLocationDetection() {

        //Check for permissions and request for permissions to granted if necessary
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            //If permission request has been done already, explain to the user why the app needs
            //this permission then request for permission to be granted again
            //else just request for permission to be granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Toast.makeText(activity, "Traffic need to be able to get your location and in" +
                        " order to be able to function properly", Toast.LENGTH_LONG).show();

                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQUEST_CHECK_PERMISSIONS);

            } else {

                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQUEST_CHECK_PERMISSIONS);
            }
            //If all permissions have been granted, make sure the google api client is disconnected
            //then connect it
        } else {
            if (googleApiClient != null) {
                googleApiClient.disconnect();
                googleApiClient.connect();
            } else {
                Toast.makeText(activity, "Unable to access google" +
                        "play services", Toast.LENGTH_LONG).show();
            }
        }
    }

    //stop location updates. called by the activity using the location detector when necessary
    public void stopLocationDetection(){
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient, locationListener);
        googleApiClient.disconnect();
    }

}