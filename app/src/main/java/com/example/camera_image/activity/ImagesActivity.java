package com.example.camera_image.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.camera_image.R;
import com.example.camera_image.adapter.RecyclerAdapter;
import com.example.camera_image.mvp.PresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * second activity, that presents list of images (that we made by camera)
 */
public class ImagesActivity extends AppCompatActivity{

    private static final String TAG = "ImagesActivity";
    private PresenterImpl presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private void initPresenter() {
        presenter = PresenterImpl.getInstancePresenter();
        presenter.initActivityContext(ImagesActivity.this);
        presenter.initContext(getBaseContext());
        presenter.getPrefs();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);
        initPresenter();
        presenter.getListFromPref();
        //      presenter.onCreate();
        fillRecyclerView();
    }


    /**
     * configure recyclerView and setting data to this
     */
    private void fillRecyclerView() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ImagesActivity.this);
        //     DividerItemDecoration itemDecoration = new DividerItemDecoration(this, mLinearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(), 0));
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(getBaseContext(), presenter.getListFromPref()));
        Log.e(TAG, "fillRecyclerView: " + String.valueOf(presenter.getListFromPref().size()));
        Log.e(TAG, "fillRecyclerView: value" + presenter.getListFromPref().get(0));
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ImagesActivity.this, MainActivity.class));
    }


}
