package com.example.camera_image;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;

import io.realm.Realm;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        startService(new Intent(this, NotificationService.class));
    }
}
