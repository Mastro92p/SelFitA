package com.example.mastro.selfit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by Mastro on 3/10/2017.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeCustomViewHolder> {

    private List<HomeItem> homefeedItemList;
    private Context mContext;

    public HomeRecyclerViewAdapter(Context context, List<HomeItem> feedItemList) {
        this.homefeedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public HomeRecyclerViewAdapter.HomeCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_home, null);
        return new HomeRecyclerViewAdapter.HomeCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeCustomViewHolder customViewHolder, int i) {
        HomeItem feedItem = homefeedItemList.get(i);

        //Render image using Picasso library
        // if (!TextUtils.isEmpty(feedItem.getThumbnail())) {


        final ImageView imageView = customViewHolder.getImageView();

        Glide.with(mContext).load(R.drawable.profile).asBitmap().centerCrop()
                .error(R.drawable.profile)
                .placeholder(R.drawable.profile)
                .into(new BitmapImageViewTarget(customViewHolder.imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

        //ImageView mainHold = customViewHolder.getMainHold();

        Glide.with(mContext).load(R.drawable.placeholder).centerCrop()
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder).centerCrop()
                .into(customViewHolder.mainHold);


        // }

        customViewHolder.body.setText(feedItem.getBody());
        customViewHolder.textView.setText(feedItem.getTitle());
        customViewHolder.location.setText(feedItem.getLocation());
        customViewHolder.setItem(feedItem);
    }

    @Override
    public int getItemCount() {
        return (null != homefeedItemList ? homefeedItemList.size() : 0);
    }

    class HomeCustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView mainHold;
        protected ImageView imageView ;
        protected TextView textView;
        protected TextView location;
        protected TextView body;
        HomeItem item;

        private final Context context;

        public ImageView getMainHold() {
            return mainHold;
        }

        public void setMainHold(ImageView mainHold) {
            this.mainHold = mainHold;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public TextView getLocation() {
            return location;
        }

        public void setLocation(TextView location) {
            this.location = location;
        }

        public TextView getBody() {
            return body;
        }

        public void setBody(TextView body) {
            this.body = body;
        }

        public void setItem(HomeItem _item){

            item = _item;

        }

        public HomeItem getItem(){

            return item;

        }

        public HomeCustomViewHolder(View view) {
            super(view);

            context = view.getContext();

            this.imageView = (ImageView) view.findViewById(R.id.imageProfile);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.mainHold = (ImageView) view.findViewById(R.id.thumbnail);
            this.body = (TextView) view.findViewById(R.id.body);
            this.location = (TextView) view.findViewById(R.id.location);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"Clicked: "+ item.getTitle(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, DetailedClassActivity.class);
                    intent.putExtra("tittle",item.getTitle());
                    context.startActivity(intent);


                }
            });


        }

        public ImageView getImageView(){

            return this.imageView;
        }

    }


}
