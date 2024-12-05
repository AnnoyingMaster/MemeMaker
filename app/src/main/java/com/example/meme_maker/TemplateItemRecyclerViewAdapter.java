package com.example.meme_maker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meme_maker.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.meme_maker.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;


public class TemplateItemRecyclerViewAdapter extends RecyclerView.Adapter<TemplateItemRecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final OnTemplateClickListener onTemplateClickListener;

    public TemplateItemRecyclerViewAdapter(List<Item> items, OnTemplateClickListener listener) {
        mValues = items;
        onTemplateClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gallery, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Item item = mValues.get(position);
        holder.memeTextView.setText(item.getTitle());
        holder.memeImageView.setImageResource(item.getImage());

        // Kattintás kezelése
        holder.itemView.setOnClickListener(v -> {
            if (onTemplateClickListener != null) {
                // Kiválasztott kép továbbítása
                onTemplateClickListener.onTemplateClick(item.getImage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView memeTextView;
        public final ImageView memeImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memeTextView = itemView.findViewById(R.id.memeTextView);
            memeImageView = itemView.findViewById(R.id.memeImageView);
        }
    }

    public interface OnTemplateClickListener {
        void onTemplateClick(int imageResId);
    }
}

