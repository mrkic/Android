package com.example.mariyan.startform;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Puzzle extends Activity implements View.OnDragListener, View.OnTouchListener {



    private List<Drawable> allPieces;
    private Map<Drawable, Integer> map;
    private GridLayout gridLayout;
    private List<Integer> imageIndexes;
    private long startTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_puzzle);

        fromTypedArrayToList();
        int puzzleSize = (int) Math.sqrt(allPieces.size());
        Collections.shuffle(allPieces);

        gridLayout = (GridLayout) findViewById(R.id.puzzle_grid);
        gridLayout.setRowCount(puzzleSize);
        gridLayout.setColumnCount(puzzleSize);

        puzzle();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.puzzle, menu);
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

    private void fromTypedArrayToList() {
        startTimer = System.currentTimeMillis();
        TypedArray typedArray = getResources().obtainTypedArray(R.array.images);
        Log.i("length", typedArray.length() + "");

        allPieces = new ArrayList<Drawable>(typedArray.length());
        map = new HashMap<Drawable, Integer>();
        for (int i = 0; i < typedArray.length(); i++) {
            Drawable drawable = typedArray.getDrawable(i);

            allPieces.add(drawable);

            map.put(drawable, i);
        }

        typedArray.recycle();
    }

    private GridLayout.LayoutParams gridParams() {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();

        Display display = getWindowManager().getDefaultDisplay();
        Point windowSize = new Point();
        display.getSize(windowSize);


        params.width = (int) (windowSize.x / Math.sqrt(allPieces.size()));
        params.height = (int) (windowSize.y / Math.sqrt(allPieces.size()));
        return params;

    }

    private void puzzle() {
        int puzzleSize = gridLayout.getRowCount();
        imageIndexes = new ArrayList<Integer>(allPieces.size());
        for (int i = 0; i < puzzleSize; i++) {

            for (int j = 0; j < puzzleSize; j++) {
                int index = i * puzzleSize + j;
                Drawable drawable = allPieces.get(index);
                final ImageView imageView = new ImageView(this);
                imageView.setAdjustViewBounds(true);
                imageView.setPadding(2, 2, 2, 2);
                int orderedIndex = map.get(drawable);
                imageView.setTag(Constants.ORDER_TAG, orderedIndex);
                imageIndexes.add(orderedIndex);
                Log.i("Tag", map.get(drawable) + "");
                imageView.setImageDrawable(drawable);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                imageView.setLayoutParams(gridParams());
                Point point = new Point(j, i);
                imageView.setTag(Constants.POINT_TAG, point);
                imageView.setOnDragListener(this);


                imageView.setOnTouchListener(this);


                gridLayout.addView(imageView);

            }

        }
    }


    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();

        switch (action) {
            case DragEvent.ACTION_DROP: {
                ImageView onDragImage = (ImageView) dragEvent.getLocalState();
                ImageView dragImage = (ImageView) view;


                int onDragIndex = (Integer) onDragImage.getTag(Constants.ORDER_TAG);
                Point onDragPoint = (Point) onDragImage.getTag(Constants.POINT_TAG);
                int dragIndex = (Integer) dragImage.getTag(Constants.ORDER_TAG);
                Point dragPoint = (Point) dragImage.getTag(Constants.POINT_TAG);

                int index1 = imageIndexes.indexOf(onDragIndex);
                int index2 = imageIndexes.indexOf(dragIndex);

                imageIndexes.set(index1, dragIndex);
                imageIndexes.set(index2, onDragIndex);

                onDragImage.setTag(Constants.POINT_TAG, dragPoint);

                dragImage.setTag(Constants.POINT_TAG, onDragPoint);

                Animator animatorOnDragX = ObjectAnimator.ofFloat(onDragImage, "x", dragPoint.x * onDragImage.getMeasuredWidth());
                Animator animatorOnDragY = ObjectAnimator.ofFloat(onDragImage, "y", dragPoint.y * onDragImage.getMeasuredHeight());
                Animator animatorDragX = ObjectAnimator.ofFloat(dragImage, "x", onDragPoint.x * dragImage.getMeasuredWidth());
                Animator animatorDragY = ObjectAnimator.ofFloat(dragImage, "y", onDragPoint.y * dragImage.getMeasuredHeight());


                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorDragX, animatorDragY, animatorOnDragX, animatorOnDragY);
                animatorSet.start();
                if (isSolved()) {
//                    Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show();

                    double timer = ((double) (System.currentTimeMillis() - startTimer))/1000;
                    Log.i("TIMER_LOG", (System.currentTimeMillis() - startTimer) + "");
                    MyDataBase dataBase = new MyDataBase(this);

                    SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
                    Intent intent = getIntent();
//                    ImageView logo = new ImageView(Puzzle.this);
//                    String uri = "@drawable/ic_launcher";
//                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
//                    Drawable res = getResources().getDrawable(imageResource);
//                    logo.setImageDrawable(res);
                    String nickname = intent.getStringExtra(Constants.LOGIN_EXTRA);
                    String insertQuery = "INSERT INTO " + MyDataBase.RankingConstants.TABLE_NAME
                            + " (" + MyDataBase.RankingConstants.COLUMN_NICKNAME + ", "
                            + MyDataBase.RankingConstants.COLUMN_TIME
//                            + MyDataBase.RankingConstants.COLUMN_AVATAR
                            + ") VALUES ('"
                            + nickname + "', '" + timer + "')";
                    sqLiteDatabase.execSQL(insertQuery);
                    sqLiteDatabase.close();
                    dataBase.close();
                    Log.i("LOG_QUERY", insertQuery);

                    AlertDialog.Builder builder = Constants.alertMessage("Puzzle is Solved", "Congrats! Score: "
                            + timer, null, view.getContext());
                    builder.setPositiveButton("ok", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent rankingIntent = new Intent(Puzzle.this, RankingList.class);
                            startActivity(rankingIntent);
                            finish();
                        }
                    });
                    builder.show();
//                    Toast.makeText(this, "Congrats! Score: "
//                            + time, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

        return true;
    }


    private boolean isSolved() {

        for (int i = 0; i < imageIndexes.size() - 1; i++) {
            if (imageIndexes.get(i) > imageIndexes.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(null, shadowBuilder, view, 0);
        return true;
    }
}
