package com.mercyarr.gbm.vkgallry.common.model.users;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Counters extends RealmObject {

    @SerializedName("albums")
    public long albums;
    @SerializedName("videos")
    public long videos;
    @SerializedName("audios")
    public long audios;
    @SerializedName("notes")
    public long notes;
    @SerializedName("photos")
    public long photos;
    @SerializedName("followers")
    public long followers;
    @SerializedName("friends")
    public long friends;

}
