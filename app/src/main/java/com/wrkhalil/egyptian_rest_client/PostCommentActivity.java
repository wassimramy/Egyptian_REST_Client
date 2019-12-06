package com.wrkhalil.egyptian_rest_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wrkhalil.egyptian_rest_client.BaseApplication.jsonPlaceHolderApi;

public class PostCommentActivity extends Activity {

    TextView commentEmailTextView, commentTitleTextView, commentBodyTextView;
    Button saveButton, cancelButton;
    int postId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        Intent intent = getIntent();
        postId = intent.getIntExtra ("postId", 0); //get the URI value from the previous activity

        commentEmailTextView = findViewById(R.id.commentEmailTextView);
        commentTitleTextView = findViewById(R.id.commentTitleTextView);
        commentBodyTextView = findViewById(R.id.commentBodyTextView);

    }

    public void returnToPost(View view) {
        Intent intent = new Intent(this, PostViewerActivity.class);
        intent.putExtra("postId", postId); //Sends the URI value to the ShowPictureActivity to fetch the picture
        startActivity(intent); //Start the activity
    }

    public void postComment(View view) {

        Comment comment = new Comment(postId,
                commentTitleTextView.getText().toString(),
                commentEmailTextView.getText().toString(),
                commentBodyTextView.getText().toString());

        Log.d("Comment Instance", comment.getId() + " " +
                                            comment.getPostId() + " " +
                                            comment.getName() + " " +
                                            comment.getEmail() + " " +
                                            comment.getBody() + " " );

        Call<Comment> callComment = jsonPlaceHolderApi.postComment(comment);
        callComment.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                BaseApplication.commentsList.add(response.body());
                returnToPost(view);
                Log.d("Comment Feedback", "Successful!");
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.d("Comment Feedback", "Failed!");
            }
        });
    }
}
