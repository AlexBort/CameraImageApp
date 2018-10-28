package com.example.camera_image;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainRepositoryImpl implements MainContract.MainRepository {


    @Override
    public void transformToList(Uri uri, final RepositoryListener listener) {
        List<Uri> uriList = new ArrayList<>();

        //uriList.add(uri);
        final RealmModel realmModel = toRealmObject(uri);
        Realm realm = configRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmModel);
                listener.getRealmListUri(getList());
            }
        });
    }


    private RealmModel toRealmObject(Uri uri) {
        RealmModel realmModel = new RealmModel();
        realmModel.setUri(uri);
        return realmModel;
    }

    void workWithRealm() {

    }

    private List<RealmModel> getList() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmModel.class).findAll();
    }


    private Realm configRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        return Realm.getDefaultInstance();
    }

}
