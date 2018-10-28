package com.example.camera_image.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.camera_image.data.RealmModel;

import java.util.List;

public interface Contract {


    interface Presenter {

        void onDestroy();

        void callCamera();

        void onCameraResult(Intent data);

        void initContext(Context context);

        void initActivityContext(Activity activity);

        void initView(View view);

        void initPerformList(List<RealmModel> uriList);

        void passListToView();

        List<RealmModel> getList();
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

        void transformToList(String uriString, RepositoryListener listener);

        List<RealmModel> getList();
        //    void toRealmObject(Uri uri);
    }
}
