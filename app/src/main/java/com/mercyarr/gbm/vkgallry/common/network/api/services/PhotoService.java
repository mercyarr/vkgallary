package com.mercyarr.gbm.vkgallry.common.network.api.services;

import com.mercyarr.gbm.vkgallry.common.model.photos.Photo;
import com.mercyarr.gbm.vkgallry.common.model.photosAlbum.PhotoAlbum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gbm19 on 02.02.2017.
 */

public interface PhotoService {

    @GET("photos.get")
    Call<Photo> getAllPhoto(
            @Query("owner_id") long user_ids,
            @Query("access_token") String access_token,
            @Query("album_id") String album_id,
            @Query("extended") int extended,
            @Query("offset") int offset,
            @Query("count") int count
    );

    @GET("photos.getAlbums")
    Call<PhotoAlbum> getAllbums(
            @Query("owner_id") long user_ids,
            @Query("access_token") String access_token,
            @Query("need_covers") int need_covers

    );
}