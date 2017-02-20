package com.mercyarr.gbm.vkgallry.common.model.users;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gbm19 on 18.02.2017.
 */

public class Response extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("sex")
    private int sex;

    @SerializedName("city")
    private City city;

    @SerializedName("photo_max_orig")
    private String photoMaxOrig;

    @SerializedName("is_friend")
    private long isFriend;

    @SerializedName("online")
    private long online;

    @SerializedName("counters")
    private Counters counters;

    @SerializedName("hidden")
    private long hidden;

    @SerializedName("bdate")
    private String bdate;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSex() {
        return sex;
    }

    public City getCity() {
        return city;
    }

    public String getPhotoMaxOrig() {
        return photoMaxOrig;
    }

    public long getIsFriend() {
        return isFriend;
    }

    public boolean getOnline() {
        if (online==1)
            return true;
        else
            return false;
    }

    public Counters getCounters() {
        return counters;
    }

    public long getHidden() {
        return hidden;
    }

    public String getBdate() {
        return bdate;
    }
}