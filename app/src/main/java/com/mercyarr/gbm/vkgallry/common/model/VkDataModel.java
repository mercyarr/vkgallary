package com.mercyarr.gbm.vkgallry.common.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VkDataModel
        implements Parcelable {
    private long client_id;
    private String access_token;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(client_id);
        parcel.writeString(access_token);
    }

    public static final Creator<VkDataModel> CREATOR = new Creator<VkDataModel>() {
        @Override
        public VkDataModel createFromParcel(Parcel in) {
            return new VkDataModel(in);
        }

        @Override
        public VkDataModel[] newArray(int size) {
            return new VkDataModel[size];
        }
    };

    public VkDataModel(){

    }

    protected VkDataModel(Parcel in) {
        client_id = in.readLong();
        access_token = in.readString();
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
