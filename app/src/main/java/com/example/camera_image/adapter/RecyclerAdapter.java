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
import com.example.camera_image.data.RealmModel;
import com.squareup.picasso.Picasso;

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

    public RecyclerAdapter(Context context) {
        //   this.uriList = uriList;
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
        String testUrl = "https://scontent.fiev7-2.fna.fbcdn.net/v/t1.0-9/44961129_676854412698391_7891029656436998144_n.jpg?_nc_cat=100&_nc_ht=scontent.fiev7-2.fna&oh=9b95ae7a2a08919e3ef356d78fe9e46a&oe=5C494D64";
        //  Uri uri = Uri.parse(uriList.get(position).getUriString());
        //    holder.cameraImage.setImageURI(uri);
        //"http://i.imgur.com/DvpvklR.png"
        Picasso.get().load(testUrl).into(holder.cameraImage);

        holder.clickItem();

        holder.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(context, "SHARE!!", Toast.LENGTH_SHORT).show();

                holder.callShare(/*uriList.get(position)*/null);
            }
        });

    }


    @Override
    public int getItemCount() {
        return 3 /*uriList.size()*/;
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

        public void callShare(final RealmModel realmModel) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//            sharingIntent.setType("image/jpeg");
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            sharingIntent.setType("image/*");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://media/external/images/media/37230") /*Uri.parse(realmModel.getUriString())*/);
            view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share Image Using"));

        }

        public void clickItem(/*final RealmModel realmModel*/) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareImage.setVisibility(View.VISIBLE);
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
