package com.example.mastro.selfit;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by Mastro on 4/20/2017.
 */

public class ContentRecyclerViewAdapter extends RecyclerView.Adapter <ContentRecyclerViewAdapter.ContentViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;

    public ContentRecyclerViewAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public ContentRecyclerViewAdapter.ContentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_plan, null);

        return new ContentRecyclerViewAdapter.ContentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContentRecyclerViewAdapter.ContentViewHolder customViewHolder, int i) {
        FeedItem feedItem = feedItemList.get(i);

        customViewHolder.textView.setText(feedItem.getTitle());
        customViewHolder.setItem(feedItem);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView ;
        protected TextView textView;
        FeedItem item;
        private final Context context;

        public ContentViewHolder(View view) {
            super(view);

            context = view.getContext();
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"Clicked: "+ item.getTitle(),Toast.LENGTH_SHORT).show();
                    MainActivity t = (MainActivity) v.getContext();
                    PlanFragment aux = (PlanFragment) t.mNavController.getCurrentFrag();


                    aux.update(item.getTitle());

                }
            });




        }


        public ImageView getImageView(){

            return this.imageView;
        }

        public void setItem(FeedItem _item){

            item = _item;

        }

        public FeedItem getItem(){

            return item;

        }

    }

}