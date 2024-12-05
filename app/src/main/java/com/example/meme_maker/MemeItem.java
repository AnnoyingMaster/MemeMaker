package com.example.meme_maker;

public class MemeItem {
    private String imagePath;
    private String title;

    public MemeItem(String imagePath, String title) {
        this.imagePath = imagePath;
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }
}
