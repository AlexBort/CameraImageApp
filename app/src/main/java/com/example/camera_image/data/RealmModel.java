package com.example.camera_image.data;

import android.net.Uri;

import io.realm.RealmObject;

public class RealmModel extends RealmObject {

    private String uriString;

    public RealmModel() {
    }

    public String getUriString() {
        return uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }
}
