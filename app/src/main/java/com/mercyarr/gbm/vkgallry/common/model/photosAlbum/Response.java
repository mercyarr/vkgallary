package com.mercyarr.gbm.vkgallry.common.model.photosAlbum;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Response {

    @SerializedName("count")
    private long count;

    @SerializedName("items")
    private List<Item> items = null;

    public long getCount() {
        return count;
    }

    public List<Item> getItems() {
        return items;
    }
}
