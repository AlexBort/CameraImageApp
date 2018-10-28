package com.example.camera_image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainPresenterImpl implements MainContract.MainPresenter, MainContract.MainRepository.RepositoryListener {

    private static final String TAG = "MainPresenterImpl";
    private Context context;
    private MainContract.View view;
    private Activity activity;
    private MainContract.MainRepository repository;
    //   private static final int ACTION_CAMERA = 43;


    public MainPresenterImpl(Context context, MainContract.View view, Activity activity) {
        this.context = context;
        this.view = view;
        this.activity = activity;
        repository = new MainRepositoryImpl();
    }

    @Override
    public void callCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, Constants.ACTION_CAMERA);
    }

    @Override
    public void onCameraResult(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        Drawable drawable = new BitmapDrawable(bitmap);
        Log.e(TAG, "onCameraResult: " + String.valueOf(bitmap.getByteCount()));
        // imageView.setBackground(drawable);
        Uri uri = getImageUri(context, bitmap);
        Log.e(TAG, "onCameraResult: path" + uri.getPath());
        repository.transformToList(uri, this);
        //   repository.toRealmObject(uri);
        // FIXME: 26.10.2018 место, где будут сохраняться фотки!
    }


    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    @Override
    public void getRealmListUri(List<RealmModel> uriList) {
        Log.e(TAG, "getRealmListUri size: " + String.valueOf(uriList.size()));
        view.launchListImageActivity(uriList);
    }
}
