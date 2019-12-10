package com.wrkhalil.egyptian_rest_client;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class UserViewerActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private TextView nameTextView, usernameTextView, emailTextView, phoneTextView, websiteTextView;
    private LatLng userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0); //get the URI value from the previous activity

        setContentView(R.layout.activity_user_viewer);

        setupMapFragment(); //Setup the map fragment in the activity

        recyclerView = findViewById(R.id.postsRecyclerViewer);

        //TextViews declaration
        nameTextView = findViewById(R.id.nameTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        websiteTextView = findViewById(R.id.websiteTextView);

        retrieveUserInformation(userId); //Retrieve the user information for the received userId


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostAdapter postAdapter = new PostAdapter(this, fetchUserPost(userId), this::showPostActivity);
        recyclerView.setAdapter(postAdapter); //Update the recyclerView

    }

    //Called to retrieve the user information and display it in the respective text views
    private void retrieveUserInformation(int userId){
        for (int i = 0 ; i < BaseApplication.usersList.size() ; i++){
            if (BaseApplication.usersList.get(i).getId() == userId){
                nameTextView.       setText("Name: " + BaseApplication.usersList.get(i).getName()); //Fetch the user's name
                usernameTextView.   setText("Username: " + BaseApplication.usersList.get(i).getUsername()); //Fetch the user's username
                emailTextView.      setText("Email: " + BaseApplication.usersList.get(i).getEmail()); //Fetch the user's email
                phoneTextView.      setText("Phone: " + BaseApplication.usersList.get(i).getPhone()); //Fetch the user's phone
                websiteTextView.    setText("Website : " + BaseApplication.usersList.get(i).getWebsite()); //Fetch the user's website
                userLocation = new LatLng(BaseApplication.usersList.get(i).getAddress().getGeo().getLat(),
                        BaseApplication.usersList.get(i).getAddress().getGeo().getLng()); //Fetch the user's location
                break;
            }
        }
    }

    private void showPostActivity(int position){
        Intent intent = new Intent(this, PostViewerActivity.class);
        intent.putExtra("postId", BaseApplication.postsList.get(position).getId()); //Sends the postId value to the PostViewerActivity to display the selected post
        startActivity(intent); //Start the activity
    }

    //Called to fetch the user's post
    private ArrayList<Post> fetchUserPost(int userId){
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < BaseApplication.postsList.size(); i++){
            if (BaseApplication.postsList.get(i).getUserId() == userId){
                posts.add(BaseApplication.postsList.get(i)); //Add post to the posts list if the userId is the same
            }
        }
        return posts; //Return the posts list to the adapter
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap; //Link the map to mMap variable
        mMap.setOnMarkerClickListener(this); //Link to the onClick listener
        Marker marker = mMap.addMarker(new MarkerOptions().position(userLocation)); //Place marker on the map in the userLocation position
        marker.setTitle("User Location"); //Set the title of the marker to location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 2)); //Move the camera to where the userLocation is located
    }

    //Setup the map fragment in the activity
    public void setupMapFragment (){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
