package com.mercyarr.gbm.vkgallry.common.model.photos;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Comments extends RealmObject {

    @SerializedName("response")
    public PhotoResponse response;

    public Comments(){};
}

