package com.wrkhalil.egyptian_rest_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserViewerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position", 0); //get the URI value from the previous activity

        setContentView(R.layout.activity_post_viewer);
        recyclerView = findViewById(R.id.commentsRecyclerViewer);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Retrieve the position of the item clicked in the recycleView and send it to startItemEditActivity to show the respective item information
        PostAdapter postAdapter = new PostAdapter(this, fetchUserPost(), this::showPostActivity);
        recyclerView.setAdapter(postAdapter); //Update the recyclerView

    }

    private void showPostActivity(int position){
        Intent intent = new Intent(this, PostViewerActivity.class);
        intent.putExtra("Position", position); //Sends the URI value to the ShowPictureActivity to fetch the picture
        startActivity(intent); //Start the activity
    }

    private ArrayList<Post> fetchUserPost(){

        ArrayList<Post> posts = new ArrayList<>();



        return posts;
    }

}
