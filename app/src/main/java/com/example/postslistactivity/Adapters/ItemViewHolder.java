package com.example.postslistactivity.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.postslistactivity.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public View mView;

    public ItemViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
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
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        mImageIv.setImageBitmap(imageBitmap);
    }

    private ItemViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(ItemViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
