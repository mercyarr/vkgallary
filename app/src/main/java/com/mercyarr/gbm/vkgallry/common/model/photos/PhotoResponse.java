package com.mercyarr.gbm.vkgallry.common.model.photos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class PhotoResponse extends RealmObject {
    @SerializedName("count")
    private long count;

    @SerializedName("items")
    private RealmList<PhotoItem> items = null;

    public long getCount() {
        return count;
    }

    public RealmList<PhotoItem> getItems() {
        return items;
    }

    public PhotoResponse(){};
}