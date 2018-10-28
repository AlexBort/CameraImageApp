package com.example.camera_image.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.camera_image.R;
import com.example.camera_image.data.RealmModel;
import com.example.camera_image.adapter.RecyclerAdapter;
import com.example.camera_image.mvp.MainContract;
import com.example.camera_image.mvp.MainPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenterImpl presenter;
//    @BindView(R.id.camera_image)
//    ImageView imageView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        fillRecyclerView();
    }

    @Override
    public void presentListImages(List<RealmModel> uriList) {
        Uri uri = Uri.parse(uriList.get(0).getUriString());
        //     imageView.setImageURI(uri);
        //    else Toast.makeText(ImagesActivity.this, "", Toast.LENGTH_SHORT).show();

        recyclerView.setAdapter(new RecyclerAdapter(getBaseContext()));
    }


    private void fillRecyclerView() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ImagesActivity.this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, mLinearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(getBaseContext()));
    }

}
