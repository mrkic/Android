package com.example.mariyan.startform;

import android.widget.ImageView;

/**
 * Created by Mariyan on 30.1.2015 г..
 */
public class User {
    private String nickname;
    private double time;
    private ImageView imageView;


    public User (String nickname, double time, ImageView imageView) {
        this.nickname = nickname;
        this.time = time;
        this.imageView = imageView;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
