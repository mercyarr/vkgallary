package com.mercyarr.gbm.vkgallry.features.profile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.photosAlbum.Item;
import com.mercyarr.gbm.vkgallry.features.profile.holders.RecyclerViewHolder;

import java.util.List;

/**
 * Created by gbm19 on 18.02.2017.
 */

public class RecyclerViewAlbumAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final int ALL = 0, ALBUMS = 1;
    private List<Item> itemList;
    private Context context;

    public RecyclerViewAlbumAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.name.setText(itemList.get(position).getTitle());
        holder.sdvPhoto.setImageURI(itemList.get(position).getThumb_src());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

}
