package com.example.camera_image;

import android.content.Intent;
import android.net.Uri;

import java.util.List;

public interface MainContract {


    interface MainPresenter {

        void callCamera();

        void onCameraResult(Intent data);
    }


    interface View {
        void launchListImageActivity(List<RealmModel> uriList);
    }

    interface MainRepository {

        interface RepositoryListener {
            void getRealmListUri(List<RealmModel> uriList);
        }

        void transformToList(Uri uri, RepositoryListener listener);

        //    void toRealmObject(Uri uri);
    }
}
