package com.example.camera_image.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.camera_image.data.RealmModel;

import java.util.List;

import io.realm.RealmResults;

public interface Contract {


    interface Presenter {

        void onDestroy();

        void onCreate();

        void callCamera();

        void onCameraResult(Intent data);

        void initContext(Context context);

        SharedPreferences getPrefs();

        void initActivityContext(Activity activity);

        void initView(View view);

        void initPerformList(List<RealmModel> uriList);

        void passListToView();

        List<RealmModel> getList();

        RealmResults<RealmModel> getRealmResults();

        List<String> getListFromPref();
    }


    interface View {
        void presentListImages(List<RealmModel> uriList);

        void onDestroy();
        //   void launchListImageActivity(List<RealmModel> uriList);
    }

    interface Repository {

        interface RepositoryListener {
            void getRealmListUri(List<RealmModel> uriList);
        }

        void closeRealm();

        void createInstance();

        List<String> getListFromPref(SharedPreferences preferences);

        void transformToRealm(String uriString, RepositoryListener listener);

        void transformToPref(String uriString, SharedPreferences preferences);

        List<RealmModel> getList();

        RealmResults<RealmModel> getRealmResults();
        //    void toRealmObject(Uri uri);
    }
}
