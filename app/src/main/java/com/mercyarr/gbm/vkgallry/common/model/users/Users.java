package com.mercyarr.gbm.vkgallry.common.model.users;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Users {

    @SerializedName("response")
    public List<Response> response = new ArrayList<Response>();

    public List<Response> getResponse(){
        return response;
    }
}
