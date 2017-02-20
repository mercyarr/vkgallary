package com.mercyarr.gbm.vkgallry.common.model.photosAlbum;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PhotoAlbum {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public List<Item> getItems(){
        return response.getItems();
    }

    public long getCount(){
        return response.getCount();
    }

}
