package com.example.camera_image.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.camera_image.mvp.Contract;
import com.example.camera_image.mvp.PresenterImpl;
import com.example.camera_image.utils.Constants;
import com.example.camera_image.utils.PermissionUtils;
import com.example.camera_image.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity /*implements Contract.View*/{

    private PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPresenter();
  //      presenter.getList(); // FIXME: 28.10.2018 нужно ли здесь вызывать!?
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initPresenter() {
        presenter = PresenterImpl.getInstancePresenter();
        presenter.initActivityContext(this);
        presenter.initContext(getBaseContext());
    }


    @OnClick(R.id.call_activity)
    public void startImageActivity() {
        startActivity(new Intent(MainActivity.this, ImagesActivity.class));
    }

    @OnClick(R.id.button_camera)
    public void openCamera() {
        if (PermissionUtils.checkPermission(this, Constants.PERMISSIONS))
            presenter.callCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;

        switch (requestCode) {
            case Constants.ACTION_CAMERA:
                presenter.onCameraResult(data);
                break;
        }
    }
}
