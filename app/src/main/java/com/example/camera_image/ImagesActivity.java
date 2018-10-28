package com.example.camera_image;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenterImpl presenter;
    @BindView(R.id.camera_image)
    ImageView imageView;

    private void initPresenter() {
        presenter = MainPresenterImpl.getIsntancePresenter();
        presenter.initActivityContext(this);
        presenter.initContext(getBaseContext());
        presenter.initView(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);

        initPresenter();

    }

    @Override
    public void presentListImages(List<RealmModel> uriList) {
        Uri uri = Uri.parse(uriList.get(0).getUriString());
        imageView.setImageURI(uri);
        //    else Toast.makeText(ImagesActivity.this, "", Toast.LENGTH_SHORT).show();
    }
}
