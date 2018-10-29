package com.example.camera_image.mvp;

import android.content.SharedPreferences;
import android.util.Log;
import com.example.camera_image.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Contract.Repository {

    private static String TAG = "RepositoryImpl";
    private List<String> stringUriList = new ArrayList<>();

    /**
     * saving uris convert to the list of uriString
     *
     * @param preferences saving uri in SharedPreferences format
     * @return list of uri in string format (it helps us to present images by recyclerView)
     */
    @Override
    public List<String> getListFromPref(SharedPreferences preferences) {
        String wordsString = preferences.getString(Constants.KEY_PREF, " ");
        String[] itemsWords = wordsString.split(",");

        List<String> items = new ArrayList<>();
        for (int i = 0; i < itemsWords.length; i++) {
            items.add(itemsWords[i]);
        }
        return items;
    }


    /**
     * saving uri in preferences
     *
     * @param uriString   uri of image in String format
     * @param preferences - due to pref we will save uri of images in app
     */
    @Override
    public void transformToPref(String uriString, SharedPreferences preferences) {
        stringUriList.add(uriString);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringUriList) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        // for check
        String wordsString = preferences.getString(Constants.KEY_PREF, " ");
        Log.e(TAG, "oldResults: " + wordsString);

        SharedPreferences.Editor editor = preferences.edit();
        if (wordsString.length() > 3)
            editor.putString(Constants.KEY_PREF, wordsString + stringBuilder.toString());
        else
            editor.putString(Constants.KEY_PREF, stringBuilder.toString());
        String newWord = /*wordsString +*/ stringBuilder.toString();
        Log.e(TAG, "newResults: " + newWord);
        editor.apply();
    }

}
