package com.example.meme_maker;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class MemeEditorFragment extends Fragment{
    private ImageView memeImageView;
    private EditText topTextEditText, bottomTextEditText;
    private Button applyTextButton;
    private SeekBar fontSizeSeekBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_your_meme, container, false);

        memeImageView = view.findViewById(R.id.memeImage);
        topTextEditText = view.findViewById(R.id.topTextEditText);
        bottomTextEditText = view.findViewById(R.id.bottomTextEditText);
        applyTextButton = view.findViewById(R.id.applyTextButton);
        fontSizeSeekBar = view.findViewById(R.id.fontSizeSeekBar);

        // A kapott kép ID betöltése
        if (getArguments() != null && getArguments().containsKey("TEMPLATE_IMAGE")) {
            int imageResId = getArguments().getInt("TEMPLATE_IMAGE");
            memeImageView.setImageResource(imageResId);
        }

        applyTextButton.setOnClickListener(v -> addTextToMeme());

        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float textSize = progress;
                topTextEditText.setTextSize(textSize);
                bottomTextEditText.setTextSize(textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        return view;
    }

    private void addTextToMeme() {
        // bitmap a képből
        BitmapDrawable drawable = (BitmapDrawable) memeImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);

        // canvas készítése
        Canvas canvas = new Canvas(bitmap);

        // szöveg szinezése
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(topTextEditText.getTextSize());
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setAntiAlias(true);

        // felső szöveg
        canvas.drawText(
                topTextEditText.getText().toString(),
                bitmap.getWidth() * 0.1f,
                bitmap.getHeight() * 0.1f,
                paint
        );

        // alsó szöveg
        canvas.drawText(
                bottomTextEditText.getText().toString(),
                bitmap.getWidth() * 0.1f,
                bitmap.getHeight() * 0.9f,
                paint
        );

        // Frissíti a kép nézetét az új bitmap-el
        memeImageView.setImageBitmap(bitmap);

        // Lementi a képet JPG formátumban
        saveBitmapAsJPG(bitmap);
    }

    private void saveBitmapAsJPG(Bitmap bitmap) {
        String filename = "meme_" + System.currentTimeMillis() + ".jpg";
        OutputStream outputStream;

        try {
            // Scoped Storage használata (Android 10+ kompatibilis)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MemeMaker");

                Uri uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                if (uri != null) {
                    outputStream = requireContext().getContentResolver().openOutputStream(uri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    showMessage("Kép sikeresen mentve: " + uri.getPath());
                }
            } else {
                // Régebbi Android verziókhoz
                File directory = new File(requireContext().getExternalFilesDir(null), "MemeMaker");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File file = new File(directory, filename);
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
                showMessage("Kép sikeresen mentve: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Hiba a kép mentésekor: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
