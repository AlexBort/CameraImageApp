package com.example.camera_image.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.camera_image.data.RealmModel;

import java.util.List;

public interface Contract {


    interface Presenter {

        void callCamera();

        void onCameraResult(Intent data);

        void initContext(Context context);

        void initActivityContext(Activity activity);

        void initView(View view);

        void initPerformList(List<RealmModel> uriList);

        void passListToView();
    }


    interface View {
        void presentListImages(List<RealmModel> uriList);
        //   void launchListImageActivity(List<RealmModel> uriList);
    }

    interface Repository {

        interface RepositoryListener {
            void getRealmListUri(List<RealmModel> uriList);
        }

        void transformToList(String uriString, RepositoryListener listener);

        //    void toRealmObject(Uri uri);
    }
}
