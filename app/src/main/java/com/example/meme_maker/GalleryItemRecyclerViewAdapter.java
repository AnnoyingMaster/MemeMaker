package com.example.meme_maker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class GalleryItemRecyclerViewAdapter extends RecyclerView.Adapter<GalleryItemRecyclerViewAdapter.ViewHolder> {

    private List<MemeItem> memeFiles;

    public GalleryItemRecyclerViewAdapter(List<MemeItem> files) {
        this.memeFiles = files;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemeItem memeItem = memeFiles.get(position);
        holder.memeTextView.setText(memeItem.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(new File(memeItem.getImagePath()))
                .into(holder.memeImageView);
    }

    @Override
    public int getItemCount() {
        return memeFiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView memeTextView;
        ImageView memeImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            memeTextView = itemView.findViewById(R.id.memeTextView);
            memeImageView = itemView.findViewById(R.id.memeImageView);
        }
    }
}
