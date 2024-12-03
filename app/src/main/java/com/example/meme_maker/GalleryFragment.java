package com.example.meme_maker;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView recyclerView;
    private GalleryItemRecyclerViewAdapter adapter;
    private List<MemeItem> memeItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        recyclerView = rootView.findViewById(R.id.templatesRecyclerView);
        memeItemList = new ArrayList<>();
        adapter = new GalleryItemRecyclerViewAdapter(memeItemList);
        recyclerView.setAdapter(adapter);

        // Ha engedélyek megvannak, akkor képek betöltése
        loadImages();

        return rootView;
    }

    private void loadImages() {
        // Android 9-es és újabb verziók esetén
        File memeDirectory = new File(Environment.getExternalStorageDirectory(), "Pictures/MemeMaker");

        Log.d("GalleryFragment", "Directory exists: " + memeDirectory.exists());

        // Listázza a fájlokat a mappában
        File[] files = memeDirectory.listFiles();
        if (files != null && files.length > 0) {
            Log.d("GalleryFragment", "Found " + files.length + " files.");
            memeItemList.clear();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jpg")) {
                    MemeItem memeItem = new MemeItem(file.getAbsolutePath(), file.getName());
                    memeItemList.add(memeItem);
                    Log.d("GalleryFragment", "Added meme: " + memeItem.getTitle());
                } else {
                    Log.d("GalleryFragment", "Skipping non-JPG file: " + file.getName());
                }
            }
            adapter.notifyDataSetChanged();
        } else {
            Log.d("GalleryFragment", "No files found.");
            showMessage("Nincs elérhető fájl.");
        }
    }

    // Segítő metódus a hibaüzenetek megjelenítéséhez
    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

