package com.mercyarr.gbm.vkgallry.features.profile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.photos.PhotoItem;
import com.mercyarr.gbm.vkgallry.features.profile.holders.RecyclerViewHolder;

import java.util.List;

public class RecyclerViewPhotoAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final int ALL = 0, ALBUMS = 1;
    private List<PhotoItem> itemList;
    private Context context;
    private RecyclerViewHolder rcv;

    private IOnItemClickListener mItemClickListener;

    public RecyclerViewPhotoAdapter(List<PhotoItem> itemList, Context context,
                                    final IOnItemClickListener mItemClickListener) {
        this.itemList = itemList;
        this.context = context;
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        rcv = new RecyclerViewHolder(layoutView);
        rcv.setOnItemClickListener(mItemClickListener);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String name = itemList.get(position).getText();
        if (!name.startsWith("Original"))
            holder.name.setText(name);
        else
            holder.name.setVisibility(View.GONE);
        holder.sdvPhoto.setImageURI(getPhoto(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private String getPhoto(int position){
        String url = "";

        url = itemList.get(position).getPhotoLarge();
        if (url != null) return url;

        url = itemList.get(position).getPhotoBig();
        if (url != null) return url;

        url = itemList.get(position).getPhotoMedium();
        if (url != null) return url;

        url = itemList.get(position).getPhotoSmall();
        if (url != null) return url;

        return "";
    }
}
