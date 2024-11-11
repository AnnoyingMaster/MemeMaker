package com.example.meme_maker;

public class Item {



    private  String title;
    private String image;

    public Item(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
