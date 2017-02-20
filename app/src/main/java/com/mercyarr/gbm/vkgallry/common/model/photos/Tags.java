package com.mercyarr.gbm.vkgallry.common.model.photos;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gbm19 on 20.02.2017.
 */

public class Tags extends RealmObject {

    @SerializedName("count")
    public long count;

    public Tags(){};
}
