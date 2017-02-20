package com.mercyarr.gbm.vkgallry.common.model.photos;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Photo{

    @SerializedName("response")
    private PhotoResponse response;

    public PhotoResponse getResponse(){
        return response;
    }

    public List<PhotoItem> getItems(){
        return response.getItems();
    }

    public long getCount(){
        return response.getCount();
    }
}
