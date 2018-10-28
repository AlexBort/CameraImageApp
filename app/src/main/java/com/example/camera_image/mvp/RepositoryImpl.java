package com.example.camera_image.mvp;

import android.net.Uri;

import com.example.camera_image.data.RealmModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RepositoryImpl implements Contract.Repository {

    private Realm realm;

    @Override
    public void closeRealm() {
        if (realm != null) realm.close();
    }

    @Override
    public void transformToList(String uriString, final RepositoryListener listener) {
        List<Uri> uriList = new ArrayList<>();

        //uriList.add(uri);
        realm = getConfiguredRealm();
        final RealmModel realmModel = toRealmObject(uriString);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmModel);
                listener.getRealmListUri(getList());
          //      realm.commitTransaction();
            }
        });
    }


    private RealmModel toRealmObject(String uriString) {
        RealmModel realmModel = new RealmModel();
        realmModel.setUriString(uriString);
        return realmModel;
    }


    public List<RealmModel> getList() {
        Realm realm = Realm.getDefaultInstance();
     //   realm.beginTransaction();
        List<RealmModel> list = realm.where(RealmModel.class).findAll();
        if (list != null)
            return list;
     //   realm.commitTransaction();
        return null;
    }


    private Realm configRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        return Realm.getDefaultInstance();
    }

    private Realm getConfiguredRealm() {
        return Realm.getDefaultInstance();
    }

}
