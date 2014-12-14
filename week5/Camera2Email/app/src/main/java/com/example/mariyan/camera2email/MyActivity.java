package com.example.mariyan.camera2email;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyActivity extends Activity {
    public static final int IMAGE_CODE = 777;
    private String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        dispatchTakePictureIntent();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_CODE && resultCode == RESULT_OK) {

            File image = new File(imagePath);




            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/image");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Image");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(image));
            intent.putExtra(Intent.EXTRA_TEXT, "cmon");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"mrkic9308@gmail.com"});
            startActivity(Intent.createChooser(intent, "Send email..."));

            finish();

        }
    }




    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
    );

    imagePath = image.getAbsolutePath();
    return image;
}

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {






                File file = new File("/sdcard/ïmage"  + String.valueOf(System.currentTimeMillis()) + ".png");
                imagePath = file.getAbsolutePath();
                Uri uriSavedImage=Uri.fromFile(file);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                startActivityForResult(takePictureIntent, IMAGE_CODE);

//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
////                Log.i("TAG", ex.getStackTrace());
//            }
//
//            if (photoFile != null) {
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent, IMAGE_CODE);
//            }
        }
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
        // as you specify a paren
        // t activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
