package com.example.mariyan.puzzleanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MyActivity extends Activity {

    private GridLayout grid;
    private List<Drawable> imageList;
    private List<ImageView> orderedImages;
    private Map<Integer, Drawable> orderedImagesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        imageList = fromTypedArrayToList();
        orderedImages = new ArrayList<ImageView>();
        grid = (GridLayout) findViewById(R.id.grid);
        int puzzleSize = (int) Math.sqrt(imageList.size());
        grid.setColumnCount(puzzleSize);
        grid.setRowCount(puzzleSize);
        final List<Drawable> randomImages = new ArrayList<Drawable>(imageList);
        orderedImagesMap = orderedImagesMap();
        Collections.shuffle(randomImages);


        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                final ImageView image = new ImageView(this);
                image.setAdjustViewBounds(true);
                image.setPadding(5, 5, 5, 5);
                image.setLayoutParams(gridParams(120, 80));
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image.setImageDrawable(randomImages.get(i * puzzleSize + j));
                orderedImages.add(image);
//                String tag = i + " " + j;
                Point point = new Point(j, i);
                image.setTag(point);
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
//                            String tagImage = (String) image.getTag();
//                            Scanner coordinatesImage = (Scanner) image.getTag();
                            Point pointImage = (Point) image.getTag();
                            int coordinateXImage = pointImage.x;
                            int coordinateYImage = pointImage.y;
//                            int coordinateXImage = coordinatesImage.nextInt();
//                            int coordinateYImage = coordinatesImage.nextInt();

                            Point pointDrag = (Point) dragImage.getTag();
                            int coordinateXDrag = pointDrag.x;
                            int coordinateYDrag = pointDrag.y;

//                            String tagDrag = (String) dragImage.getTag();
//                            Scanner coordinatesDrag = (Scanner) dragImage.getTag();
//                            int coordinateXDrag = coordinatesDrag.nextInt();
//                            int coordinateYDrag = coordinatesDrag.nextInt();
//                            System.out.println(coordinateXImage + " " + coordinateYImage + " " + coordinateXDrag + " " + coordinateYDrag);
//                            toast(coordinateXImage + " " + coordinateYImage + " " + coordinateXDrag + " " + coordinateYDrag);
//                        Drawable temp = dragImage.getDrawable();
//                        dragImage.setImageDrawable(image.getDrawable());
//                        image.setImageDrawable(temp);
//                            int[] animatorDragImageXY = new int[2];
//                            dragImage.getLocationOnScreen(animatorDragImageXY);
//                            int[] animatorImageXY = new int[2];
//                            image.getLocationOnScreen(animatorImageXY);
//                            float animatorImageY = animatorImageXY[1] / 32;
//                            float animatorImageX = animatorImageXY[0] / 32;
//                            float animatorDragImageY = animatorDragImageXY[1] / 32;
//                            float animatorDragImageX = animatorDragImageXY[0] / 32;
//

//                        int[] w = new int[2];
//                        image.getLocationInWindow(w);
//                        String coordinates = new String();
//                        coordinates += animatorDragImageY+ " "  + animatorDragImageX + " " + w[0]/32 + " " + w[1]/32 + " " + animatorImageY + " " + animatorImageX;

//                        toast(coordinates);

//                        Animator animator = ObjectAnimator.ofFloat(image, "scaleX", animatorDragImageX);
//                        Animator animatorY = ObjectAnimator.ofFloat(image, "scaleY", animatorDragImageY);
//                        animator.setInterpolator(new LinearInterpolator());
//                        Animator dragAnimator = ObjectAnimator.ofFloat(dragImage, "scaleX", animatorImageX);
//                        Animator dragAnimatorY = ObjectAnimator.ofFloat(dragImage, "scaleY", animatorImageY);

//                            AnimationSet animationSet = new AnimationSet(true);
//                            animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
//
//                            Animation animation = new TranslateAnimation(animatorDragImageX, animatorImageX, animatorDragImageY, animatorImageY);
//                            animation.setDuration(1000);
//                            animation.setFillAfter(true);
//                            animation.setFillEnabled(true);
//                        animationSet.addAnimation(animation);

//
//                            Animation animation2 = new TranslateAnimation(animatorImageX, animatorDragImageX, animatorImageY, animatorDragImageY);
//                            animation2.setDuration(1000);
//                            animation2.setFillAfter(true);
//                            animation2.setFillEnabled(true);
//                        animationSet.addAnimation(animation2);
//
//                        animationSet.setFillAfter(true);
//                        animationSet.setFillEnabled(true);
//                            Drawable temp = dragImage.getDrawable();
//                            dragImage.setImageDrawable(image.getDrawable());
//                            image.setImageDrawable(temp);

                            image.setTag(pointDrag);
                            dragImage.setTag(pointImage);
                            Animator animatorImageX = ObjectAnimator.ofFloat(dragImage, "x", coordinateXImage*image.getMeasuredWidth());
                            Animator animatorImageY = ObjectAnimator.ofFloat(dragImage, "y", coordinateYImage*image.getMeasuredHeight());
                            Animator animatorDragX = ObjectAnimator.ofFloat(image, "x", coordinateXDrag * image.getMeasuredWidth());
                            Animator animatorDragY = ObjectAnimator.ofFloat(image, "y", coordinateYDrag*image.getMeasuredHeight());

                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.playTogether(animatorImageX, animatorImageY, animatorDragX, animatorDragY);
                            animatorSet.start();
//                            image.startAnimation(animation);
//                            dragImage.startAnimation(animation2);

//                        image.startAnimation(animation2);
//                        dragImage.startAnimation(animation);


//                        dragAnimator.setInterpolator(new LinearInterpolator());
//                        dragAnimator.start();
//                        animator.start();

//                        AnimatorSet animatorSet = new AnimatorSet();
////                        animatorSet.addListener(new Animator.AnimatorListener() {
////                            @Override
////                            public void onAnimationStart(Animator animator) {
////
////                            }
////
////                            @Override
////                            public void onAnimationEnd(Animator animator) {
////                                Drawable temp = dragImage.getDrawable();
////                                dragImage.setImageDrawable(image.getDrawable());
////                                image.setImageDrawable(temp);
////                            }
////
////                            @Override
////                            public void onAnimationCancel(Animator animator) {
////
////                            }
////
////                            @Override
////                            public void onAnimationRepeat(Animator animator) {
////
////                            }
////                        });
////                        animatorSet.playTogether(animator, animatorY);
//                        animatorSet.play(animator).with(animatorY).before(dragAnimator).with(dragAnimatorY);
////                        animatorSet.playTogether(dragAnimator, dragAnimatorY);
//                        animatorSet.start();

//                        Animation animation = new TranslateAnimation(animatorDragImageX, animatorDragImageY, animatorImageX, animatorImageY);
//                        Animation dragAnimation = new TranslateAnimation(animatorImageX,animatorImageY, animatorDragImageX, animatorDragImageY );
//                        animation.setDuration(1000);
//                        animation.setFillAfter(true);

//                        dragImage.startAnimation(animation);
//                        image.startAnimation(dragAnimation);

                        }
//                        imageList.equals(toDrawableList())
                        if (isSolved()) {
                            toast("Solved!");
                        }

                        return true;
                    }
                });

                grid.addView(image);

            }
        }


    }

    private boolean isSolved() {
        List<Drawable> list = new ArrayList<Drawable>(orderedImagesMap.size());
//        Map<Integer, Drawable> map = new TreeMap<Integer, Drawable>();
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView iv = (ImageView) gridLayout.getChildAt(i);

            list.add(iv.getDrawable());


//            ImageView imageView = (ImageView) grid.getChildAt(i);
//            map.put(i, imageView.getDrawable());
//            toast(orderedImagesMap.size()+"");
//            if (!imageView.getDrawable().equals(orderedImagesMap.get(i))) {
////                toast("Explode!");
//                return false;
//
//            } else {
//                toast("Hold up!");
//            }
        }
        List<Drawable> ordered = fromTypedArrayToList();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getConstantState().equals(ordered.get(i).getConstantState())) {
                return false;
            }
        }
        return true;
    }
    private Map<Integer, Drawable> orderedImagesMap() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.images);
        Map<Integer, Drawable> map = new TreeMap<Integer, Drawable>();
        for (int i = 0; i < typedArray.length(); i++) {
            map.put(i, typedArray.getDrawable(i));
        }
        return map;
    }

    private List<Drawable> fromTypedArrayToList() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.images);
        List<Drawable> images = new ArrayList<Drawable>();
//        orderedImagesMap = new HashMap<Integer, Drawable>();

        for (int i = 0; i < typedArray.length(); i++) {
            images.add(typedArray.getDrawable(i));
//            orderedImagesMap.put(i, typedArray.getDrawable(i));
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
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private List<Drawable> toDrawableList() {
        List<Drawable> result = new ArrayList<Drawable>();

        for (ImageView image : orderedImages) {
            result.add(image.getDrawable());
        }

        return result;
    }
}
