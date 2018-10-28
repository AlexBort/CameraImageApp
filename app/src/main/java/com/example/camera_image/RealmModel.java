package com.example.camera_image;

import android.net.Uri;

import io.realm.RealmObject;

public class RealmModel extends RealmObject {

    private Uri uri;

    public RealmModel() {
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
