package com.wrkhalil.egyptian_rest_client;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostViewerActivity extends AppCompatActivity {

    private int position = 0;
    private List<Comment> commentsList = new ArrayList<>();
    private TextView itemTitleTextView, itemBodyTextView, itemAuthorNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        position = intent.getIntExtra ("Position", 0); //get the URI value from the previous activity
        populateCommentsList();

        setContentView(R.layout.activity_post_viewer);
        recyclerView = findViewById(R.id.commentsRecyclerViewer);

        itemAuthorNameTextView = findViewById(R.id.itemAuthorNameTextView);
        itemBodyTextView = findViewById(R.id.itemBodyTextView);
        itemTitleTextView = findViewById(R.id.itemTitleTextView);

        itemTitleTextView.setText(BaseApplication.postsList.get(position).getTitle());
        itemBodyTextView.setText(BaseApplication.postsList.get(position).getBody());
        itemAuthorNameTextView.setText("By: " + BaseApplication.usersList.get(BaseApplication.postsList.get(position).getUserId()-1).getName());


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Retrieve the position of the item clicked in the recycleView and send it to startItemEditActivity to show the respective item information
        CommentAdapter commentAdapter = new CommentAdapter(this, commentsList, this::showPostActivity);
        recyclerView.setAdapter(commentAdapter); //Update the recyclerView

    }

    private void populateCommentsList(){
        for (int i = 0 ; i < BaseApplication.commentsList.size() ; i++){
            if (BaseApplication.commentsList.get(i).getPostId()-1 == position){
                commentsList.add(BaseApplication.commentsList.get(i));
            }
        }
    }

    private void showPostActivity(int position){
        //Intent intent = new Intent(this, PostViewerActivity.class);
        //intent.putExtra("Position", position); //Sends the URI value to the ShowPictureActivity to fetch the picture
        //startActivity(intent); //Start the activity
        this.finish();
    }
}
