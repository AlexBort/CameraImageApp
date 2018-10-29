package com.example.camera_image.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.camera_image.R;
import com.example.camera_image.data.RealmModel;
import com.example.camera_image.adapter.RecyclerAdapter;
import com.example.camera_image.mvp.Contract;
import com.example.camera_image.mvp.PresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesActivity extends AppCompatActivity /*implements Contract.View*/ {

    private static final String TAG = "ImagesActivity";
    private PresenterImpl presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private void initPresenter() {
        presenter = PresenterImpl.getInstancePresenter();
        presenter.initActivityContext(ImagesActivity.this);
        presenter.initContext(getBaseContext());
        presenter.getPrefs();
        // FIXME: 28.10.2018 удалить initView
        // presenter.initView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //    presenter.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //    presenter.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);
        initPresenter();
        //      presenter.onCreate();
        fillRecyclerView();
    }


    private void fillRecyclerView() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ImagesActivity.this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, mLinearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //     if (presenter.getRealmResults() != null) {
        recyclerView.setAdapter(new RecyclerAdapter(getBaseContext(), presenter.getListFromPref()));
        Log.e(TAG, "fillRecyclerView: " + String.valueOf(presenter.getListFromPref().size()));
        Log.e(TAG, "fillRecyclerView: value" + presenter.getListFromPref().get(0));
        //       }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ImagesActivity.this,MainActivity.class));
     //   finish();

    }
}
