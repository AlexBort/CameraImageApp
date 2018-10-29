package com.example.camera_image.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.List;



/**
 * interfaces for the presenter and repository classes
 */
public interface Contract {


    interface Presenter {

        void callCamera();

        void onCameraResult(Intent data);

        void initContext(Context context);

        SharedPreferences getPrefs();

        void initActivityContext(Activity activity);

        List<String> getListFromPref();
    }


    interface Repository {
        List<String> getListFromPref(SharedPreferences preferences);
        void transformToPref(String uriString, SharedPreferences preferences);
    }
}
