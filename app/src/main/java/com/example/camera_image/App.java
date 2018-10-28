package com.example.camera_image;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        configRealm();
        startService(new Intent(this, NotificationService.class));
    }

    private void configRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        //      return Realm.getDefaultInstance();
    }


}
