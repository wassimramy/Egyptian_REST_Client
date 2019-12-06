package com.wrkhalil.egyptian_rest_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wrkhalil.egyptian_rest_client.BaseApplication.jsonPlaceHolderApi;

public class MainActivity extends AppCompatActivity {

    private boolean usersFetched, commentsFetched, postsFetched = false;
    private TextView usersDownloadStatusTextView;
    private TextView commentsDownloadStatusTextView;
    private TextView postsDownloadStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        postsDownloadStatusTextView = findViewById(R.id.postsDownloadStatusTextView);
        usersDownloadStatusTextView = findViewById(R.id.usersDownloadStatusTextView);
        commentsDownloadStatusTextView = findViewById(R.id.commentsDownloadStatusTextView );

        Call<List<Post>> callPosts = jsonPlaceHolderApi.getPosts();
        callPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                BaseApplication.postsList.addAll(response.body());
                setPostsFetched(true);
                showPostsActivity();
                postsDownloadStatusTextView.setText("All posts have been downloaded successfully!");
                Log.d("Fetching Posts", "Successful!");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Log.d("Fetching Posts", "Failed!");
            }
        });

        Call<List<User>> callUsers = jsonPlaceHolderApi.getUsers();
        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                BaseApplication.usersList.addAll(response.body());
                setUsersFetched(true);
                showPostsActivity();
                usersDownloadStatusTextView.setText("All Users have been downloaded successfully!");
                Log.d("Fetching Users", "Successful!");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Log.d("Fetching Users", "Failed!");
            }
        });

        Call<List<Comment>> callComments = jsonPlaceHolderApi.getComments();
        callComments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                BaseApplication.commentsList.addAll(response.body());
                setCommentsFetched(true);
                showPostsActivity();
                commentsDownloadStatusTextView.setText("All Comments have been downloaded successfully!");
                Log.d("Fetching Comments", "Successful!");
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                Log.d("Fetching Comments", "Failed!");
            }
        });

        Log.d("Response:", "Class is instantiated\n");

    }

    public void showPostsActivity(){
        if (usersFetched && commentsFetched && postsFetched){
            Intent intent = new Intent(this, PostsActivity.class);
            startActivity(intent); //Start the activity
            this.finish();
        }
    }

    public void setPostsFetched(boolean postsFetched) {
        this.postsFetched = postsFetched;
    }

    public void setCommentsFetched(boolean commentsFetched) {
        this.commentsFetched = commentsFetched;
    }

    public void setUsersFetched(boolean usersFetched) {
        this.usersFetched = usersFetched;
    }
}
