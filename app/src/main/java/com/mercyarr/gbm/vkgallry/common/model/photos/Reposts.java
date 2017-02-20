package com.mercyarr.gbm.vkgallry.common.model.photos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gbm19 on 20.02.2017.
 */

public class Reposts extends RealmObject implements Parcelable {

    @SerializedName("count")
    public long count;
    public Reposts(){
    }

    protected Reposts(Parcel in) {
        count = in.readLong();
    }

    public static final Creator<Reposts> CREATOR = new Creator<Reposts>() {

        @Override
        public Reposts createFromParcel(Parcel in) {

            return new Reposts(in);
        }

        @Override
        public Reposts[] newArray(int size) {

            return new Reposts[size];
        }
    };

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(count);
    }
}