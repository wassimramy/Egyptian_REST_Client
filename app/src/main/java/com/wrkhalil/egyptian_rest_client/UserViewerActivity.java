package com.wrkhalil.egyptian_rest_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserViewerActivity extends Activity {

    private TextView nameTextView, usernameTextView, emailTextView, phoneTextView, websiteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0); //get the URI value from the previous activity

        setContentView(R.layout.activity_user_viewer);
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

}
