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

    Context context;
    List<Post> postsList;
    List<Post> usersList;


    public OnPostAdapterItemClickListener onPostAdapterItemClickListener;

    public PostAdapter (Context context, List<Post> postsList, OnPostAdapterItemClickListener onPostAdapterItemClickListener){
        this.postsList = postsList;
        this.context = context;
        this.onPostAdapterItemClickListener = onPostAdapterItemClickListener;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, final int position) {

        holder.itemTitleTextView.setText(postsList.get(position).getTitle());
        holder.itemAuthorNameTextView.setText( "By: " + BaseApplication.usersList.get(postsList.get(position).getUserId()-1).getUsername() );
        holder.itemPostIDTextView.setText( "Post ID: " + postsList.get(position).getId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostAdapterItemClickListener.onItemClicked(position);
            }
        });
    }

    public int getItemCount(){
        return postsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitleTextView, itemPostIDTextView, itemAuthorNameTextView;

        public MyViewHolder (View itemView){
            super(itemView);
            itemTitleTextView = itemView.findViewById(R.id.itemTitleTextView);
            itemAuthorNameTextView = itemView.findViewById(R.id.itemAuthorNameTextView);
            itemPostIDTextView = itemView.findViewById(R.id.itemPostIDTextView);
        }
    }

}
