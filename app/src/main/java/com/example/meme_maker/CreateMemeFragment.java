package com.example.meme_maker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import androidx.core.content.ContextCompat;
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

    ImageButton buttonBrowse, buttonCamera;
    ImageView testImgView;

    private final int CAM_REQ = 1000;
    private final int IMG_REQ = 2000;
    Uri imageUri;


    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // A kamera által készített képet Bitmap formájában szerezhetjük be
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    // Átadjuk a képet a Fragment-nek
                    MemeEditorFragment fragment = new MemeEditorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("imageBitmap", imageBitmap); // Kép átadása
                    fragment.setArguments(bundle);

                    // Fragment tranzakció indítása
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainerView, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
    );

    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();

                    if (imageUri != null) {
                        // Új Fragment példányosítása és a kép URI átadása
                        MemeEditorFragment fragment = new MemeEditorFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("imageUri", imageUri.toString()); // URI átadása
                        fragment.setArguments(bundle);

                        // Fragment tranzakció végrehajtása
                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainerView, fragment) // fragmentContainer a hely, ahova a fragment kerül
                                .addToBackStack(null) // Visszalépési lehetőség
                                .commit();
                    }
                }
            }
    );

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonBrowse = view.findViewById(R.id.btnBrowse);
        buttonCamera = view.findViewById(R.id.btnCamera);

        buttonCamera.setOnClickListener(v -> {
            Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(camIntent);
        });

        buttonBrowse.setOnClickListener(v -> {
            Intent photoIntent = new Intent(Intent.ACTION_PICK);
            photoIntent.setType("image/*");
            galleryLauncher.launch(photoIntent);
        });




    }


    private static final int RESULT_OK = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == CAM_REQ) {
                Bitmap camBitmap = (Bitmap) data.getExtras().get("data");
                if (camBitmap != null) {
                    testImgView.setImageBitmap(camBitmap);
                }
            } else if (requestCode == IMG_REQ) {
                imageUri = data.getData();
                if (imageUri != null) {
                    testImgView.setImageURI(imageUri);
                }
            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_meme, container, false);

        return view;
    }





}