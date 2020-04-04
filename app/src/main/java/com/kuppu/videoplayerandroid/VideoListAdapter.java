package com.kuppu.videoplayerandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {
    private List<Video> videosList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView vimage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
           vimage=(ImageView)view.findViewById(R.id.image_video_item);
        }
    }
    public VideoListAdapter(List<Video> videosList) {
        this.videosList = videosList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.videolist_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Video videos = videosList.get(position);
        holder.title.setText(videos.getTitle());


    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

}
