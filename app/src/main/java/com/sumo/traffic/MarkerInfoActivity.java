package com.sumo.traffic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;

public class MarkerInfoActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ProgressDialog progressDialog;
    GoogleApiClient mGoogleApiClient;
   static TextView placeNameTV, placeTypeTV, placeAddressTV, placeNumberTV,placeIsOpenTV,placeOpeningHoursTV;
    private RatingBar ratingBar;
    private RelativeLayout pagerLayout;
    private String placeId;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter recyclerAdapter;
    public RecyclerView.LayoutManager layoutManager;
    private List<String> reviewAuthorNames = new ArrayList<>(), authorTexts = new ArrayList<>();
    private Map<String,String> profilePictureUrl = new HashMap<>();
    private List<Integer> reviewRating = new ArrayList<>();
    String placeName, placeAddress, phoneNumber;
    List<Integer> placeType = new LinkedList<>();
    float rating;
    ArrayList<BitmapDrawable> bitmapDrawables = new ArrayList<>();

    PlacePhotoMetadataBuffer photoMetadataBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO key metadata changed in the manifest with android.geo
        //TODO compiled new modules
        //TODO SHOW ATTRIBUTIONS FROM PLACES AND IMAGES
        //TODO updated google library
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_info);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data, Please Wait...");
        progressDialog.show();
        buildGoogleApiClient();
        pagerLayout = (RelativeLayout) findViewById(R.id.relative_pager);
        pagerLayout.setVisibility(View.GONE);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
        placeId = intent.getStringExtra("placeId");
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                            placeNameTV = (TextView) findViewById(R.id.place_name_text);
                            placeTypeTV = (TextView) findViewById(R.id.place_type_text);
                            placeAddressTV = (TextView) findViewById(R.id.place_address_text);
                            placeNumberTV = (TextView) findViewById(R.id.place_number_text);
                            placeIsOpenTV=(TextView) findViewById(R.id.place_is_open);
                            placeOpeningHoursTV=(TextView) findViewById(R.id.place_opening_hours);
                            viewPager = (ViewPager) findViewById(R.id.viewPager);
                            final Place placeFound = places.get(0);
                            //THE BELOW ATTRIBUTION HAVE TO BE PLACED SOMEWHERE IN A TEXTVIEW BECAUSE OF GOOGLE POLICY
                            final CharSequence placeAttribution = placeFound.getAttributions();
                            placeName = placeFound.getName().toString();
                            placeAddress = placeFound.getAddress().toString();
                            phoneNumber = placeFound.getPhoneNumber().toString();
                            placeType = placeFound.getPlaceTypes();
                            rating = placeFound.getRating();
                            placeNameTV.setText(placeName);
                            placeAddressTV.setText(placeAddress);
                            placeNumberTV.setText(phoneNumber);
                            ratingBar.setRating(rating);
                            String placeType = placeTypeFilter();
                            placeTypeTV.setText(placeType);
                            places.release();
                        }
                    }
                });
        FetchOpeningTimes fetchOpeningTimes = new FetchOpeningTimes();
        fetchOpeningTimes.execute();
        FetchReviews fetchReviews = new FetchReviews();
        fetchReviews.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FetchImage downloadImage = new FetchImage();
        downloadImage.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //if list is not cleared, memory is quickly consumed
        bitmapDrawables = new ArrayList<>();
        reviewAuthorNames = new ArrayList<>();
        authorTexts = new ArrayList<>();
        profilePictureUrl = new HashMap<>();
     reviewRating = new ArrayList<>();
        if (viewPager != null) viewPager = new ViewPager(this);
        photoMetadataBuffer.release();
      mGoogleApiClient.disconnect();

    }

    public void openNumberDialer(View view) {
        if (placeNumberTV.getText().equals(getResources().getString(android.R.string.emptyPhoneNumber)))
            return;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + placeNumberTV.getText()));
        startActivity(intent);
    }

    private class FetchOpeningTimes extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid="
                    + placeId + "&key=AIzaSyDK1zxUEp38e6sQYzJq6qGNKxdOUqUZR1Y";
            JSONParser jsonParser = new JSONParser();
            String json = jsonParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(String json){
            try {
                final JSONObject jsonObject = new JSONObject(json);
                JSONObject result = jsonObject.getJSONObject("result");
                if(result.has("opening_hours")){
                    JSONObject openingHours =result.getJSONObject("opening_hours");
                    String isOpen = (openingHours.getBoolean("open_now"))? "Now opened":"Now closed";
                    placeIsOpenTV.setText(isOpen);
                    JSONArray openingDays = openingHours.getJSONArray("periods");
                    StringBuilder openingHoursDays =new StringBuilder();
                    openingHoursDays.append("Opening hours:\n");
                    for(int i = 0;i<openingDays.length();i++){
                        JSONObject open = openingDays.getJSONObject(i);
                        String openingDay = filterDays(open.getJSONObject("open").getInt("day"));
                        String jsonCloseTime = open.getJSONObject("close").getString("time");
                        String jsonOpenTime = open.getJSONObject("open").getString("time");
                        String closingTime = (jsonCloseTime.substring(0,2))+":"+(jsonCloseTime.substring(2));
                        String openingTime = (jsonOpenTime.substring(0,2))+":"+(jsonOpenTime.substring(2));
                        if((openingDays.length()-1) == i){
                            openingHoursDays.append(openingDay+openingTime+"-"+closingTime);
                        }else openingHoursDays.append(openingDay+openingTime+"-"+closingTime+", ");
                    }
                    placeOpeningHoursTV.setText(openingHoursDays);
                }
            } catch (Exception e){}
        }
        private String filterDays(int day){
            switch (day){
                case 0:
                    return "Sunday ";
                case 1:
                    return "Monday ";
                case 2:
                    return "Tuesday ";
                case 3:
                    return "Wednesday ";
                case 4:
                    return "Thursday ";
                case 5:
                    return "Friday ";
                case 6:
                    return "Saturday ";
                default:
                    return "";
            }
        }
    }
    private class FetchImage extends AsyncTask<Void, Void, ArrayList<BitmapDrawable>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<BitmapDrawable> doInBackground(Void... args) {

            PlacePhotoMetadataResult result = Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, placeId)
                    .await();
            if (result != null && result.getStatus().isSuccess()) {
                photoMetadataBuffer = result.getPhotoMetadata();
            } else {
                return null; //here you didn't get any photo for this place, what image do you want to show?
            }
            if(photoMetadataBuffer.getCount()==0) {
                if(progressDialog.isShowing()) progressDialog.dismiss();
                return null;
            }
            //we'll download three photos at most
            Log.d("MarkerInfo", "metadatabuffer.getcount: " + photoMetadataBuffer.getCount());
            for (int i = 0; i < photoMetadataBuffer.getCount(); i++) {
                PlacePhotoMetadata photo = photoMetadataBuffer.get(i);
                //THIS ATTRIBUTIONS MUST BE SHOWN SOMEWHERE IN A TEXTVIEW BECAUSE OF GOOGLE POLICY
                final CharSequence photoAttributions = photo.getAttributions();
                Bitmap image = photo.getScaledPhoto(mGoogleApiClient,300,300).await().getBitmap();
                BitmapDrawable drawable = new BitmapDrawable(MarkerInfoActivity.this.getResources(), image);
                bitmapDrawables.add(drawable);
                if (bitmapDrawables.size() == 3) {
                    break;
                }
            }
            photoMetadataBuffer.release();
            return bitmapDrawables;
        }

        @Override
        protected void onPostExecute(ArrayList<BitmapDrawable> bitmapDrawables) {
            try{
                if(bitmapDrawables.size()>0) {
                    if (progressDialog.isShowing()) progressDialog.dismiss();
                    pagerLayout.setVisibility(View.VISIBLE);
                    ImageSliderAdapter adapter = new ImageSliderAdapter();
                    viewPager.setAdapter(adapter);
                    circleIndicator.setViewPager(viewPager);
                }
            } catch (Exception e){}
        }
    }

    private class FetchReviews extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... args) {
            String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid="
                    + placeId + "&key=AIzaSyDK1zxUEp38e6sQYzJq6qGNKxdOUqUZR1Y";
            JSONParser jsonParser = new JSONParser();
            String json = jsonParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                final JSONObject jsonObject = new JSONObject(json);
                JSONObject result = jsonObject.getJSONObject("result");
                JSONArray reviews = result.getJSONArray("reviews");
                for (int i = 0; i < reviews.length(); i++) {
                    JSONObject singleReviews = reviews.getJSONObject(i);
                    JSONObject aspects = singleReviews.getJSONArray("aspects").getJSONObject(0);
                    reviewRating.add(aspects.getInt("rating"));
                    String authorName = singleReviews.getString("author_name");
                    reviewAuthorNames.add(authorName);
                    if(singleReviews.has("profile_photo_url")){
                        String profilePicture = singleReviews.getString("profile_photo_url");
                        String url ="http:".concat(profilePicture);
                        profilePictureUrl.put(authorName,url);
                    } else profilePictureUrl.put(authorName,null);
                    authorTexts.add(singleReviews.getString("text"));
                }
                layoutManager = new LinearLayoutManager(MarkerInfoActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerAdapter = new RecyclerAdapter(reviewAuthorNames, profilePictureUrl, authorTexts, reviewRating);
                recyclerView.setAdapter(recyclerAdapter);
            } catch (Exception e) {
                //couldnt fetch the reviews datas, what to do? I'll show an alert dialog for now
                AlertDialog.Builder builder = new AlertDialog.Builder(MarkerInfoActivity.this);
                builder.setTitle("Error retrieving reviews data")
                        .setMessage("This might either be because of a faulty internet connection or"
                                + " simply because reviews are not available for this place")
                        .setPositiveButton("Ok", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //you could do something if connection fails, for example
        AlertDialog.Builder builder = new AlertDialog.Builder(MarkerInfoActivity.this);
        builder.setTitle("Connection failed!")
                .setMessage("Please check your internet connection and try again");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String placeTypeFilter() {
        String placeTypeString = "";
        if (placeType.isEmpty()) return placeTypeString;
        Log.d("MarkerInfo", "place type: " + placeType);
        switch (placeType.get(0)) {
            //ADD PLACES TYPES HERE
            case Place.TYPE_AMUSEMENT_PARK:
                placeTypeString = "Amusement Park";
                break;
            case Place.TYPE_AIRPORT:
                placeTypeString = "Airport";
                break;
            case Place.TYPE_RESTAURANT:
                placeTypeString = "Restaurant";
                break;
            case Place.TYPE_MUSEUM:
                placeTypeString = "Museum";
                break;
            case Place.TYPE_REAL_ESTATE_AGENCY:
                placeTypeString = "Real State Agency";
                break;
            case Place.TYPE_INSURANCE_AGENCY:
                placeTypeString = "Insurance Agency";
                break;
            case Place.TYPE_TRAVEL_AGENCY:
                placeTypeString = "Travel Agency";
                break;
            case Place.TYPE_BUS_STATION:
                placeTypeString = "Bus Station";
                break;
            case Place.TYPE_CAR_RENTAL:
                placeTypeString = "Car Rental";
                break;
            case Place.TYPE_TAXI_STAND:
                placeTypeString = "Car Rental";
                break;
            case Place.TYPE_TRANSIT_STATION:
                placeTypeString = "Transit Station";
                break;
            case Place.TYPE_TRAIN_STATION:
                placeTypeString = "Train Station";
                break;
            case Place.TYPE_BAR:
                placeTypeString = "Bar";
                break;
            case Place.TYPE_BOWLING_ALLEY:
                placeTypeString = "Bowling Alley";
                break;
            case Place.TYPE_CAFE:
                placeTypeString = "Cafe";
                break;
            case Place.TYPE_CAMPGROUND:
                placeTypeString = "CampGround";
                break;

            case Place.TYPE_LODGING:
                placeTypeString = "Hotel";
                break;

            default:
                break;
        }
        return placeTypeString;
    }

    //custom adapter for the image slider
    private class ImageSliderAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return bitmapDrawables.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MarkerInfoActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageDrawable(bitmapDrawables.get(position));
            ((ViewPager) container).addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

    }

    public void street(View view)
    {
        Intent i = new Intent(MarkerInfoActivity.this , StreetMap.class);
        startActivity(i);
        finish();
    }



}
