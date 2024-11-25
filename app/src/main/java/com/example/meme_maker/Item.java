package com.example.meme_maker;

public class Item {



    private  String title;
    private int image;

    public Item(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public void setImage( int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
