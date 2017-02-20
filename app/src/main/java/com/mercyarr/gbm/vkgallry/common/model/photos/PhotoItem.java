package com.mercyarr.gbm.vkgallry.common.model.photos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhotoItem  extends RealmObject implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("album_id")
    private long albumId;

    @SerializedName("owner_id")
    private long ownerId;

    @SerializedName("photo_75")
    private String photoSmall;

    @SerializedName("photo_130")
    private String photoMedium;

    @SerializedName("photo_604")
    private String photoBig;

    @SerializedName("photo_807")
    private String photoLarge;

    @SerializedName("width")
    private long width;

    @SerializedName("height")
    private long height;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private long date;

    @SerializedName("post_id")
    private long postId;

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("reposts")
    private Reposts reposts;

    @SerializedName("comments")
    private Comments comments;

    @SerializedName("can_comment")
    private int canComment;

    @SerializedName("tags")
    private Tags tags;

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public Comments getComments() {
        return comments;
    }

    public Tags getTags() {
        return tags;
    }

    public long getId() {
        return id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getPhotoSmall() {
        return photoSmall;
    }

    public String getPhotoMedium() {
        return photoMedium;
    }

    public String getPhotoBig() {
        return photoBig;
    }

    public String getPhotoLarge() {
        return photoLarge;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public long getDate() {
        return date;
    }

    public long getPostId() {
        return postId;
    }

    public PhotoItem(){};

    protected PhotoItem(Parcel in) {
        id = in.readLong();
        albumId = in.readLong();
        ownerId = in.readLong();
        photoSmall = in.readString();
        photoMedium = in.readString();
        photoBig = in.readString();
        photoLarge = in.readString();
        width = in.readLong();
        height = in.readLong();
        text = in.readString();
        date = in.readLong();
        postId = in.readLong();
        likes = in.readParcelable(getClass().getClassLoader());
        reposts = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {
        @Override
        public PhotoItem createFromParcel(Parcel in) {
            return new PhotoItem(in);
        }

        @Override
        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeLong(albumId);
        parcel.writeLong(ownerId);
        parcel.writeString(photoSmall);
        parcel.writeString(photoMedium);
        parcel.writeString(photoBig);
        parcel.writeString(photoLarge);
        parcel.writeLong(width);
        parcel.writeLong(height);
        parcel.writeString(text);
        parcel.writeLong(date);
        parcel.writeLong(postId);
        parcel.writeParcelable(likes, flags);
        parcel.writeParcelable(reposts, flags);
    }
}