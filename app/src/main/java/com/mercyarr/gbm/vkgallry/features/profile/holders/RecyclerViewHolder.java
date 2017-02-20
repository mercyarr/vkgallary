package com.mercyarr.gbm.vkgallry.features.profile.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.features.profile.adapters.IOnItemClickListener;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public SimpleDraweeView sdvPhoto;
    private IOnItemClickListener mItemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name = (TextView)itemView.findViewById(R.id.albumName);
        sdvPhoto = (SimpleDraweeView)itemView.findViewById(R.id.sdvAlbumPhoto);
    }

    public void setTextVisibility(int isVisible){
        name.setVisibility(isVisible);
    }

    public void setOnItemClickListener(final IOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}