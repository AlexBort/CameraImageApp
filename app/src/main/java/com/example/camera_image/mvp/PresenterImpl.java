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
import android.widget.Toast;

import com.example.camera_image.utils.Constants;
import com.example.camera_image.data.RealmModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

import io.realm.RealmResults;

public class PresenterImpl implements Contract.Presenter, Contract.Repository.RepositoryListener {

    private static final String TAG = "PresenterImpl";
    private Context mContext;
    private Contract.View mView;
    private Activity mActivity;
    private static Contract.Repository repository;
    private static List<RealmModel> realmList;
    private SharedPreferences preferences;

    private static PresenterImpl mainPresenter = new PresenterImpl();

    private PresenterImpl() {
    }

    // preferences = getSharedPreferences("PREF", 0);

    public static PresenterImpl getInstancePresenter() {
        repository = new RepositoryImpl();
        //     if (repository.getRealmResults() != null) realmList = repository.getRealmResults();
        return mainPresenter;
    }


//    public PresenterImpl(Context context, Contract.View view, Activity activity) {
//        this.context = context;
//        this.view = view;
//        this.activity = activity;
//        repository = new RepositoryImpl();
//    }

    @Override
    public void onDestroy() {
        repository.closeRealm();
    }

    @Override
    public void onCreate() {
        repository.createInstance();
    }

    @Override
    public void callCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.e(TAG, "callCamera: "+ " CHECK START  ACTIVITY FOR RESULT!" );
        mActivity.startActivityForResult(intent, Constants.ACTION_CAMERA);
    }

    @Override
    public void onCameraResult(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        Drawable drawable = new BitmapDrawable(bitmap);
        Log.e(TAG, "onCameraResult: " + "CHECK!! ");
        Log.e(TAG, "onCameraResult: " + String.valueOf(bitmap.getByteCount()));
        // imageView.setBackground(drawable);
        Uri uri = getImageUri(mContext, bitmap);
        Log.e(TAG, "onCameraResult: path " + uri);
        // uri.getPath() - path/external/images/media/37206
        // String.valueOf(uri)- path content://media/external/images/media/37208
        // uri- content://media/external/images/media/37210

        //   repository.transformToRealm(String.valueOf(uri), this);



        repository.transformToPref(String.valueOf(uri), getPrefs());

        //   repository.toRealmObject(uri);
        // FIXME: 26.10.2018 место, где будут сохраняться фотки!
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

    @Override
    public void initView(Contract.View view) {
        mView = view;
    }

    @Override
    public void initPerformList(List<RealmModel> uriList) {
        if (uriList != null && uriList.get(0) != null) mView.presentListImages(uriList);
        else
            Toast.makeText(mActivity, "You haven't added any photo yet", Toast.LENGTH_SHORT).show();
    }


    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        // return path;
        return Uri.parse(path);
    }

    public List<RealmModel> getList() {
        if (repository.getList() != null)
            return repository.getList();
        return null;
    }

    @Override
    public RealmResults<RealmModel> getRealmResults() {
        if (repository.getRealmResults() != null)
            return repository.getRealmResults();
        return null;
    }

    @Override
    public List<String> getListFromPref() {
        if (repository.getListFromPref(getPrefs()) != null)
            return repository.getListFromPref(getPrefs());

        return null;
    }

    @Override
    public void passListToView() {
        if (realmList != null)
            mView.presentListImages(realmList);
    }

    @Override
    public void getRealmListUri(List<RealmModel> uriList) {
        Log.e(TAG, "getRealmListUri: string uri" + uriList.get(0).getmUriString());
        Log.e(TAG, "getRealmListUri size: " + String.valueOf(uriList.size()));
        realmList = uriList;
        // FIXME: 28.10.2018 короче такая формулировака не зашла!!    this.initPerformList(uriList);
    }
}
