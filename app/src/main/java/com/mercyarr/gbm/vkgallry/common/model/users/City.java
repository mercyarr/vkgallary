package com.mercyarr.gbm.vkgallry.common.model.users;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gbm19 on 20.02.2017.
 */

public class City extends RealmObject {

    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}