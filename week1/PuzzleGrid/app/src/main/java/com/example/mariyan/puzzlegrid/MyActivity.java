package com.example.mariyan.puzzlegrid;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyActivity extends Activity {

    private GridView grid;
    private List<Drawable> imageList;
    private List<ImageView> orderedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        imageList = fromTypedArrayToList();
        orderedImages = new ArrayList<ImageView>();
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        int puzzleSize = (int) Math.sqrt(imageList.size());
        grid.setColumnCount(puzzleSize);
        grid.setRowCount(puzzleSize);
        final List<Drawable> randomImages = new ArrayList<Drawable>(imageList);
        Collections.shuffle(randomImages);

        for (int i = 0; i < puzzleSize*puzzleSize; i++) {
            final ImageView image = new ImageView(this);
            image.setAdjustViewBounds(true);
            image.setPadding(2, 2, 2, 2);
            image.setLayoutParams(gridParams(120, 80));
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageDrawable(randomImages.get(i));
            orderedImages.add(image);
            image.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(image);
                    image.startDrag(null, shadowBuilder, image, 0);
                    return true;
                }
            });

            image.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    if (dragEvent.getAction() == dragEvent.ACTION_DROP) {
                        ImageView dragImage = (ImageView) dragEvent.getLocalState();

                        Drawable temp = dragImage.getDrawable();
                        dragImage.setImageDrawable(image.getDrawable());
                        image.setImageDrawable(temp);
                    }

                    if (imageList.equals(toDrawableList())) {
                        toast("You win!");
                    }

                    return true;
                }
            });

            grid.addView(image);

        }


    }

    private List<Drawable> fromTypedArrayToList() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.images);
        List<Drawable> images = new ArrayList<Drawable>();

        for (int i = 0; i < typedArray.length(); i++) {
            images.add(typedArray.getDrawable(i));
        }

        return images;
    }

    private GridLayout.LayoutParams gridParams(int width, int height) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width=width;
        params.height=height;
        return params;

    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private List<Drawable> toDrawableList() {
        List<Drawable> result = new ArrayList<Drawable>();

        for (ImageView image : orderedImages) {
            result.add(image.getDrawable());
        }

        return result;
    }
}
