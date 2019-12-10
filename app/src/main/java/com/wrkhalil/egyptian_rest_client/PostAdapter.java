package com.wrkhalil.egyptian_rest_client;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    //Attributes
    Context context;
    List<Post> postsList;
    public OnPostAdapterItemClickListener onPostAdapterItemClickListener;

    //PostAdapter constructor
    public PostAdapter (Context context, List<Post> postsList, OnPostAdapterItemClickListener onPostAdapterItemClickListener){
        this.postsList = postsList; //Fetch postsList
        this.context = context; //Fetch context
        this.onPostAdapterItemClickListener = onPostAdapterItemClickListener; //Fetch onPostAdapterItemClickListener
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false); //Inflate the "row" layout
        return new MyViewHolder(view); //Return the recycler view list
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, final int position) {
        holder.itemTitleTextView.setText(postsList.get(position).getTitle()); //Display the post title
        holder.itemAuthorNameTextView.setText( "By: " + BaseApplication.usersList.get(postsList.get(position).getUserId()-1).getUsername() ); //Display the author's username
        holder.itemPostIDTextView.setText( "Post ID: " + postsList.get(position).getId()); //Display the post id
        holder.itemView.setOnClickListener(v -> onPostAdapterItemClickListener.onItemClicked(position)); //return the position when the item is clicked
    }

    public int getItemCount(){
        return postsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitleTextView, itemPostIDTextView, itemAuthorNameTextView;

        public MyViewHolder (View itemView){
            super(itemView);

            //TextViews declaration
            itemTitleTextView = itemView.findViewById(R.id.itemTitleTextView);
            itemAuthorNameTextView = itemView.findViewById(R.id.itemAuthorNameTextView);
            itemPostIDTextView = itemView.findViewById(R.id.itemPostIDTextView);
        }
    }

}
