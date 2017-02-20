package com.mercyarr.gbm.vkgallry.common.model.photosAlbum;


import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Item {
        @SerializedName("id")
        private long id;

        @SerializedName("thumb_id")
        private long thumbId;

        @SerializedName("owner_id")
        private long ownerId;

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("created")
        private long created;

        @SerializedName("updated")
        private long updated;

        @SerializedName("size")
        private long size;

        @SerializedName("thumb_is_last")
        private long thumbIsLast;

        @SerializedName("thumb_src")
        private String thumb_src;

        @SerializedName("privacy_view")
        private List<String> privacyView = null;

        @SerializedName("privacy_comment")
        private List<String> privacyComment = null;

        public long getId() {
            return id;
        }

        public long getThumbId() {
            return thumbId;
        }

        public long getOwnerId() {
            return ownerId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public long getCreated() {
            return created;
        }

        public long getUpdated() {
            return updated;
        }

        public long getSize() {
            return size;
        }

        public long getThumbIsLast() {
            return thumbIsLast;
        }

        public String getThumb_src() {
            return thumb_src;
        }

        public List<String> getPrivacyView() {
            return privacyView;
        }

        public List<String> getPrivacyComment() {
            return privacyComment;
        }

}