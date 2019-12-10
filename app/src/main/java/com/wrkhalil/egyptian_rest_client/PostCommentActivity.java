package com.wrkhalil.egyptian_rest_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wrkhalil.egyptian_rest_client.BaseApplication.commentsList;
import static com.wrkhalil.egyptian_rest_client.BaseApplication.jsonPlaceHolderApi;

public class PostCommentActivity extends Activity {

    TextView commentEmailTextView, commentTitleTextView, commentBodyTextView;
    int postId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        Intent intent = getIntent();
        postId = intent.getIntExtra ("postId", 0); //get the postId value from the previous activity to post the comment in the right post

        //TextViews declaration
        commentEmailTextView = findViewById(R.id.commentEmailTextView);
        commentTitleTextView = findViewById(R.id.commentTitleTextView);
        commentBodyTextView = findViewById(R.id.commentBodyTextView);

    }

    //When the user taps the back button
    public void returnToPost(View view) {
        Intent intent = new Intent(this, PostViewerActivity.class);
        intent.putExtra("postId", postId); //Sends the postId value to the PostViewerActivity to show the post where the comment was made
        startActivity(intent); //Start the activity
        this.finish(); //The user won't be able to go back to this layout again
    }

    //When the user taps the save button
    public void postComment(View view) {

        Comment comment = new Comment(postId,
                commentTitleTextView.getText().toString(), //Retrieve comment title
                commentEmailTextView.getText().toString(), //Retrieve the user's email address
                commentBodyTextView.getText().toString()); //Retrieve the comment's body

        Log.d("Comment Instance", comment.getId() + " " +
                                            comment.getPostId() + " " +
                                            comment.getName() + " " +
                                            comment.getEmail() + " " +
                                            comment.getBody() + " " );

        Call<Comment> callComment = jsonPlaceHolderApi.postComment(comment);
        callComment.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                BaseApplication.commentsList.add(response.body()); //Add the new comment posted to the comment list to display it
                Toast.makeText(getApplicationContext(), "Comment " + commentsList.get(commentsList.size()-1).getId() + " is posted successfully!", Toast.LENGTH_SHORT).show();
                returnToPost(view); //Switch back to the post to view the new comment posted
                Log.d("Comment Feedback", "Successful!"); //If the HTTP request has succeeded, display this log line
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.d("Comment Feedback", "Failed!"); //If the HTTP request has failed, display this log line
            }
        });
    }
}
