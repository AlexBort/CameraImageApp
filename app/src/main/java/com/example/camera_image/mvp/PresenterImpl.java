package com.example.camera_image.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import com.example.camera_image.utils.Constants;
import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * presenter- layer between data and activity
 */
public class PresenterImpl implements Contract.Presenter {

    private static final String TAG = "PresenterImpl";
    private Context mContext;
//    private Contract.View mView;
    private Activity mActivity;
    private static Contract.Repository repository;
    private SharedPreferences preferences;
    private static PresenterImpl mainPresenter = new PresenterImpl();

    private PresenterImpl() {
    }

    /**
     * making singltone
     *
     * @return instance of presenter
     */
    public static PresenterImpl getInstancePresenter() {
        repository = new RepositoryImpl();
        return mainPresenter;
    }


    /**
     * call camera app using intent
     */
    @Override
    public void callCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.e(TAG, "callCamera: " + " CHECK START  ACTIVITY FOR RESULT!");
        mActivity.startActivityForResult(intent, Constants.ACTION_CAMERA);
    }

    /**
     * getting result after making a photo
     *
     * @param data - result from camera, that we convert into uri
     */
    @Override
    public void onCameraResult(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        Drawable drawable = new BitmapDrawable(bitmap);
        Log.e(TAG, "onCameraResult: " + "CHECK!! ");

        Uri uri = getImageUri(mContext, bitmap);
        Log.e(TAG, "onCameraResult: path " + uri);

        repository.transformToPref(String.valueOf(uri), getPrefs());
    }

    @Override
    public void initContext(Context context) {
        mContext = context;
    }

    @Override
    public SharedPreferences getPrefs() {
        if (mContext != null)
            preferences = mContext.getSharedPreferences("PREF", 0);
        return preferences;
    }

    @Override
    public void initActivityContext(Activity activity) {
        mActivity = activity;
    }

    /**
     * converting image from bitmap to uri
     *
     * @param context
     * @param inImage - get image in the bitmap format
     * @return uri of image
     */
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        // return path;
        return Uri.parse(path);
    }


    @Override
    public List<String> getListFromPref() {
        if (repository.getListFromPref(getPrefs()) != null) {
            return repository.getListFromPref(getPrefs());
        }
        return null;
    }


}
