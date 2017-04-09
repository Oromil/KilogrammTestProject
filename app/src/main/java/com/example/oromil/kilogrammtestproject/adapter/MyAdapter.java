package com.example.oromil.kilogrammtestproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.oromil.kilogrammtestproject.R;
import com.example.oromil.kilogrammtestproject.realm.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oromil on 05.04.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<Song> songs;
    private Context context;
    private int lastPosition = -1; 

    public MyAdapter(List<Song>songs, Context context) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder newHolder = new ViewHolder(v);
        return newHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.label.setText(songs.get(position).getLabel());
            holder.author.setText(songs.get(position).getAuthor());
            setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        if (songs != null)
            return songs.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        FrameLayout frameLayout;
        TextView label;
        TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.frame_layout);
            label = (TextView) itemView.findViewById(R.id.tvLabel);
            author = (TextView) itemView.findViewById(R.id.tvAuthor);
        }
    }

    public void setSongs(List<Song> songs){
        this.songs = songs;
            lastPosition = -1;
    }

    public void setSongs(ArrayList<Song> realmSongs){
        this.songs = realmSongs;
            lastPosition = -1;
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.slid_in_up);
                viewToAnimate.startAnimation(animation1);
                if (position!=6)  //// FIXME: 09.04.2017 if lastPosition = 6, we get an error:"queuebuffer error queuing buffer to surfacetexture -19"
                    lastPosition = position;
                Log.d("Last position", lastPosition+"");

        }
    }

}
