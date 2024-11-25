package com.example.meme_maker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class Main extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton btnCreate = rootView.findViewById(R.id.btnCreate);
        ImageButton btnGallery = rootView.findViewById(R.id.btnGallery);
        ImageButton btnTemplate = rootView.findViewById(R.id.btnTemplate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).loadFragmentAndAddToBackStack(new CreateMemeFragment(), "Create Meme");
                ((MainActivity)getActivity()).changeTitle("create");
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).loadFragmentAndAddToBackStack(new GalleryFragment(), "Gallery");
                ((MainActivity)getActivity()).changeTitle("gallery");
            }
        });

        btnTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).loadFragmentAndAddToBackStack(new TemplateFragment(), "Templates");
                ((MainActivity)getActivity()).changeTitle("template");
            }
        });

        return rootView;
    }
}