package com.example.camera_image.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * class with ordinary methods for checking permissions
 *
 * @author: AlexBort (alex.bortnik@gmail.com)
 */

public class PermissionUtils {

    public static final int PERMISSION_REQUEST_CODE_ACTIVITY = 1101;
    public static final String PERMISSIONS_LOCATION[] = {Manifest.permission.ACCESS_FINE_LOCATION};


    public static boolean isPermissionGranted(Context context, String[] permissions) {
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static boolean checkPermission(Fragment fragment, String[] permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (isPermissionGranted(fragment.getContext(), permission)) {
                // Permission Granted Already
                return true;
            }
            // Request Permission
            requestPermission(fragment, permission);
        } else {
            return true;
        }
        return false;
    }

    public static boolean checkPermission(Activity activity, String permission) {
        return checkPermission(activity, new String[]{permission});
    }

    public static boolean checkPermission(Activity activity, String[] permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (isPermissionGranted(activity.getApplicationContext(), permission)) {
                // Permission Granted Already
                return true;
            }
            // Request Permission
            requestPermission(activity, permission);
        } else {
            return true;
        }
        return false;
    }

    public static void requestPermission(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, PERMISSION_REQUEST_CODE_ACTIVITY);
        }
    }

    public static void requestPermission(Activity activity, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, requestCode);
        }
    }

    public static void requestPermission(Fragment fragment, String[] permissions) {
        fragment.requestPermissions(permissions, PERMISSION_REQUEST_CODE_ACTIVITY);
    }


    public static boolean checkShouldShowRequestPermission(Fragment fragment, String[] permissions) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        for (String permission : permissions)
            if (!fragment.shouldShowRequestPermissionRationale(permission)) {
                return false;
            }
//        }
        return true;
    }

    public static boolean checkShouldShowRequestPermission(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions)
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    return false;
                }
        }
        return true;
    }

}
