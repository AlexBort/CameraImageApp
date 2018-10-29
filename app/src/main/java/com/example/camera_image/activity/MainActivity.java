package com.example.camera_image.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.camera_image.mvp.PresenterImpl;
import com.example.camera_image.utils.Constants;
import com.example.camera_image.utils.PermissionUtils;
import com.example.camera_image.R;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPresenter();
        //    Toast.makeText(MainActivity.this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    private void initPresenter() {
        presenter = PresenterImpl.getInstancePresenter();
        presenter.initActivityContext(MainActivity.this);
        presenter.initContext(getBaseContext());
        presenter.getPrefs();
    }

    /**
     * calling ImagesActivity by clicking on callActivity button
     */
    @OnClick(R.id.call_activity)
    public void startImageActivity() {
        startActivity(new Intent(MainActivity.this, ImagesActivity.class));
    }

    /**
     * calling camera app
     */
    @OnClick(R.id.button_camera)
    public void openCamera() {
        if (PermissionUtils.checkPermission(this, Constants.PERMISSIONS))
            presenter.callCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: requestCode" + requestCode);
        Log.e(TAG, "onActivityResult: resultCode" + resultCode);
        if (resultCode != Activity.RESULT_OK) return;

        switch (requestCode) {
            case Constants.ACTION_CAMERA:
                presenter.onCameraResult(data);
                break;
        }
    }
}
