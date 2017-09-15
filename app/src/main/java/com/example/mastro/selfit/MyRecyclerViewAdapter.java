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


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        FeedItem feedItem = feedItemList.get(i);

        //Render image using Picasso library
       // if (!TextUtils.isEmpty(feedItem.getThumbnail())) {


            final ImageView imageView = customViewHolder.getImageView();

            Glide.with(mContext).load(R.drawable.profile).asBitmap().centerCrop()
                    .error(R.drawable.profile)
                    .placeholder(R.drawable.profile).centerCrop()
                    .into(new BitmapImageViewTarget(customViewHolder.imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });



       // }

        customViewHolder.textView.setText(feedItem.getTitle());
        customViewHolder.setItem(feedItem);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView ;
        protected TextView textView;
        protected Button button;
        FeedItem item;
        private final Context context;

        public CustomViewHolder(View view) {
            super(view);

            context = view.getContext();
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.button = (Button) view.findViewById(R.id.buttonlist);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"added: "+ item.getTitle(),Toast.LENGTH_SHORT).show();

                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"Clicked: "+ item.getTitle(),Toast.LENGTH_SHORT).show();

                   // Intent intent = new Intent(context, WeekActivity.class);
                   // context.startActivity(intent);

                    MainActivity t = (MainActivity) v.getContext();
                    t.getSupportActionBar().setTitle(item.getTitle());
                    OriginalFragment fragment = new SportCenterFragment();
                    fragment.setTAG(item.getTitle());
                    fragment.setHasOptionsMenu(true);
                    t.mNavController.pushFragment(fragment);


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