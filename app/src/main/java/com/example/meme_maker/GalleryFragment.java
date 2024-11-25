package com.example.meme_maker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private List<Item> memeList;
    private RecyclerView galleryRecyclerView;
    private GalleryItemRecyclerViewAdapter adapter;

    public GalleryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GalleryFragment newInstance(int columnCount) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_list, container, false);
        memeList = new ArrayList<>();

        memeList.add(new Item("kép1", "kép1szövege" ));
        memeList.add(new Item("kép2", "kép2szövege" ));
        memeList.add(new Item("kép3", "kép3szövege" ));

        galleryRecyclerView = view.findViewById(R.id.templatesRecyclerView);
        adapter = new GalleryItemRecyclerViewAdapter(memeList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        galleryRecyclerView.setLayoutManager(layoutManager);
        galleryRecyclerView.setAdapter(adapter);


        // Set the adapter
        /*if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new GalleryItemRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }*/
        return view;
    }
}