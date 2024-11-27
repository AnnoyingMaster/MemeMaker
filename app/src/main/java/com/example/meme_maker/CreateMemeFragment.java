package com.example.meme_maker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class CreateMemeFragment extends Fragment {


    public CreateMemeFragment() {
        // Required empty public constructor
    }

    ImageButton buttonBrowse;
    ImageView testImgView;

    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonBrowse = view.findViewById(R.id.btnBrowse);
        testImgView = view.findViewById(R.id.testImgView);

        registerResult();

        buttonBrowse.setOnClickListener(v -> pickImage());
    }


    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }


    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result){
                        try{
                            Uri imageUri = result.getData().getData();
                            testImgView.setImageURI(imageUri);
                        } catch (Exception e){
                            Toast.makeText(getContext(), "Nincs kép kiválasztva", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

        );
    }

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_meme, container, false);

        return view;
    }





}