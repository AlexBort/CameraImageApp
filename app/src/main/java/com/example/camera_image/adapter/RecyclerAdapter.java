package com.example.camera_image.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.camera_image.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> /*implements RealmChangeListener*/ {

    private static final String TAG = "RecyclerAdapter";
    private Context context;
    private List<String> uriStringList;


    public RecyclerAdapter(Context context, List<String> uriStringList) {
        this.uriStringList = uriStringList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ViewHolder holder, final int position) {
        final Uri uri = Uri.parse(uriStringList.get(position));

        if (uriStringList.get(position).length() > 5)
            Picasso.get().load(uri).into(holder.cameraImage);
        else
            holder.view.setVisibility(View.GONE);


        holder.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    holder.getAdapterPosition()
                //position
                holder.callShare(Uri.parse(uriStringList.get(position)));
            }
        });

    }


    @Override
    public int getItemCount() {
        return uriStringList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.camera_image)
        ImageView cameraImage;
        @BindView(R.id.share_image)
        ImageView shareImage;
        private View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        public void callShare(final Uri uri) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            sharingIntent.setType("image/*");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri /*Uri.parse(realmModel.getUriString())*/);
            view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share Image Using"));
        }
    }
}
