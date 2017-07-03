package com.sumo.traffic;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DirectionsApi extends AppCompatActivity {

    private static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private Handler handler = new Handler();
    private Random random = new Random();
    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            //setHasOptionsMenu(true);
        }
    };

    private GoogleMap googleMap;
    private List<Marker> markers = new ArrayList<>();
    private boolean directionsFetched = false;
    private String from, to;
    Marker marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_map_fragment);
        handler.postDelayed(runner, random.nextInt(2000));

        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;
                if (ActivityCompat.checkSelfPermission(DirectionsApi.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DirectionsApi.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                else {
                    googleMap.setMyLocationEnabled(true);
                }
                new DirectionsFetcher(from, to).execute();
            }
        });

    }

    /**
     * Adds a list of markers to the map.
     */
    public void addPolylineToMap(List<LatLng> latLngs) {
        PolylineOptions options = new PolylineOptions().width(10).color(Color.GREEN);
        for (LatLng latLng : latLngs) {
            options.add(latLng);
        }
        googleMap.addPolyline(options);
    }

    /**
     * Clears all markers from the map.
     */
    public void clearMarkers() {
        googleMap.clear();
        markers.clear();
    }

    private List<LatLng> latLngs = new ArrayList<LatLng>();

    private class DirectionsFetcher extends AsyncTask<URL, Integer, Void> {


        private String origin;
        private String destination;

        public DirectionsFetcher(String origin,String destination) {
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            clearMarkers();
            setProgressBarIndeterminateVisibility(Boolean.TRUE);

        }

        protected Void doInBackground(URL... urls) {
            try {
                HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });

                GenericUrl url = new GenericUrl("http://maps.googleapis.com/maps/api/directions/json");
                url.put("origin", origin);
                url.put("destination", destination);
                url.put("sensor",false);

                HttpRequest request = requestFactory.buildGetRequest(url);
                HttpResponse httpResponse = request.execute();
                DirectionsResult directionsResult = httpResponse.parseAs(DirectionsResult.class);

                String encodedPoints = directionsResult.routes.get(0).overviewPolyLine.points;
                latLngs = PolyUtil.decode(encodedPoints);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Void result) {
            directionsFetched=true;
            System.out.println("Adding polyline");
            addPolylineToMap(latLngs);
            System.out.println("Fix Zoom");
            GoogleMapUtis.fixZoomForLatLngs(googleMap, latLngs);
            System.out.println("Start anim");
            animator.startAnimation(false, latLngs);
            //updateNavigationStopStart();
            setProgressBarIndeterminateVisibility(Boolean.FALSE);
        }
    }

    public static class DirectionsResult {

        @Key("routes")
        public List<Route> routes;

    }

    public static class Route {
        @Key("overview_polyline")
        public OverviewPolyLine overviewPolyLine;

    }

    public static class OverviewPolyLine {
        @Key("points")
        public String points;

    }

    private Animator animator = new Animator();
    private final Handler mHandler = new Handler();

    public class Animator implements Runnable {

        private static final int ANIMATE_SPEEED = 1500;
        private static final int ANIMATE_SPEEED_TURN = 1500;
        private static final int BEARING_OFFSET = 20;

        private final Interpolator interpolator = new LinearInterpolator();

        private boolean animating = false;

        private List<LatLng> latLngs = new ArrayList<LatLng>();

        int currentIndex = 0;

        float tilt = 90;
        float zoom = 15.5f;
        boolean upward=true;

        long start = SystemClock.uptimeMillis();

        LatLng endLatLng = null;
        LatLng beginLatLng = null;

        boolean showPolyline = false;

        private Marker trackingMarker;

        public void reset() {
            resetMarkers();
            start = SystemClock.uptimeMillis();
            currentIndex = 0;
            endLatLng = getEndLatLng();
            beginLatLng = getBeginLatLng();

        }

        public void stopAnimation() {
            animating=false;
            mHandler.removeCallbacks(animator);

        }

        public void initialize(boolean showPolyLine) {
            reset();
            this.showPolyline = showPolyLine;

            highLightMarker(0);

            if (showPolyLine) {
                polyLine = initializePolyLine();
            }

            // We first need to put the camera in the correct position for the first run (we need 2 markers for this).....
            LatLng markerPos = latLngs.get(0);
            LatLng secondPos = latLngs.get(1);

            setInitialCameraPosition(markerPos, secondPos);

        }

        private void setInitialCameraPosition(LatLng markerPos,
                                              LatLng secondPos) {

            float bearing = GoogleMapUtis.bearingBetweenLatLngs(markerPos,secondPos);

            trackingMarker = googleMap.addMarker(new MarkerOptions().position(markerPos)
                    .title("title")
                    .snippet("snippet")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.drivers)));


            float mapZoom = googleMap.getCameraPosition().zoom >=16 ? googleMap.getCameraPosition().zoom : 16;

            CameraPosition cameraPosition =
                    new CameraPosition.Builder()
                            .target(markerPos)
                            .bearing(bearing + BEARING_OFFSET)
                            .tilt(90)
                            .zoom(mapZoom)
                            .build();

            googleMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(cameraPosition),
                    ANIMATE_SPEEED_TURN,
                    new CancelableCallback() {

                        @Override
                        public void onFinish() {
                            System.out.println("finished camera");
                            animator.reset();
                            Handler handler = new Handler();
                            handler.post(animator);

                        }

                        @Override
                        public void onCancel() {
                            System.out.println("cancelling camera");
                        }
                    }
            );
        }

        private Polyline polyLine;
        private PolylineOptions rectOptions = new PolylineOptions();


        private Polyline initializePolyLine() {
            //polyLinePoints = new ArrayList<LatLng>();
            rectOptions.add(latLngs.get(0));
            return googleMap.addPolyline(rectOptions);
        }

        /**
         * Add the marker to the polyline.
         */
        private void updatePolyLine(LatLng latLng) {
            List<LatLng> points = polyLine.getPoints();
            points.add(latLng);
            polyLine.setPoints(points);

        }

        public void startAnimation(boolean showPolyLine,List<LatLng> latLngs) {
            if (trackingMarker!=null) {
                trackingMarker.remove();
            }
            this.animating = true;
            this.latLngs=latLngs;
            if (latLngs.size()>2) {
                initialize(showPolyLine);
            }

        }

        public boolean isAnimating() {
            return this.animating;
        }


        @Override
        public void run() {

            long elapsed = SystemClock.uptimeMillis() - start;
            double t = interpolator.getInterpolation((float)elapsed/ANIMATE_SPEEED);
            LatLng intermediatePosition = SphericalUtil.interpolate(beginLatLng, endLatLng, t);

            Double mapZoomDouble = 18.5-( Math.abs((0.5- t))*5);
            float mapZoom =  mapZoomDouble.floatValue();

            System.out.println("mapZoom = " + mapZoom);

            trackingMarker.setPosition(intermediatePosition);

            if (showPolyline) {
                updatePolyLine(intermediatePosition);
            }

            if (t< 1) {
                mHandler.postDelayed(this, 16);
            } else {


                //ETO YUNG GUMAGALAW NA MARKER
                System.out.println("Move to next marker.... current = " + currentIndex + " and size = " + latLngs.size());
                // imagine 5 elements -  0|1|2|3|4 currentindex must be smaller than 4
                if (currentIndex<latLngs.size()-2) {

                    currentIndex++;

                    endLatLng = getEndLatLng();
                    beginLatLng = getBeginLatLng();


                    start = SystemClock.uptimeMillis();

                    Double heading = SphericalUtil.computeHeading(beginLatLng, endLatLng);

                    highLightMarker(currentIndex);

                    CameraPosition cameraPosition =
                            new CameraPosition.Builder()
                                    .target(endLatLng)
                                    .bearing(heading.floatValue() /*+ BEARING_OFFSET*/) // .bearing(bearingL  + BEARING_OFFSET)
                                    .tilt(tilt)
                                    .zoom(googleMap.getCameraPosition().zoom)
                                    .build();

                    googleMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(cameraPosition),
                            ANIMATE_SPEEED_TURN,
                            null
                    );

                    //start = SystemClock.uptimeMillis();
                    mHandler.postDelayed(this, 16);

                } else {
                    currentIndex++;
                    highLightMarker(currentIndex);
                    googleMap.clear();
                    stopAnimation();
                    googleMap.addMarker(new MarkerOptions()
                            .position(endLatLng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.kikomarke1r11))

                            .anchor(0.5f, 1))

                    ;
                }

            }
        }



        private LatLng getEndLatLng()
        {



            return latLngs.get(currentIndex+1);



        }

        private LatLng getBeginLatLng() {
            return latLngs.get(currentIndex);
        }

    };

    /**
     * Highlight the marker by index.
     */
    private void highLightMarker(int index) {
        if (markers.size()>=index+1) {
            highLightMarker(markers.get(index));
        }
    }

    /**
     * Highlight the marker by marker.
     */
    private void highLightMarker(Marker marker) {

        if (marker!=null) {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            marker.showInfoWindow();
        }

    }

    private void resetMarkers() {
        for (Marker marker : this.markers) {
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.drivers));
        }
    }

}
