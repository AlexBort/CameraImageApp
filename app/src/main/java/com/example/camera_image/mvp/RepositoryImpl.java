package com.example.camera_image.mvp;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.example.camera_image.data.RealmModel;
import com.example.camera_image.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RepositoryImpl implements Contract.Repository {

    private Realm realm;
    private static String TAG = "RepositoryImpl";
    private List<String> stringUriList = new ArrayList<>();

    @Override
    public void closeRealm() {
        if (realm != null) realm.close();
    }

    @Override
    public void createInstance() {
        realm = getConfiguredRealm();
    }

    @Override
    public List<String> getListFromPref(SharedPreferences preferences) {
        String wordsString = preferences.getString(Constants.KEY_PREF, " ");
        String[] itemsWords = wordsString.split(",");

        List<String> items = new ArrayList<>();
        for (int i = 0; i < itemsWords.length; i++) {
            items.add(itemsWords[i]);
        }

//        for (int i = 0; i < items.size(); i++) {
//            resultText.setText(items.get(i) + "\n");
//        }
        return items;
    }


    @Override
    public void transformToPref(String uriString, SharedPreferences preferences) {
        stringUriList.add(uriString);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringUriList) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        //   preferences = getSharedPreferences("PREF", 0);

        // for check
        String wordsString = preferences.getString(Constants.KEY_PREF, " ");
        Log.e(TAG, "oldeResults: " + wordsString);

        SharedPreferences.Editor editor = preferences.edit();
        if (wordsString.length() > 3)
            editor.putString(Constants.KEY_PREF, wordsString + stringBuilder.toString());
        else
            editor.putString(Constants.KEY_PREF, /*wordsString + */stringBuilder.toString());
        String newWord = /*wordsString +*/ stringBuilder.toString();
        Log.e(TAG, "newResults: " + newWord);
        editor.apply();
    }


    private RealmModel toRealmObject(String uriString) {
        RealmModel realmModel = new RealmModel();
        realmModel.setmUriString(uriString);
        return realmModel;
    }

    public List<RealmModel> getList() {
        realm = getConfiguredRealm(); // Realm.getDefaultInstance();
        //   realm.beginTransaction();
        List<RealmModel> list = realm.where(RealmModel.class).findAll();
        if (list != null)
            return list;
        //   realm.commitTransaction();
        return null;
    }

    @Override
    public RealmResults<RealmModel> getRealmResults() {
        realm = getConfiguredRealm();/*Realm.getDefaultInstance();*/
        RealmResults<RealmModel> list = realm.where(RealmModel.class).findAll();
        Log.e(TAG, "getRealmResults: size" + String.valueOf(list.size()));
        if (list != null)
            return list;
//        RealmResults<RealmModel> list = realm.allObjects(RealmModel.class)) - allObjects is deprecated
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


    @Override
    public void transformToRealm(String uriString, final RepositoryListener listener) {
        List<Uri> uriList = new ArrayList<>();

        //stringUriList.add(uri);
        realm = getConfiguredRealm();

        realm.beginTransaction();
        RealmModel realmModel = realm.createObject(RealmModel.class);
        realmModel.setmUriString(uriString);
        realm.commitTransaction();
        //  realmModel = toRealmObject(uriString);


//        final RealmModel realmModel = toRealmObject(uriString);
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealm(realmModel);
//                listener.getRealmListUri(getList());
//
//            }
//        });


    }


}
