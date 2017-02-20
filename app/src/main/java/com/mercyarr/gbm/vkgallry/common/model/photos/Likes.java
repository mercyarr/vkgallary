package com.mercyarr.gbm.vkgallry.common.model.photos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gbm19 on 20.02.2017.
 */

public class Likes extends RealmObject implements Parcelable {

    @SerializedName("user_likes")
    public int userLikes;

    @SerializedName("count")
    public long count;
    public Likes() {
    }

    protected Likes(Parcel in) {
        userLikes = in.readInt();
        count = in.readLong();
    }

    public static final Creator<Likes> CREATOR = new Creator<Likes>() {
        @Override
        public Likes createFromParcel(Parcel in) {
            return new Likes(in);
        }

        @Override
        public Likes[] newArray(int size) {
            return new Likes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userLikes);
        parcel.writeLong(count);
    }
}