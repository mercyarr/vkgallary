package com.mercyarr.gbm.vkgallry.common.network.api.services;

import com.mercyarr.gbm.vkgallry.common.model.users.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileService {

    @GET("users.get")
    Call<Users> getInfo(
            @Query("user_ids") long user_ids,
            @Query("access_token") String access_token,
            @Query("fields") String fields,
            @Query("name_case") String name_case
    );
}