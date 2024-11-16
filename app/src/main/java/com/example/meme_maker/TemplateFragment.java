package com.example.meme_maker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TemplateFragment extends Fragment {


    public TemplateFragment() {
        // Required empty public constructor
    }


    public static TemplateFragment newInstance(String param1, String param2) {
        TemplateFragment fragment = new TemplateFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_template, container, false);
        return view;
    }
}