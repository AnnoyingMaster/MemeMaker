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

        memeList.add(new Item( R.drawable.memetemplate_3, "" ));
        memeList.add(new Item( R.drawable.memetemplate_5, "" ));
        memeList.add(new Item( R.drawable.memetemplate_6, "" ));
        memeList.add(new Item( R.drawable.memetemplate_7, "" ));
        memeList.add(new Item( R.drawable.memetemplate_8, "" ));
        memeList.add(new Item( R.drawable.memetemplate_9, "" ));
        memeList.add(new Item( R.drawable.memetemplate_11, "" ));
        memeList.add(new Item( R.drawable.memetemplate_12, "" ));
        memeList.add(new Item( R.drawable.memetemplate_13, "" ));
        memeList.add(new Item( R.drawable.memetemplate_14, "" ));
        memeList.add(new Item( R.drawable.memetemplate_15, "" ));
        memeList.add(new Item( R.drawable.memetemplate_16, "" ));
        memeList.add(new Item( R.drawable.memetemplate_17, "" ));
        memeList.add(new Item( R.drawable.memetemplate_18, "" ));
        memeList.add(new Item( R.drawable.memetemplate_19, "" ));
        memeList.add(new Item( R.drawable.memetemplate_20, "" ));
        memeList.add(new Item( R.drawable.memetemplate_21, "" ));






        galleryRecyclerView = view.findViewById(R.id.templatesRecyclerView);

        // Kattintás kezelése manuálisan (lambda helyett)
        GalleryItemRecyclerViewAdapter.OnGalleryClickListener listener = new GalleryItemRecyclerViewAdapter.OnGalleryClickListener() {
            @Override
            public void onGalleryClick(int imageResId) {
                // Kiválasztott sablon megnyitása az editorban
                openEditorFragment(imageResId);
            }
        };

        adapter = new GalleryItemRecyclerViewAdapter(memeList, listener);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        galleryRecyclerView.setLayoutManager(layoutManager);
        galleryRecyclerView.setAdapter(adapter);


        return view;
    }
    private void openEditorFragment(int imageResId) {
        MemeEditorFragment editorFragment = new MemeEditorFragment();
        Bundle args = new Bundle();
        args.putInt("TEMPLATE_IMAGE", imageResId); // A kiválasztott sablonkép ID-t átadjuk
        editorFragment.setArguments(args);

        ((MainActivity)getActivity()).changeTitle("edit");

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, editorFragment) // Editor fragmentbe cseréljük
                .addToBackStack(null)
                .commit();
    }
}