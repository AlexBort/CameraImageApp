package com.example.camera_image;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private Context context;
    private List<RealmModel> uriList;


    public RecyclerAdapter(Context context, List<RealmModel> uriList) {
        this.uriList = uriList;
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
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        Uri uri = Uri.parse(uriList.get(position).getUriString());
        holder.cameraImage.setImageURI(uri);
//        holder.firstName.setText(uriList.get(position).getName().getFirst());
//        Picasso.get().load(uriList.get(position).getPicture().getMedium()).into(holder.cameraImage);
        holder.clickItem();

        holder.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 28.10.2018 вот здесь мы делаем Intent!!
            }
        });

    }


    @Override
    public int getItemCount() {
        return uriList.size();
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
            //    cameraImage = itemView.findViewById(R.id.camera_image);


        }

        public void callShare() {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("image/jpeg");
            
        }

        public void clickItem(/*final RealmModel realmModel*/) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // TODO: 28.10.2018 здесь будем делать visibility of
                }
            });
        }

//        private byte[] bitmapToByteArray(Bitmap bitmap) {
//            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
//            return bStream.toByteArray();
//        }


    }

}
