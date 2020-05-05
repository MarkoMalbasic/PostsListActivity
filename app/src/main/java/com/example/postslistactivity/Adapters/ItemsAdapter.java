package com.example.postslistactivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.postslistactivity.Model.NewModel;
import com.example.postslistactivity.PostDetailActivity;
import com.example.postslistactivity.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {

    private Context mCtx;
    private List<NewModel> menuItemList;


    public ItemsAdapter(Context mCtx, List<NewModel> menuItemList) {
        this.mCtx = mCtx;
        this.menuItemList = menuItemList;
    }



    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.row, parent, false);
        ItemHolder itemHolder = new ItemHolder(view);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        itemViewHolder.setOnClickListener(new ItemViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                TextView mExplTv = view.findViewById(R.id.explanationTV);
                ImageView mImageView = view.findViewById(R.id.rImageView);

                String mTitle = mTitleTv.getText().toString();
                String mDesc = mDescTv.getText().toString();
                String mExpl = mExplTv.getText().toString();
                Drawable mDrawable = mImageView.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                //pass this data to new activity - PostDetailActivity
                Intent intent = new Intent(view.getContext(), PostDetailActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                intent.putExtra("IMAGE", bytes); //put bitmap image as array of bytes
                intent.putExtra("TITLE", mTitle); // put title
                intent.putExtra("DESCRIPTION", mDesc); //put description
                intent.putExtra("EXPLANATION", mExpl); // put explanation
                mCtx.startActivity(intent); //start activity
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        NewModel newModel = menuItemList.get(position);
        holder.setDetails(mCtx, newModel.getTitle(), newModel.getDescription(), newModel.getImage(), newModel.getExplanation());

    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public void setFilter(List<NewModel> myList) {

        menuItemList = new ArrayList<>();
        menuItemList.addAll(myList);
        notifyDataSetChanged();
    }


    class ItemHolder extends RecyclerView.ViewHolder{

        public View mView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }



        public void setDetails(Context mCtx, String title, String description, byte[] image, String explanation){

            TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
            TextView mExplanTv = mView.findViewById(R.id.explanationTV);
            TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv);
            ImageView mImageIv = mView.findViewById(R.id.rImageView);

            mTitleTv.setText(title);
            mDetailTv.setText(description);
            mExplanTv.setText(explanation);
            mExplanTv.setVisibility(View.GONE);


            Glide.with(mCtx).load(image).into(mImageIv);

        }
    }

    private ItemViewHolder.ClickListener mClickListener;


    public void setOnClickListener(ItemViewHolder.ClickListener listener){

        mClickListener = listener;
    }
}
