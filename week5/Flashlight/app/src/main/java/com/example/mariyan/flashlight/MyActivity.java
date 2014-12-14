package com.example.mariyan.flashlight;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;


public class MyActivity extends Activity {

    private Camera camera;
    private boolean onLight = false;
    private Camera.Parameters parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Context context = this;
        PackageManager pm = context.getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.e("err", "Device has no mCamera!");
            return;
        }

        camera = Camera.open();
        parameters = camera.getParameters();



    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!onLight) {
            Log.v("tag", "STARTING FLASH");
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
            onLight = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (onLight) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
            onLight = false;
        }
    }
}
