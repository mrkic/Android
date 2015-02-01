package com.example.mariyan.startform;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Mariyan on 29.1.2015 г..
 */
public class Constants {

    public static final String LOGIN_EXTRA = "loginNickname";
    public static final String NICKNAME = "nickname";
    public static final String START_TIMER = "timer";
    public static final int POINT_TAG = R.id.point_tag;
    public static final int ORDER_TAG = R.id.ordered_tag;


    public static AlertDialog.Builder alertMessage(String title, String message, String negativeButtonName, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setNegativeButton(negativeButtonName, null);

        return builder;
    }



    private Constants() {

    }
}
