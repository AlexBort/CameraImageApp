package com.example.camera_image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.List;

public interface MainContract {


    interface MainPresenter {

        void callCamera();

        void onCameraResult(Intent data);

        void initContext(Context context);

        void initActivityContext(Activity activity);

        void initView(View view);

        void initPerformList(List<RealmModel> uriList);
    }


    interface View {
        void presentListImages(List<RealmModel> uriList);
        //   void launchListImageActivity(List<RealmModel> uriList);
    }

    interface MainRepository {

        interface RepositoryListener {
            void getRealmListUri(List<RealmModel> uriList);
        }

        void transformToList(String uriString, RepositoryListener listener);

        //    void toRealmObject(Uri uri);
    }
}
