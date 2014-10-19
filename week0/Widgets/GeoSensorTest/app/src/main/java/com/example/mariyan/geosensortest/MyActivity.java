package com.example.mariyan.geosensortest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import android.view.ViewGroup.LayoutParams;

import java.io.File;
import java.net.URI;


public class MyActivity extends Activity {
    private VideoView video;
    private Button prev;
    private Button next;
    private Button play;

    public static final int THREE_SEC = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        video = (VideoView) findViewById(R.id.video);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "Ronaldo_Dive_Moti.mp4";
        File file = new File(path, fileName);
        video.setVideoURI(Uri.parse(file.toString()));
        video.setMediaController(new MediaController(this));


        play = (Button) findViewById(R.id.play);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        play.setLayoutParams(layoutParams);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!video.isPlaying()) {
                    video.start();
                    play.setBackgroundResource(R.drawable.pause);
                } else {
                    if (video.canPause()) {
                        video.pause();
                        play.setBackgroundResource(R.drawable.play);
                    }
                }
            }
        });

        prev = (Button) findViewById(R.id.prev);
        prev.setLayoutParams(layoutParams);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (video.canSeekBackward()) {
                    video.seekTo(video.getCurrentPosition() - THREE_SEC);
                }
            }
        });


        next = (Button) findViewById(R.id.next);
        next.setLayoutParams(layoutParams);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (video.canSeekForward()) {
                    video.seekTo(video.getCurrentPosition() + THREE_SEC);
                }
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
