package com.wrkhalil.egyptian_rest_client;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserViewerActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private TextView nameTextView, usernameTextView, emailTextView, phoneTextView, websiteTextView;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private int userId;
    private LatLng userLocation;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", 0); //get the URI value from the previous activity

        setContentView(R.layout.activity_user_viewer);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this); //Handles the location
        setupMapFragment(); //Setup the map fragment in the activity

        recyclerView = findViewById(R.id.postsRecyclerViewer);


        nameTextView = findViewById(R.id.nameTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        websiteTextView = findViewById(R.id.websiteTextView);

        retrieveUserInformation(userId);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Retrieve the position of the item clicked in the recycleView and send it to startItemEditActivity to show the respective item information
        PostAdapter postAdapter = new PostAdapter(this, fetchUserPost(userId), this::showPostActivity);
        recyclerView.setAdapter(postAdapter); //Update the recyclerView

    }

    private void retrieveUserInformation(int userId){
        for (int i = 0 ; i < BaseApplication.usersList.size() ; i++){
            if (BaseApplication.usersList.get(i).getId() == userId){
                nameTextView.       setText("Name: " + BaseApplication.usersList.get(i).getName());
                usernameTextView.   setText("Username: " + BaseApplication.usersList.get(i).getUsername());
                emailTextView.      setText("Email: " + BaseApplication.usersList.get(i).getEmail());
                phoneTextView.      setText("Phone: " + BaseApplication.usersList.get(i).getPhone());
                websiteTextView.    setText("Website : " + BaseApplication.usersList.get(i).getWebsite());
                userLocation = new LatLng(BaseApplication.usersList.get(i).getAddress().getGeo().getLat(),
                        BaseApplication.usersList.get(i).getAddress().getGeo().getLng());
                break;
            }
        }
    }

    private void showPostActivity(int position){
        Intent intent = new Intent(this, PostViewerActivity.class);
        intent.putExtra("postId", BaseApplication.postsList.get(position).getId()); //Sends the URI value to the ShowPictureActivity to fetch the picture
        startActivity(intent); //Start the activity
    }

    private ArrayList<Post> fetchUserPost(int userId){

        ArrayList<Post> posts = new ArrayList<>();

        for (int i = 0; i < BaseApplication.postsList.size(); i++){
            if (BaseApplication.postsList.get(i).getUserId() == userId){
                posts.add(BaseApplication.postsList.get(i));
            }
        }
        return posts;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap; //Link the map to mMap variable
        mMap.setOnMarkerClickListener(this); //Link to the onClick listener
        // Set the current location to the latitude and longitude of the image
        Marker marker = mMap.addMarker(new MarkerOptions().position(userLocation)); //Place marker on the map in the currentLocation position
        marker.setTitle("User Location"); //Set the title of the marker to location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 2)); //Move the camera to where the currentLocationMarker is located
    }

    //Setup the map fragment in the activity
    public void setupMapFragment (){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Return the current state of the permissions needed.
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    // request permissions from the user
    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i("requestPermissions", "Displaying permission rationale to provide additional context.");
            startLocationPermissionRequest();

        } else {
            Log.i("requestPermissions", "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }

    // request permissions from the user
    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(UserViewerActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onStart() {
        super.onStart();

        /*
        //Check the location permissions
        if (!checkPermissions()) {
            requestPermissions(); //Request permissions for location services if the user didn't grant them
        } else {
            //getLastLocation(); //Acquire the last location if the user has already granted permission to access location services
        }
         */
    }
}
