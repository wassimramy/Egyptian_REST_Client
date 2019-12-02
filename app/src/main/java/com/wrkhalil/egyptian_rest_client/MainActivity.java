package com.wrkhalil.egyptian_rest_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        OnlineDataFetcher postsDataFetcher = new OnlineDataFetcher("https://jsonplaceholder.typicode.com/posts/");

        //OnlineDataFetcher usersDataFetcher = new OnlineDataFetcher("https://jsonplaceholder.typicode.com/users");

        /*
                //Retrieve the position of the item clicked in the recycleView and send it to startItemEditActivity to show the respective item information
        PostAdapter postAdapter = new PostAdapter(this, BaseApplication.postsList, position -> {
            showPostActivity(position);
        });
        recyclerView.setAdapter(postAdapter); //Update the recyclerView
         */



    }

    private void showPostActivity(int position){
        //Intent intent = new Intent(this, PostViewerActivity.class);
        //intent.putExtra("Position", position); //Sends the URI value to the ShowPictureActivity to fetch the picture
        //startActivity(intent); //Start the activity
        this.finish();
    }
}
