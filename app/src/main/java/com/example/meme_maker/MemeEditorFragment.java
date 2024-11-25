package com.example.meme_maker;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;


public class MemeEditorFragment extends Fragment{
    private ImageView memeImageView;
    private EditText topTextEditText, bottomTextEditText;
    private Button applyTextButton;
    private SeekBar fontSizeSeekBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_your_meme, container, false);

        //views
        memeImageView = view.findViewById(R.id.memeImage);
        topTextEditText = view.findViewById(R.id.topTextEditText);
        bottomTextEditText = view.findViewById(R.id.bottomTextEditText);
        applyTextButton = view.findViewById(R.id.applyTextButton);
        fontSizeSeekBar = view.findViewById(R.id.fontSizeSeekBar);

        //betölti a képet a drawable mappából nem tudom hogy hogyan legyen a választhatóság jelenleg
        memeImageView.setImageResource(R.drawable.memetemplate_1);

        //hallgatja a gombot
        applyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTextToMeme();
            }
        });

        //hallgatja a font változtatót
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //szöveg méret változtatása
                float textSize = progress;
                topTextEditText.setTextSize(textSize);
                bottomTextEditText.setTextSize(textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed
            }
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
        paint.setColor(Color.BLACK);
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

        //frissíti a kép nézetét az új bitmap-el
        memeImageView.setImageBitmap(bitmap);
    }
}
