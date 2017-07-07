package com.sumo.traffic;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.model.DirectionsRoute;
import com.kyo.expandablelayout.ExpandableLayout;
import com.sumo.traffic.InfoOfPlaces.InfoOfArt;
import com.sumo.traffic.InfoOfPlaces.InfoOfAteneo;
import com.sumo.traffic.InfoOfPlaces.InfoOfBayani;
import com.sumo.traffic.InfoOfPlaces.InfoOfCOF;
import com.sumo.traffic.InfoOfPlaces.InfoOfDam;
import com.sumo.traffic.InfoOfPlaces.InfoOfEast;
import com.sumo.traffic.InfoOfPlaces.InfoOfEdsa;
import com.sumo.traffic.InfoOfPlaces.InfoOfMaginhawa;
import com.sumo.traffic.InfoOfPlaces.InfoOfNinoy;
import com.sumo.traffic.InfoOfPlaces.InfoOfParish;
import com.sumo.traffic.InfoOfPlaces.InfoOfPeople;
import com.sumo.traffic.InfoOfPlaces.InfoOfQmc;
import com.sumo.traffic.InfoOfPlaces.InfoOfUp;
import com.sumo.traffic.InfoOfPlaces.InfoOfVargas;
import com.sumo.traffic.InfoOfPlaces.InfoOfWatershed;
import com.sumo.traffic.QcPackage.welcome_qcpackage1;
import com.sumo.traffic.QcPackage.welcome_qcpackage2;
import com.sumo.traffic.QcPackage.welcome_qcpackage3;
import com.sumo.traffic.QcPackage.welcome_qcpackage4;
import com.sumo.traffic.QcPackage.welcome_qcpackage5;
import com.sumo.traffic.bestplaces.bestplaces_package1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class traffic extends FragmentActivity implements LocationListener, OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    public static LinkedList<MarkerOptions> mList = new LinkedList<MarkerOptions>();
    public static LinkedList<LatLng> points = new LinkedList<LatLng>();
    public static LinkedList<LatLng> place = new LinkedList<LatLng>();
    public static LinkedList<String> placename = new LinkedList<String>();
    public static LinkedList<Marker> markers = new LinkedList<Marker>();
    public static LinkedList<String> distances = new LinkedList<String>();
    public static LinkedList<String> durations = new LinkedList<String>();
    public static LinkedList<String> timestoStay = new LinkedList<String>();
    public static LinkedList<String> mins = new LinkedList<String>();
    public static LinkedList<String> reminders = new LinkedList<String>();
    public static LinkedList<String> listofturns = new LinkedList<String>();
    static String placeId;
    LinkedList<MarkerOptions> placeMarkers = new LinkedList<MarkerOptions>();
    static BitmapDescriptor[] icons = null;
    String[] loadingToasts = null;
    static int streetmapenabled = 0;
    private Button rout;
    private Button traffic;
    private Button altroute;
    static TextView pointview, durationview, distanceview;
    static LatLng latLng;
    LatLng oldplace;
    Polyline line, bigline;
    Timer t = new Timer();
    String res;

    boolean clear = false;
    int rn = 0, rl;

    ListView listViewz;

    String ins = "html_instructions";
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    static CoordinatorLayout main;

    public static double latitude;
    public static double longitude;

    static Integer reminder = 0;
    public static GoogleMap mMap;

    PlaceAutocompleteFragment autocompleteFragment;

    static int placesnumber;

    static LinearLayout hideme;
    static Button selected;

    static int ekis;

    static int art = 0;
    static int ateneo = 0;
    static LatLng streetmap;

    static int button3;
    static int button1;

    static Button b1, b2, b3;
    private BottomNavigationView bottomNavigationView;


    static int qp1, qp2, qp3, qp4, qp5;

static int packs , itc;

    static Button nav;

   com.github.clans.fab.FloatingActionButton fab1,fab2,fab3;
    com.github.clans.fab.FloatingActionMenu menured;

    static int checklist = 0;
    float dX;
    float dY;
    int lastAction;

    static String place_id = null;
    String placeID = "";
    List<String> placesId = new ArrayList<String>();

    JSONArray turns;
    private final int interval = 2000; // 1 Second
    private Handler handler = new Handler();
    ArrayList<JSONArray> listOfRouteArray = new ArrayList<>();
    private ArrayList<Integer> listOfIndicesOfCurrentRoutes = new ArrayList<>();
    private ArrayList<Polyline> polylines = new ArrayList<>();
    private ArrayList<DirectionsRoute[]> listOfSetOfRoutesOfPolylines = new ArrayList<>();

    final BottomSheetDialogFragment myBottomSheet = MyBottomSheetDialogFragment.newInstance("Modal Bottom Sheet");

    JSONObject t2;
    String get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final ExpandableLayout expandableLayout = (ExpandableLayout) this.findViewById(R.id.expandablelayout);
        listViewz = (ListView) findViewById(R.id.list_item);

       Runnable runnable = new Runnable(){
            public void run() {
             selected();
            }
        };

        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);


fab1 = (  com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);

        fab2 = (  com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);


        fab3 = (  com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab3);
        menured = (  com.github.clans.fab.FloatingActionMenu) findViewById(R.id.menu_red);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(traffic.this, "Test1", Toast.LENGTH_SHORT).show();
            }
        });



        if (packs == 1 )
        {
            fab2.setLabelText("Summary of Tour");

        }
        else if (itc == 1)
        {
            fab2.setLabelText("Add more destination");
        }

        fab1.setLabelText("Enable StreetMap");


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (streetmapenabled == 0) {
                    Toast.makeText(traffic.this, "StreetMap Enabled", Toast.LENGTH_SHORT).show();
                    Toast.makeText(traffic.this, "Click on any marker", Toast.LENGTH_SHORT).show();
                    streetmapenabled = 1;
                    fab1.setLabelText("Disable StreetMap");
                }
                else if (streetmapenabled == 1)
                {
                    Toast.makeText(traffic.this, "StreetMap Disabled", Toast.LENGTH_SHORT).show();
                    streetmapenabled = 0;
                    fab1.setLabelText("Enable StreetMap");
                }



            }
        });




        main = (CoordinatorLayout) findViewById(R.id.activity_main);


  /*      if (TemplateOrChoices.renew ==1)
        {
            Intent mIntent=new Intent(traffic.this, ChoicesOfPlace.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(mIntent);

        }*/



  /*      rout = (Button) findViewById(R.id.Broute);
        traffic = (Button) findViewById(R.id.traffic);
        altroute = (Button) findViewById(R.id.Aroute);
        PROXIMITY_RADIUS = (EditText) findViewById(R.id.radiuz);
*/


        icons = new BitmapDescriptor[11];
        icons[0] = BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11);
        icons[1] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[2] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[3] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[4] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[5] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[6] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[7] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[8] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[9] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);
        icons[10] = BitmapDescriptorFactory.fromResource(R.drawable.ccheck);

        loadingToasts = new String[10];
        loadingToasts[0] = "1st Marker Loading...";
        for (int i = 1; i <= 9; i++) {
            loadingToasts[i] = (i + 1) + "nd Marker Loading...";
        }


        pointview = (TextView) findViewById(R.id.tv1);
        durationview = (TextView) findViewById(R.id.tv2);
        distanceview = (TextView) findViewById(R.id.tv3);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.route) {
                    if (checklist == 0) {
                        Toast.makeText(getApplicationContext(), "Select Packages first or Add a marker in the map", Toast.LENGTH_LONG).show();

                    } else {
                        Intent z = new Intent(traffic.this, DestinationActivity.class);
                        z.putExtra("currentMarker", markers.size());
                        startActivityForResult(z, 99);
                    }

               /*     String url = null;
                    String url2 = null;
                    if (markers.size() <= 1) {
                        Toast.makeText(getApplicationContext(), "Please add a marker first!", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            url = makeURL3();
                            url2 = altURL();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        pointview.setVisibility(View.VISIBLE);
                        durationview.setVisibility(View.VISIBLE);
                        distanceview.setVisibility(View.VISIBLE);

                        connectAsyncTask downloadTask = new connectAsyncTask(url);
                        downloadTask.execute();

                        Log.d("tntn", points.size() + "");
                        for (int i = 0; i < points.size(); i++) {
                            Log.d("tntn", points.get(i).toString() + "");
                        }

                        if (points.size() > 2) {
                            //connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url2);
                            connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, traffic.this, false);
                            downloadTask2.execute();
                        }

                    }
*/

                } else if (item.getItemId() == R.id.places) {
                    myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());

                    mMap.setTrafficEnabled(false);
                } else if (item.getItemId() == R.id.directions) {
                    Intent i = new Intent(traffic.this, Home.class);
                    startActivity(i);
                    mMap.setTrafficEnabled(false);
                } else if (item.getItemId() == R.id.traffic) {
                    alternateRoute();
                    Log.e("Testing", String.valueOf(polylines));
                    Log.e("Testing", String.valueOf(listOfRouteArray));
                    Log.e("Testing", String.valueOf(listOfIndicesOfCurrentRoutes));


                    final Animation myAnim = AnimationUtils.loadAnimation(traffic.this, R.anim.button_bounce);
                    // Use bounce interpolator with amplitude 0.2 and frequency 20
                    BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                    expandableLayout.startAnimation(myAnim);
                    expandableLayout.toggleExpansion();

                } else if (item.getItemId() == R.id.reset) {
                    //Correction starts here
                    for(Marker marker : markers){
                        marker.remove();
                    }
                    markers.clear();

                    for (Polyline polyline : polylines){
                        polyline.remove();
                    }
                    polylines.clear();
                    listOfIndicesOfCurrentRoutes.clear();
                    listOfRouteArray.clear();
                    //Correction ends here
                    mMap.clear();
                    try {
                        pointview.setVisibility(View.GONE);
                        durationview.setVisibility(View.GONE);
                        distanceview.setVisibility(View.GONE);
                        clear = true;
                        place = new LinkedList<LatLng>();
                        points = new LinkedList<LatLng>();
                        markers = new LinkedList<>();
                        durations = new LinkedList<String>();
                        distances = new LinkedList<String>();
                        mList = new LinkedList<MarkerOptions>();

                        try {
                            altroute.setVisibility(View.GONE);
                            rout.setVisibility(View.GONE);
                        } catch (Exception e) {
                        }

                        rn = 0;
                        rl = 0;
                        res = "";
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Criteria criteria = new Criteria();
                        String provider = locationManager.getBestProvider(criteria, true);
                        Location myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                        //onLocationChanged(myLocation);
                        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 200,1, (android.location.LocationListener) traffic.this);

                        if (myLocation == null) {
                            Log.d("meme", "null location");
                        }

                 /*       mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);*/

                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(latLng)
                                .title("My Location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11))
                                .anchor(0.5f, 1);

                        Marker m = mMap.addMarker(markerOptions);

                        markers.add(m);
                        //EXTRA CODES
                        mList.add(markerOptions);
                        durations.add(new String("0"));
                        distances.add(new String("0"));

                        // Log.d("meme",myLocation.toString());
                        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                                myLocation.getLongitude());
                        // Log.d("meme",myLatLng.toString());

                        points.add(myLatLng);


                        getplaces();
                        Log.e("Testing", "hello" + String.valueOf(polylines));
                        Log.e("Testing", "hello" + String.valueOf(listOfRouteArray));
                        Log.e("Testing", "hello" + String.valueOf(listOfIndicesOfCurrentRoutes));

                    } catch (Exception e) {

                    }
                }


                return false;
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

        //new Handler().postDelayed(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            latLng = myLocation();
        //            oldplace = latLng;
        //            points.add(latLng);
        //            Log.d("mkmk", "c1");
        //            getplaces();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //    }
        //}, 3000);

        //   t.scheduleAtFixedRate(new TimerTask() {
        //                             @Override
        //
        //                            public void run() {
        try {
            latLng = updateLocation();
            float check = distance(oldplace, latLng);
            if (clear == false) {
                points.removeFirst();
                Log.d("mkmk", "d");
            }
            points.addFirst(latLng);
            Log.d("mkmk", "c2");
            if (check > 500 || clear == true) {
                clear = false;
                oldplace = latLng;
                getplaces();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }}, 0,5000);
    }

    public void navigate(View view) {
        float mapZoom = mMap.getCameraPosition().zoom >= 16 ? mMap.getCameraPosition().zoom : 16;
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(latLng)
                        .bearing(20)
                        .tilt(90)
                        .zoom(mapZoom)
                        .build();

        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition));

     /*   mMap.setOnCameraMoveStartedListener(this);*/

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private static final String TAG = traffic.class.getSimpleName();

    @Override
    public void onMapReady(GoogleMap googleMap) {



        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));



            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                LatLng pickupLocation = place.getLatLng();

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(pickupLocation);
                mMap.animateCamera(cameraUpdate);


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status);
            }
        });


        mMap = googleMap;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    latLng = myLocation();
                    //  points.add(latLng);
                    Log.d("mkmk", "b1");
                    oldplace = latLng;
                    getplaces();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);


        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    latLng = updateLocation();
                    float check = distance(oldplace, latLng);

                    if (clear == false) {
                        points.removeFirst();
                        Log.d("mkmk", "a");
                    }
                    points.addFirst(latLng);
                    Log.d("mkmk", "b2");
                    if (check > 500 || clear == true) {
                        clear = false;
                        oldplace = latLng;
                        getplaces();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setBuildingsEnabled(true);

        // ================================================================
        // ================================================================
        // ================================================================
        // ================================================================
        // ================================================================

        mMap = googleMap;

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();

            }
        } else {
            buildGoogleApiClient();

        }



    }


    // ================================================================
    // ================================================================
    // ================================================================
    // ================================================================
    // ================================================================
    // ================================================================

    @TargetApi(Build.VERSION_CODES.M)

    public LatLng myLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

        }
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        //onLocationChanged(myLocation);
        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 200,1, (android.location.LocationListener) traffic.this);
        if (myLocation == null) {
            Log.d("meme", "null location");
        }
       /* mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);*/
        mMap.setMyLocationEnabled(true);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("My Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11))
                .anchor(0.5f, 1);

        Marker m = mMap.addMarker(markerOptions);

        markers.addFirst(m);
        //EXTRA CODES
        mList.addFirst(markerOptions);

        // Log.d("meme",myLocation.toString());
        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                myLocation.getLongitude());
        // Log.d("meme",myLatLng.toString());

        points.add(myLatLng);
/*
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(20));
        */

        return myLatLng;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public LatLng updateLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        }
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (myLocation == null) {
            Log.d("meme", "null location");
        }
        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                myLocation.getLongitude());
        Log.d("mbmb", myLatLng.toString());
        return myLatLng;
    }

    public float distance(LatLng StartP, LatLng EndP) throws Exception {
        Location loc1 = new Location("");
        loc1.setLatitude(StartP.latitude);
        loc1.setLongitude(StartP.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(EndP.latitude);
        loc2.setLongitude(EndP.longitude);

        float distanceInMeters = loc1.distanceTo(loc2);
        return distanceInMeters;
    }




/*
    public void navigate(View view) {


        rn++;
        rn = rn % rl;
        drawPath(res);
    }
*/

    public void schedule(View view) {

        Intent i = new Intent(traffic.this, MainActivity.class);
        startActivity(i);

    }



/*    static String makeURL1() throws UnsupportedEncodingException {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radiusplace.inputtedradius);
        googlePlacesUrl.append("&type=" + "art_gallery");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCx8-ZK6m5FTgEoTaSRaUuALV-5Vnz1Co4");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }*/

/*    static String makeURL2() throws UnsupportedEncodingException {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radiusplace.inputtedradius);
        googlePlacesUrl.append("&type=" + "amusement_park");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCx8-ZK6m5FTgEoTaSRaUuALV-5Vnz1Co4");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }*/

/*    static String urlplace1() throws UnsupportedEncodingException {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radiusplace.inputtedradius);
        googlePlacesUrl.append("&type=" + "bowling_alley");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCx8-ZK6m5FTgEoTaSRaUuALV-5Vnz1Co4");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }*/

/*    static String urlplace2() throws UnsupportedEncodingException {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radiusplace.inputtedradius);
        googlePlacesUrl.append("&type=" + "museum");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCx8-ZK6m5FTgEoTaSRaUuALV-5Vnz1Co4");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }*/

 /*   static String urlplace3() throws UnsupportedEncodingException {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radiusplace.inputtedradius);
        googlePlacesUrl.append("&type=" + "zoo");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCx8-ZK6m5FTgEoTaSRaUuALV-5Vnz1Co4");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }*/

    static String urlplace4() throws UnsupportedEncodingException {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + 10);
        googlePlacesUrl.append("&type=" + "stadium");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCx8-ZK6m5FTgEoTaSRaUuALV-5Vnz1Co4");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    public void getplaces() {
        String url = null;
        String url2 = null;
        String url3 = null;

        String url4 = null;
        String url5 = null;
        String url6 = null;


        try {
        /*    url = makeURL1();
       *//*     url2 = makeURL2();*//*
            url3 = urlplace1();
            url4 = urlplace2();
            url5 = urlplace3();*/
            url6 = urlplace4();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
 /*       placesAsyncTask download = new placesAsyncTask(url);
*//*        placesAsyncTask download2 = new placesAsyncTask(url2);*//*
        placesAsyncTask download3 = new placesAsyncTask(url3);
        placesAsyncTask download4 = new placesAsyncTask(url4);
        placesAsyncTask download5 = new placesAsyncTask(url5);*/
        placesAsyncTask download6 = new placesAsyncTask(url6);
  /*      download.execute();
*//*        download2.execute();*//*
        download3.execute();
        download4.execute();
        download5.execute();*/
        download6.execute();
    }


    public void showplaces(String result) {
        try {
            placeMarkers = new LinkedList<MarkerOptions>();

            final JSONObject son = new JSONObject(result);
            JSONArray placesArray = son.getJSONArray("results");
            for (int i = 1; i < placesArray.length(); i++) {
                JSONObject places = placesArray.getJSONObject(i);
                JSONObject geometry = places.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");

                placeId = places.getString("place_id");
                LatLng temp = new LatLng((Double) location.get("lat"), (Double) location.get("lng"));
                Log.d("tyty", places.getString("name"));
                if (!place.contains(temp)) {
                    place.add(temp);
                    placename.add(places.getString("name"));
                    MarkerOptions markerOptions =
                            new MarkerOptions()
                                    .position(temp
                                    ).title(places.getString("name"))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon4))
                                    .snippet("placeId:" + placeId);

                    Marker placeMarker = mMap.addMarker(markerOptions);
                    placeMarkers.add(markerOptions);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tyty", e.getMessage());
        }

    }





    public String altURL() throws UnsupportedEncodingException {
        String params, sensor, main;
        sensor = "&sensor=false&mode=driving&alternatives=true";
        main = "origin=" + Double.toString(latLng.latitude) + "," + Double.toString(latLng.longitude) + "&destination=" + Double.toString(points.get(points.size() - 1).latitude) + "," + Double.toString(points.get(points.size() - 1).longitude);
        params = main + sensor;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + params + "&key=AIzaSyDK1zxUEp38e6sQYzJq6qGNKxdOUqUZR1Y";
        Log.d("mama", url);
        return url;
    }

    public static String makeURL3() throws UnsupportedEncodingException {
        String params, waypoints, sensor, main;
        if (points.size() > 2) {
            waypoints = "waypoints=optimize:true";
            for (int i = 1; i < points.size() - 1; i++) {
                String Temp = URLEncoder.encode("|", "UTF-8") + points.get(i).latitude + "," + points.get(i).longitude;
                waypoints = waypoints.concat(Temp);
            }
            sensor = "&alternatives=true&sensor=false&mode=driving";
            main = "origin=" + Double.toString(points.get(0).latitude) + "," + Double.toString(points.get(0).longitude) + "&destination=" + Double.toString(points.get(points.size() - 1).latitude) + "," + Double.toString(points.get(points.size() - 1).longitude);
            params = main + "&" + waypoints + sensor;
        } else {
            sensor = "&sensor=false&mode=driving&alternatives=true";
            main = "origin=" + Double.toString(latLng.latitude) + "," + Double.toString(latLng.longitude) + "&destination=" + Double.toString(points.get(points.size() - 1).latitude) + "," + Double.toString(points.get(points.size() - 1).longitude);
            params = main + sensor;
        }
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + params + "&key=AIzaSyDK1zxUEp38e6sQYzJq6qGNKxdOUqUZR1Y";
        Log.d("mama", url);
        return url;
    }

    public void drawcheck(String result) {
        try {
            //Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONArray waypoints = json.getJSONArray("geocoded_waypoints");
            Log.d("tntn", waypoints.length() + "");
            if (routeArray.length() > 1) {
                altroute.setVisibility(View.VISIBLE);
                res = result;
                rl = routeArray.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String next;
    public void drawPath(String result) {
//        if (line != null) {
//            line.remove();
//        }

        ArrayList<HashMap<String,String>> list2 = new ArrayList<HashMap<String, String>>();
        try {
            //Transform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");



            JSONArray waypoints = json.getJSONArray("geocoded_waypoints");



            if (routeArray.length() > 1) {
                try {
                    altroute.setVisibility(View.VISIBLE);
                }
                catch (Exception e) {
                }

                res = result;
                rl = routeArray.length();
            }

            if(routeArray.length() > 0) {

                //add the set of routes to the list of routeArray
                listOfRouteArray.add(routeArray);

                //add the index of the route currently used
                //add the index of the route currently used
                listOfIndicesOfCurrentRoutes.add(0);

                JSONObject routes = routeArray.getJSONObject(0);
                JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                String encodedString = overviewPolylines.getString("points");

                List<LatLng> list = decodePoly(encodedString);
                PolylineOptions options = new PolylineOptions().width(10).color(Color.GREEN).geodesic(true);


                for (int z = 0; z < list.size(); z++) {
                    LatLng point = list.get(z);
                    options.add(point);
                }
                polylines.add(mMap.addPolyline(options));

                //add the new polyline to the list of polylines

            /*    if (waypoints.length() > 2) {
                    bigline = line;
                }*/

                JSONArray legsarray = routes.getJSONArray("legs");
                JSONObject forturn = legsarray.getJSONObject(0);
                 turns = forturn.getJSONArray("steps");

                durations = new LinkedList<String>();
                distances = new LinkedList<String>();
                durations.add(new String("0"));
                distances.add(new String("0"));
                reminders.add(new String("0"));
                timestoStay.add(new String("0"));
                mins.add(new String("0"));
//                polylines.add(line);
                String distance = "0";
                String duration = "0";

                if (legsarray.length() > 0) {


                    String temp1 = "";

                    for (int i = 0; i < markers.size() - 1; i++) {


                        JSONObject legs = legsarray.getJSONObject(i);
                        JSONObject distanceobject = legs.getJSONObject("distance");
                        distances.add(distanceobject.getString("value"));


                        JSONObject durationObject = legs.getJSONObject("duration");
                        durations.add(convertSecondsToTimeString(Integer.parseInt(durationObject.getString("value"))));


                        int zx = listofturns.size();
                        Log.d("zxc", "" + zx);


                        duration = duration + "-- " + durationObject.getString("value");
                        distance = distance + "-- " + distanceobject.getString("value");
                        temp1 = temp1 + " -->" + "pt" + i;




                    }


                }



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
try{
        for (int zxcz = 0; zxcz < turns.length(); zxcz++) {
            JSONObject t1 = turns.getJSONObject(zxcz);

String get = t1.getString(ins);


            Log.d("zxc", "" + t2);
            Log.d("zxc", "" + get);

            HashMap<String, String> employees = new HashMap<>();
            employees.put(next, get);


            list2.add(employees);
        }


    } catch (JSONException e) {
        e.printStackTrace();
    }
        ListAdapter adapter = new SimpleAdapter(
                traffic.this, list2, R.layout.turnslist,
                new String[]{next},
                new int[]{R.id.turns});

        listViewz.setAdapter(adapter);

    }


  /*  public void drawPath(String result) {
        if (line != null) {
            line.remove();
        }
        try {
            //Transform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONArray waypoints = json.getJSONArray("geocoded_waypoints");

            if (routeArray.length() > 1) {
                try {
                    altroute.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                }

                res = result;
                rl = routeArray.length();
            }

            if(routeArray.length() > 0) {

                //add the set of routes to the list of routeArray
                listOfRouteArray.add(routeArray);

                //add the index of the route currently used
                listOfIndicesOfCurrentRoutes.add(0);

                JSONObject routes = routeArray.getJSONObject(rn);
                JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                String encodedString = overviewPolylines.getString("points");
                List<LatLng> list = decodePoly(encodedString);
                PolylineOptions options = new PolylineOptions().width(10).color(Color.GREEN).geodesic(true);
                for (int z = 0; z < list.size(); z++) {
                    LatLng point = list.get(z);
                    options.add(point);
                }
                line = mMap.addPolyline(options);
                if (waypoints.length() > 2) {
                    bigline = line;
                }
                JSONArray legsarray = routes.getJSONArray("legs");

                durations = new LinkedList<String>();
                distances = new LinkedList<String>();
                durations.add(new String("0"));
                distances.add(new String("0"));
                reminders.add(new String("0"));
                timestoStay.add(new String("0"));
                mins.add(new String("0"));


                String distance = "0";
                String duration = "0";

                if (legsarray.length() > 0) {


                    String temp1 = "";

                    for (int i = 0; i < markers.size() - 1; i++) {
                        JSONObject legs = legsarray.getJSONObject(i);
                        JSONObject distanceobject = legs.getJSONObject("distance");
                        distances.add(distanceobject.getString("value"));
                        JSONObject durationObject = legs.getJSONObject("duration");
                        durations.add(convertSecondsToTimeString(Integer.parseInt(durationObject.getString("value"))));

                        duration = duration + "--Second:" + durationObject.getString("value");
                        distance = distance + "--Meters: " + distanceobject.getString("value");
                        temp1 = temp1 + " -->" + "pt" + i;
                    }


                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    //called when alternate route button is clicked
    public void alternateRoute(){

        //If the displayed polyline of the route to the first destination is not the last alternative
        //polyline, display the next alternative polyline else called the
        // incrementRouteIndexOfNextOrderPolyline() function. Take a look at
        // incrementRouteIndexOfNextOrderPolyline() and look at this example. example: if we have
        // 138 the next count is 139.
        if(!listOfRouteArray.isEmpty()){
            if((listOfIndicesOfCurrentRoutes.get(0) + 1) < listOfRouteArray.get(0).length()){

                alternatePolyline(0, listOfIndicesOfCurrentRoutes.get(0) + 1);

            }
            else {
                incrementRouteIndexOfNextOrderPolyline(0);
            }
        }

        Log.d("WAXAAXAXAXAXAX", String.valueOf(listOfRouteArray));
        Log.d("WAXAAXAXAXAXAX2", String.valueOf(listOfIndicesOfCurrentRoutes));

    }

    //To easily understand the functioning of this function, counting from 0 to 100. We start at 000,
    //when we reach 009, the next count is 010. When we reach 019, the next count is 020 and so on.
    //when we reach 099 the next count is 100 and when we reach 199 the next count is 200 and so on.
    //Now consider that to alternate routes, we are counting them an displaying the current count on
    //the map. The difference between this count and the one in the example is that the last digit is
    //not always 9 or the same for each digit order. That is the number of alternative routes from the
    //origin to a destination is not always the same for all destinations. Some might have 2, 1, 3 etc.
    //An example of a counting for this routes is 000 then 100 or 1234 then 2000, with the number of
    //digit representing the number of destination. This function helps in a next order incrementation;
    //that is in transition such as 009 t0 010 or 099 to 100 or 122 t0 200 or 111 to 200 etc
    private void incrementRouteIndexOfNextOrderPolyline(int indexOfPolyline){

        //sets the polyline of the route to a destination to the first polyline of the list of all
        //possible polylines of the route to that destination. Tha is if a digit was 9 set it to 0.
        alternatePolyline(indexOfPolyline, 0);

        //if the route to destination with dealing with is the route to the lastly added destination
        //return. that is if the digit is the first 9 in 9999 return.
        if((indexOfPolyline + 1) >= polylines.size()){
            return ;
        }

        //if the polyline of the next order route to destination is not that last possible alternative
        //polyline for that route, set it to the next possible polyline else increment the index of it's
        // next order polyline. that is if we have 118 and we are dealing with 8 make the next count to
        // be 119 else we have 119 make it to be 120
        if((listOfIndicesOfCurrentRoutes.get(indexOfPolyline + 1) + 1) <
                listOfRouteArray.get(indexOfPolyline + 1).length()){

            //Correction on this statement
            alternatePolyline(indexOfPolyline + 1,
                    listOfIndicesOfCurrentRoutes.get(indexOfPolyline + 1) + 1);

        }else {
            incrementRouteIndexOfNextOrderPolyline(indexOfPolyline + 1);
        }

        //This function is recursive a such the incrementation will done recursively. for example if
        //we have 199 the first order 9 will be set to 0 and the second order 9 incremented. The
        // second order 9 in turns will be set to 0 and the third order 1 will be 2 to give 200. But
        //in case of 189 the result will obviously be 190 without a recursive call.
    }

    //Used to display the new polyline in place of the currently displayed polyline for the route to
    //a specific destination.


    private void alternatePolyline(int indexOfRouteToAlternate, int indexOfNewPolyline){

        try {
            JSONObject routes = listOfRouteArray.get(indexOfRouteToAlternate).getJSONObject(indexOfNewPolyline);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            PolylineOptions options = new PolylineOptions().width(10).color(Color.GREEN).geodesic(true);
            for (int z = 0; z < list.size(); z++) {
                LatLng point = list.get(z);
                options.add(point);
            }
            //Correction starts here
//            line = mMap.addPolyline(options);
            polylines.get(indexOfRouteToAlternate).remove();
            polylines.set(indexOfRouteToAlternate, mMap.addPolyline(options));


            listOfIndicesOfCurrentRoutes.set(indexOfRouteToAlternate, indexOfNewPolyline);
            //Correction ends here

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String convertSecondsToTimeString(int seconds) {

        int sec = seconds % 60;
        int min = (seconds / 60) % 60;
        int hours = (seconds / 60) / 60;

        if (hours > 0) {
            return hours + " hrs: " + min + " min: " + sec + " s";
        } else {
            return min + " min: " + sec + " s";
        }
    }


    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }



    int bayani = 0;
    int cof = 0;
    int dam = 0;
    int east = 0;
    int edsa = 0;
    int maginhawa = 0;
    int ninoy = 0;
    int parish = 0;
    int people = 0;
    int qcx = 0;
    int qmc = 0;
    int up = 0;
    int vargas = 0;
    int watershed = 0;
    int wildlife = 0;

    int ayala = 0;


    public void places(View view) {
      /*  if (button1 == 1) {
            ChoicesOfPlace.open = 1;
            Intent i = new Intent(traffic.this, ChoicesOfPlace.class);
            startActivity(i);
        } else if (button1 == 2) {

            Intent i = new Intent(traffic.this, SummaryOfTour.class);
            startActivity(i);
        }
*/

    }


    public void selected() {


        if (InfoOfUp.select == 1) {
            if (ayala == 0) {
                ayala();
                ayala = 1;
            } else if (ayala == 1) {

            }
        }

        if (InfoOfArt.select == 1) {
            if (art == 0) {
                ArtInIsland();
                art = 1;
            } else if (art == 1) {

            }
        }

        if (InfoOfParish.select == 1) {
            if (parish == 0) {
                parish();
                parish = 1;
            } else if (parish == 1) {

            }
        }


        if (InfoOfAteneo.select == 1) {

            if (ateneo == 0) {
                ateneogallery();
                ateneo = 1;
            } else if (ateneo == 1) {

            }

        }
        if (InfoOfBayani.select == 1) {
            if (bayani == 0) {
                bantayog();
                bayani = 1;
            } else if (bayani == 1) {

            }

        }

        if (InfoOfCOF.select == 1) {

            if (cof == 0) {
                circleoffun();
                cof = 1;
            } else if (cof == 1) {

            }
        }

        if (InfoOfDam.select == 1) {

            if (dam == 0) {
                lamesa();
                dam = 1;
            } else if (dam == 1) {

            }
        }


        if (InfoOfEast.select == 1) {

            if (east == 0) {
                eastwood();
                east = 1;
            } else if (east == 1) {

            }
        }


        if (InfoOfEdsa.select == 1) {

            if (edsa == 0) {
                edsa();
                edsa = 1;
            } else if (edsa == 1) {

            }

        }

        if (InfoOfMaginhawa.select == 1) {


            if (maginhawa == 0) {
                maginhawa();
                maginhawa = 1;
            } else if (maginhawa == 1) {
                Toast.makeText(getApplicationContext(), "Place updated", Toast.LENGTH_LONG).show();
            }
        }


        if (InfoOfNinoy.select == 1) {


            if (ninoy == 0) {
                wildlife();
                ninoy = 1;
            } else if (ninoy == 1) {

            }

        }

        if (InfoOfPeople.select == 1) {


            if (people == 0) {
                people();
                people = 1;
            } else if (people == 1) {

            }

        }


        if (InfoOfQmc.select == 1) {


            if (qmc == 0) {
                MemorialCircle();
                qmc = 1;
            } else if (qmc == 1) {
                Toast.makeText(getApplicationContext(), "Place updated", Toast.LENGTH_LONG).show();
            }

        }

/*        if (InfoOfUp.select == 1) {


            if (up == 0) {
              ayala();
                up= 1;
            } else if (up == 1) {

            }

        }*/
        if (InfoOfVargas.select == 1) {


            if (vargas == 0) {
                vargas();
                vargas = 1;
            } else if (vargas == 1) {

            }

        }
        if (InfoOfWatershed.select == 1) {


            if (watershed == 0) {
                watershed();
                watershed = 1;
            } else if (watershed == 1) {

            }

        }


        Toast.makeText(getApplicationContext(), "Place updated", Toast.LENGTH_LONG).show();


    }






    public void destinationclear(View view) {

            mMap.clear();
            points.clear();
            markers.clear();
            mList.clear();
            distances.clear();
            durations.clear();
            reminders.clear();
            timestoStay.clear();
            mins.clear();

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng)
                    .title("My Location")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11))
                    .anchor(0.5f, 1);

            Marker m = mMap.addMarker(markerOptions);

            markers.add(m);
            //EXTRA CODES
            mList.add(markerOptions);

/*    durations.add(new String("0"));
    distances.add(new String("0"));*/

            // Log.d("meme",myLocation.toString());
            LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                    myLocation.getLongitude());
            // Log.d("meme",myLatLng.toString());

            points.add(myLatLng);



    }

    public void ayala() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6614;
        double we = 121.0635;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("UP Technohub")
                .snippet("placeId:" + "ChIJK2RZshS3lzMRBFjc7R0tyIU")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));


        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void parish() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6584;
        double we = 121.0711;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .snippet("placeId:" + "ChIJo3WucGe3lzMR_iYmkhk6TjI")
                .title("The Parish Sacrifice")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));


        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void themansion() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 16.4124;
        double we = 120.6215;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("The Mansion")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));


        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void minesview() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 16.4196;
        double we = 120.6279;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Mines  View")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void grotto() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 16.4096;
        double we = 120.5806;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void cathedral() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 16.4129;
        double we = 120.5985;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void burnham() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 16.4095;
        double we = 120.5948;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void museo() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5791;
        double we = 120.9771;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void manilaoceanpark() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5793;
        double we = 120.9724;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void coconut() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5552;
        double we = 120.9801;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void national() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5869;
        double we = 120.9812;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void starcity() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5560;
        double we = 120.9858;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void sanagustin() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5886;
        double we = 120.9749;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void pacopark() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5810;
        double we = 120.9884;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void cultural() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5583;
        double we = 120.9857;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void NationalMuseum() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5869;
        double we = 120.9812;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void manilazoo() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5651;
        double we = 120.9885;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void cemetery() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6325;
        double we = 120.9847;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void arch() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5971459;
        double we = 120.9764795;


        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void china() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6012;
        double we = 120.9750;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void bahay() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5909;
        double we = 120.9750;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void mystery() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5626;
        double we = 121.0225;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Mystery Room Quezon City")
                .snippet("placeId:" + "ChIJ3bGhD_i3lzMROoN7udn8v30")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));
        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void armed() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6110;
        double we = 121.0619;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Armed Forces of the Philippines Museum")
                .snippet("placeId:" + "ChIJK725zei3lzMRdjehQsQsUKw")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String("0"));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void fernwood() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6690;
        double we = 121.0481;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Fernwood Gardens")
                .snippet("placeId:" + "ChIJ5222gju3lzMRTgrD9gxfVko")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void sining() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6192;
        double we = 121.0564;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Sining Kamalig")
                .snippet("placeId:" + "ChIJ3xrTH8C3lzMReJnE6215ffA")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void watershed() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.7452;
        double we = 121.0984;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Lamesa Watershed")
                .snippet("placeId:" + "ChIJS8sCX8e6lzMRuwnG170scsI")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void vargas() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.653056;
        double we = 121.066667;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Jorge B.Vargas Museum")
                .snippet("placeId:" + "ChIJc7klbG-3lzMRpQnjjsAGPqw")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void people() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6000;
        double we = 121.0600;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("People Power Monument")
                .snippet("placeId:" + "ChIJkVJ4z-G3lzMRtIL8hox324E")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));
        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void intramuros() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5896;
        double we = 120.9747;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void manila() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5915;
        double we = 120.9736;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void casa() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.591496;
        double we = 120.9794;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void luneta() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5831;
        double we = 120.9794;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void lamesa() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.7164;
        double we = 121.0724;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("La Mesa Dam and Reservoir")
                .snippet("placeId:" + "ChIJbyf0oIawlzMRCRQnxW-0wEs")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void pagasa() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6474;
        double we = 121.0396;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Pagasa Planetarium")
                .snippet("placeId:" + "ChIJjZ0Jtga3lzMRD6aUuSpC_-g")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void rita() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6483;
        double we = 121.0322;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Sta. Rita de Cascia Parish")
                .snippet("placeId:" + "ChIJH0W3P_62lzMRMg5dO-FkrKI")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void wildlife() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6509;
        double we = 121.0440;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Ninoy Aquino WildLife")
                .snippet("placeId:" + "ChIJAeHvngW3lzMRikJHv1z5cVI")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void bantayog() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6442;
        double we = 121.0393;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Bantayog ng mga Bayani")
                .snippet("placeId:" + "ChIJ2yEQTQe3lzMRbfDgt60K770")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void eastwood() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6099;
        double we = 121.0811;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Eastwood City")
                .snippet("placeId:" + "ChIJp79Jhh24lzMRGBBcLU5fzio")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void churchgesu() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6401;
        double we = 121.0800;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Church of the Gesu")
                .snippet("placeId:" + "ChIJh1SYgtW5lzMRgkVhXmhwt9E")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void maginhawa() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6467;
        double we = 121.0588;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Maginhawa Food Park")
                .snippet("placeId:" + "ChIJLyKHcHO3lzMRkPD3eaV8zo4")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));
        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void edsa() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.5926;
        double we = 121.0587;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Edsa Shrine")
                .snippet("placeId:" + "ChIJ83np5BjIlzMRQnvbQuvP320")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void ateneogallery() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6401;
        double we = 121.0773;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Ateneo Art Gallery")
                .snippet("placeId:" + "ChIJQdDOsX-3lzMRsMd8b7e3uDk")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void santamaria() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6475;
        double we = 121.0752;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Santa Maria Church")
                .snippet("placeId:" + "ChIJazWVwHy3lzMRAUHR5Esc9AA")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));
        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void uptc() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6509;
        double we = 121.0753;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .snippet("placeId:" + "ChIJHao_OWO3lzMRjOuMs_XND84")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void balara() {




       /* Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();*/

        double wa = 14.6572;
        double we = 121.0779;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Balara Filter Park")
                .snippet("placeId:" + "ChIJCyiJ5d-5lzMR0CBYpfXkOE8")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void up() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6546;
        double we = 121.0647;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("University of the Philippines Diliman")
                .snippet("placeId:" + "ChIJBURBCm63lzMR65XYe4mXKhw")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void qcx() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6516;
        double we = 121.0514;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Quezon City Experience")
                .snippet("placeId:" + "ChIJJRUU-w23lzMRkCSPWgARC-A")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void circleoffun() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6534;
        double we = 121.0480;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Circle of Fun")
                .snippet("placeId:" + "ChIJKeIIzxG3lzMREPRXb7ccU40")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void heritage() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6495;
        double we = 121.0503;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("The Quezon Heritage House")
                .snippet("placeId:" + "ChIJCykyhw63lzMRDY_BQBLbE8o")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void streat() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6467;
        double we = 121.0588;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Maginhawa Streat")
                .snippet("placeId:" + "ChIJLyKHcHO3lzMRkPD3eaV8zo4")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));
        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void WildLife() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6509;
        double we = 121.0440;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Destination " + mList.size())
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*

        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);
*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }

    public void MemorialCircle() {


        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();

        double wa = 14.6516;
        double we = 121.04941;

        LatLng was = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(was)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Quezon Memorial Circle")
                .snippet("placeId:" + "ChIJ6csDPg63lzMR6I3qWXvgpVY")
                .anchor(0.5f, 1);


        points.add(was);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


/*        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);*/


        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    public void ArtInIsland() {




    /*    Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();*/

        double wa = 14.6228;
        double we = 121.0581;

        LatLng xaxa = new LatLng(wa, we);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(xaxa)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placesz))
                .title("Art In Island")
                .snippet("placeId:" + "ChIJ5UTioJW3lzMRCVEsXIinoQY")
                .anchor(0.5f, 1);


        points.add(xaxa);


        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String("."));
        timestoStay.add(new String("."));
        mins.add(new String("."));


        placesnumber = 1;


        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


/*        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);*/

        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        int request = requestCode;
        int result = resultCode;





        //add markers back
        int current = 0;
//        for (MarkerOptions options : mList) {
//            Marker m = mMap.addMarker(options);
//            //reset icons
//            m.setIcon(icons[current++]);
//        }

        //add places back
        for (MarkerOptions place : placeMarkers) {
            Marker m = mMap.addMarker(place);
        }


        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        try {

            connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, false);
            downloadTask2.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cleardestination() {
        mMap.clear();
        points.clear();
        markers.clear();
        mList.clear();
        distances.clear();
        durations.clear();
        reminders.clear();
        timestoStay.clear();
        mins.clear();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("My Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11))
                .anchor(0.5f, 1);

        Marker m = mMap.addMarker(markerOptions);

        markers.add(m);
        //EXTRA CODES
        mList.add(markerOptions);

/*    durations.add(new String("0"));
    distances.add(new String("0"));*/

        // Log.d("meme",myLocation.toString());
        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                myLocation.getLongitude());
        // Log.d("meme",myLatLng.toString());

        points.add(myLatLng);
    }




    Marker now;

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        mMap.getUiSettings().setMyLocationButtonEnabled(false);


        //Place current location marker

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mCurrLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11)));
        Toast.makeText(traffic.this, "Your Current Location", Toast.LENGTH_LONG).show();

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f", latitude, longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");


    }



    @Override
    public void onMapLongClick(LatLng latLng) {
        //Add marker on LongClick position
/*        Toast.makeText(getApplicationContext(), loadingToasts[mList.size() - 1], Toast.LENGTH_LONG).show();*/


        checklist = 1;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                //Small correction here, not too important
                .icon(icons[mList.size() + 1])
                .title("Destination " + mList.size() + 1)
                //end of correction
                .anchor(0.5f, 1);

        points.add(latLng);
        markers.add(mMap.addMarker(markerOptions));

        //EXTRA CODES
        mList.add(markerOptions);

        distances.add(new String("0"));
        durations.add(new String("0"));
        reminders.add(new String(""));
        timestoStay.add(new String(""));
        mins.add(new String(""));

        String url = null;
        try {
            url = makeURL3();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        pointview.setVisibility(View.VISIBLE);
        durationview.setVisibility(View.VISIBLE);
        distanceview.setVisibility(View.VISIBLE);



        connectAsyncTask2 downloadTask2 = new connectAsyncTask2(url, this, true);
        downloadTask2.execute();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        streetmap = marker.getPosition();
        double latitute = streetmap.latitude;
        double longotude = streetmap.longitude;

        String snippet = marker.getSnippet();
        Log.d("onMarkerClick", "snippet equals: " + snippet);
        if (snippet == null) {
            //marker is not a place, what info do you want to show? if nothing return else tell me
            return true;
        }


        String placeId = snippet.substring(8);
        Log.d("onMarkerClick", "placeId is: " + placeId);
        Intent toMarkerInfo = new Intent(traffic.this, MarkerInfoActivity.class);
        toMarkerInfo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        toMarkerInfo.putExtra("placeId", placeId);
        Toast.makeText(traffic.this, " " + placeId, Toast.LENGTH_SHORT).show();
        startActivity(toMarkerInfo);

        return true;


    }

    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;

        connectAsyncTask(String urlPass) {
            url = urlPass;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(traffic.this);
            progressDialog.setMessage("Fetching route, Please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if (result != null) {
                Log.d("momo", " : " + result);
                drawPath(result);
            }
        }
    }

    public class connectAsyncTask2 extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        private traffic traffic;
        private boolean displayDestinationDetails;
        String url;
        boolean launchDestination;

        connectAsyncTask2(String urlPass, traffic traffic, boolean displayDestinationDetails) {
            this.url = urlPass;
            this.traffic = traffic;
            this.displayDestinationDetails = displayDestinationDetails;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            try {
                super.onPreExecute();
                progressDialog = new ProgressDialog(traffic.this);
                progressDialog.setMessage("Fetching route, Please wait...");
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if (result != null) {
                Log.d("momo2", " : " + result);
                traffic.drawPath(result);



            }

            if (displayDestinationDetails) {

                Intent i = new Intent(traffic.this, poppers.class);
                i.putExtra("currentMarker", traffic.markers.size());
                traffic.startActivity(i);







            }





        }
    }

    private class placesAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;

        placesAsyncTask(String urlPass) {
            url = urlPass;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Log.d("mzmz", " : " + result);
                showplaces(result);
            }
        }
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10 * 1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    public static String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radiusplace.inputtedradius);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyDK1zxUEp38e6sQYzJq6qGNKxdOUqUZR1Y");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }

                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }



    public static class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

        String googlePlacesData;
        GoogleMap mMap;
        String url;
        public static int reminders;

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d("GetNearbyPlacesData", "doInBackground entered");
                mMap = (GoogleMap) params[0];
                url = (String) params[1];
                DownloadUrl downloadUrl = new DownloadUrl();
                googlePlacesData = downloadUrl.readUrl(url);
                Log.d("GooglePlacesReadTask", "doInBackground Exit");
            } catch (Exception e) {
                Log.d("GooglePlacesReadTask", e.toString());
            }
            return googlePlacesData;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("GooglePlacesReadTask", "onPostExecute Entered");
            List<HashMap<String, String>> nearbyPlacesList = null;
            DataParser dataParser = new DataParser();
            nearbyPlacesList = dataParser.parse(result);
            ShowNearbyPlaces(nearbyPlacesList, result);
            Log.d("GooglePlacesReadTask", "onPostExecute Exit");
        }

        private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList, String result) {

            try {

                ;
                JSONObject jsonObject = new JSONObject(result);
                LinkedList<MarkerOptions> placeMarkers = new LinkedList<MarkerOptions>();

                JSONArray placesArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < placesArray.length(); i++) {
                    Log.d("onPostExecute", "Entered into showing locations");
                    JSONObject places = placesArray.getJSONObject(i);
                    JSONObject geometry = places.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    //****************************
                    placeId = places.getString("place_id");
                    LatLng temp = new LatLng((Double) location.get("lat"), (Double) location.get("lng"));
                    Log.d("tyty", places.getString("name"));

                    if (!place.contains(temp)) {

                        place.add(temp);
                        placename.add(places.getString("name"));
                        MarkerOptions markerOptions =
                                new MarkerOptions()
                                        .position(temp
                                        ).title(places.getString("name"))
                                        .snippet("placeId:" + placeId);


                        if (reminders == 2) {

                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.air));

                        }
                        else if (reminders ==3)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_station));
                        }
                        else if (reminders ==4)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_rental));
                        }
                        else if (reminders ==5)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi_stand));
                        }
                        else if (reminders ==6)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.train_station));
                        }
                        else if (reminders ==7)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.transit_station));
                        }
                        else if (reminders ==8)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.real_state_agency));
                        }
                        else if (reminders ==9)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.insured));
                        }
                        else if (reminders ==10)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.travel_agency));
                        }
                        else if (reminders ==11)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_atm));
                        }
                        else if (reminders ==12)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.police));
                        }
                        else if (reminders ==13)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.fireszz));
                        }

                        else if (reminders ==14)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital));
                        }
                        else if (reminders ==15)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pharmacy));
                        }
                        else if (reminders == 16)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.parking));
                        }
                        else if (reminders == 17)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.meal_delivery));
                        }
                        else if (reminders == 18)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.hair_care));
                        }
                        else if (reminders == 19)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gyn));
                        }
                        else if (reminders == 20)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_wash));
                        }
                        else if (reminders == 21)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_repair));
                        }
                        else if (reminders == 23)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.laundry));
                        }
                        else if (reminders == 24)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.gas_station));
                        }
                        else if (reminders == 25)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bicycle_store));
                        }
                        else if (reminders == 26)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.book_store));
                        }
                        else if (reminders == 27)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.hardware_store));
                        }
                        else if (reminders == 28)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.furniture_store));
                        }
                        else if (reminders == 29)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.jewelry_store));
                        }
                        else if (reminders == 30)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.liquour_store));
                        }
                        else if (reminders == 31)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.store));
                        }
                        else if (reminders == 32)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clothing_store));
                        }
                        else if (reminders == 33)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.department_store));
                        }
                        else if (reminders == 34)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pet_store));
                        }
                        else if (reminders == 35)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.electronics_store));
                        }
                        else if (reminders == 36)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.parkor));
                        }
                        else if (reminders == 37)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bar));
                        }

                        else if (reminders == 38)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bowling_alley));
                        }
                        else if (reminders == 39)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe));
                        }
                        else if (reminders == 40)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.campground));
                        }
                        else if (reminders == 41)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.casino));
                        }
                        else if (reminders == 42)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.night_club));
                        }
                        else if (reminders == 43)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.art_gallery));
                        }
                        else if (reminders == 44)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.shoppers));
                        }
                        else if (reminders == 45)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.museum));
                        }
                        else if (reminders == 46)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.park1));
                        }
                        else if (reminders == 47)
                        {
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.spa));
                        }

                        Marker placeMarker = mMap.addMarker(markerOptions);
                        placeMarkers.add(markerOptions);
                    }


                    //move map camera
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                }
            }catch (Exception e) {
                e.printStackTrace();
                Log.d("tyty", e.getMessage());
            }
        }
    }







}

