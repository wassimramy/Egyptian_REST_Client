package com.wrkhalil.egyptian_rest_client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.toolbox.DiskBasedCache;

public class PostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_posts);
        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Retrieve the position of the item clicked in the recycleView and send it to PostViewerActivity to show the respective post information + comments
        PostAdapter postAdapter = new PostAdapter(this, BaseApplication.postsList, this::showPostActivity);
        recyclerView.setAdapter(postAdapter); //Update the recyclerView

    }

    //Starts when the user taps the recyclerView
    private void showPostActivity(int position){
        Intent intent = new Intent(this, PostViewerActivity.class);
        intent.putExtra("postId", BaseApplication.postsList.get(position).getId()); //Sends the postId value to the PostViewerActivity to display the selected post
        startActivity(intent); //Start the activity
    }
}
