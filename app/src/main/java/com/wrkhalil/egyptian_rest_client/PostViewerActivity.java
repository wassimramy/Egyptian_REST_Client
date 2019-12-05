package com.wrkhalil.egyptian_rest_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostViewerActivity extends AppCompatActivity {

    private List<Comment> commentsList = new ArrayList<>();
    private TextView itemTitleTextView, itemBodyTextView, itemAuthorNameTextView;
    private int userId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int postId = intent.getIntExtra ("postId", 0); //get the URI value from the previous activity
        populateCommentsList(postId);

        setContentView(R.layout.activity_post_viewer);
        recyclerView = findViewById(R.id.commentsRecyclerViewer);

        itemAuthorNameTextView = findViewById(R.id.itemAuthorNameTextView);
        itemBodyTextView = findViewById(R.id.itemBodyTextView);
        itemTitleTextView = findViewById(R.id.itemTitleTextView);

        retrievePostInformation(postId);


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

    private void retrievePostInformation(int postId){
        for (int i = 0 ; i < BaseApplication.postsList.size() ; i++){
            if (BaseApplication.postsList.get(i).getId() == postId){
                userId = BaseApplication.postsList.get(i).getUserId();
                itemTitleTextView.setText(BaseApplication.postsList.get(i).getTitle());
                itemBodyTextView.setText(BaseApplication.postsList.get(i).getBody());
                itemAuthorNameTextView.setText("By: " + retrieveUsername());
                break;
            }
        }
    }

    private String retrieveUsername(){

        for (int i = 0 ; i < BaseApplication.usersList.size(); i++){
            if (BaseApplication.usersList.get(i).getId() == userId){
                return BaseApplication.usersList.get(i).getUsername();
            }
        }
        return "null";
    }



    private void populateCommentsList(int postId){
        for (int i = 0 ; i < BaseApplication.commentsList.size() ; i++){
            if (BaseApplication.commentsList.get(i).getPostId() == postId){
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

    public void showUserViewer(View view) {
        Intent intent = new Intent(this, UserViewerActivity.class);
        intent.putExtra("userId", userId); //Sends the URI value to the ShowPictureActivity to fetch the picture
        startActivity(intent); //Start the activity
    }
}
