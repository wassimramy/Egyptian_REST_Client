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

    private boolean usersFetched, commentsFetched, postsFetched = false; //Variables to identify whether the users, comments, and posts are retrieved or not
    private TextView usersDownloadStatusTextView;
    private TextView commentsDownloadStatusTextView;
    private TextView postsDownloadStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //TextViews declaration
        postsDownloadStatusTextView = findViewById(R.id.postsDownloadStatusTextView);
        usersDownloadStatusTextView = findViewById(R.id.usersDownloadStatusTextView);
        commentsDownloadStatusTextView = findViewById(R.id.commentsDownloadStatusTextView );

        Call<List<Post>> callPosts = jsonPlaceHolderApi.getPosts();

        //Retrieve posts
        callPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                BaseApplication.postsList.addAll(response.body()); //Add all posts to the posts list
                setPostsFetched(true); //Set postsFetched to true
                showPostsActivity();  //Try to start the PostsActivity
                postsDownloadStatusTextView.setText("All posts have been downloaded successfully!"); //Notify the user that all posts were fetched successfully from the server
                Log.d("Fetching Posts", "Successful!"); //Notify the developer that all posts were fetched successfully from the server
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                setPostsFetched(false); //Set postsFetched to false
                postsDownloadStatusTextView.setText("Posts download has failed!"); //Notify the user that posts download has failed
                Log.d("Fetching Posts", "Failed!"); //Notify the developer that posts download has failed
            }
        });

        Call<List<User>> callUsers = jsonPlaceHolderApi.getUsers();

        //Retrieve Users
        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                BaseApplication.usersList.addAll(response.body()); //Add all users to the users list
                setUsersFetched(true); //Set usersFetched to true
                showPostsActivity(); //Try to start the PostsActivity
                usersDownloadStatusTextView.setText("All Users have been downloaded successfully!"); //Notify the user that all users were fetched successfully from the server
                Log.d("Fetching Users", "Successful!"); //Notify the developer that all users were fetched successfully from the server
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                setUsersFetched(false); //Set usersFetched to false
                usersDownloadStatusTextView.setText("Users download has failed!"); //Notify the user that users download has failed
                Log.d("Fetching Users", "Failed!"); //Notify the developer that users download has failed
            }
        });

        Call<List<Comment>> callComments = jsonPlaceHolderApi.getComments();

        //Retrieve comments
        callComments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                BaseApplication.commentsList.addAll(response.body()); //Add all comments to the comments list
                setCommentsFetched(true); //Set commentsFetched to true
                showPostsActivity(); //Try to start the PostsActivity
                commentsDownloadStatusTextView.setText("All Comments have been downloaded successfully!"); //Notify the user that all comments were fetched successfully from the server
                Log.d("Fetching Comments", "Successful!"); //Notify the developer that all comments were fetched successfully from the server
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                setCommentsFetched(false); //Set commentsFetched to false
                commentsDownloadStatusTextView.setText("Comments download has failed!"); //Notify the user that comments download has failed
                Log.d("Fetching Comments", "Failed!"); //Notify the developer that comments download has failed
            }
        });
    }

    //Fired after posts, users, comments are downloaded and parsed
    public void showPostsActivity(){
        if (usersFetched && commentsFetched && postsFetched){ //Check that all data are fetched
            Intent intent = new Intent(this, PostsActivity.class);
            startActivity(intent); //Start the activity
            this.finish(); //User can't go back to ths activity
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
