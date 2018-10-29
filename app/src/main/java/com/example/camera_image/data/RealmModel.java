package com.example.camera_image.data;

import android.net.Uri;

import io.realm.RealmObject;

public class RealmModel extends RealmObject {

    private String mUriString;

    public RealmModel() {
    }

    public String getmUriString() {
        return mUriString;
    }

    public void setmUriString(String mUriString) {
        this.mUriString = mUriString;
    }
}
