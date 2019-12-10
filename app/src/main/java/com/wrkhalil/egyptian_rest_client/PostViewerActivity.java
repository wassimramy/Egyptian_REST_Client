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
    private int userId, postId  = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        postId = intent.getIntExtra ("postId", 0); //get the postId value from the PostsActivity activity
        populateCommentsList(); //Retrieve all the comments posted to this post specifically

        setContentView(R.layout.activity_post_viewer);
        recyclerView = findViewById(R.id.commentsRecyclerViewer);

        //TextViews declaration
        itemAuthorNameTextView = findViewById(R.id.itemAuthorNameTextView);
        itemBodyTextView = findViewById(R.id.itemBodyTextView);
        itemTitleTextView = findViewById(R.id.itemTitleTextView);

        retrievePostInformation(); //Retrieve all posts information


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

    //Fires when the activity is started to fetch all the data related to this post
    private void retrievePostInformation(){
        for (int i = 0 ; i < BaseApplication.postsList.size() ; i++){
            if (BaseApplication.postsList.get(i).getId() == postId){
                userId = BaseApplication.postsList.get(i).getUserId(); //Retrieve the userId
                itemTitleTextView.setText(BaseApplication.postsList.get(i).getTitle()); //Retrieve the title
                itemBodyTextView.setText(BaseApplication.postsList.get(i).getBody()); //Retrieve the body
                itemAuthorNameTextView.setText("By: " + retrieveUsername()); //Retrieve the username from retrieveUsername()
                break;
            }
        }
    }

    //Starts when the post's data is retrieved to grab the username from the usersList since this information is not listed in post instances
    private String retrieveUsername(){
        for (int i = 0 ; i < BaseApplication.usersList.size(); i++){
            if (BaseApplication.usersList.get(i).getId() == userId){
                return BaseApplication.usersList.get(i).getUsername(); //Retrieve the actual username
            }
        }
        return "null"; //In case something went wrong the function will return "null". This shouldn't happen.
    }

    //Called to retrieve all the comments posted to this post specifically
    private void populateCommentsList(){
        for (int i = 0 ; i < BaseApplication.commentsList.size() ; i++){
            if (BaseApplication.commentsList.get(i).getPostId() == postId){
                commentsList.add(BaseApplication.commentsList.get(i)); //Add a comment to the post's comment list
            }
        }
    }

    //Fires when the user taps on a comment
    private void showPostActivity(int position){
    }

    //Fires when the user taps on the username of the post's author.
    public void showUserViewer(View view) {
        Intent intent = new Intent(this, UserViewerActivity.class);
        intent.putExtra("userId", userId); //Sends the userId value to the UserViewerActivity to display the user's information
        startActivity(intent); //Start the activity
    }

    //Starts when the user taps on the postNewComment button
    public void showPostComment(View view) {
        Intent intent = new Intent(this, PostCommentActivity.class);
        intent.putExtra("postId", postId); //Sends the postId value to the PostCommentActivity to post a comment on this post
        startActivity(intent); //Start the activity
    }
}
