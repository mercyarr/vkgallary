package com.mercyarr.gbm.vkgallry.features.database;

import com.mercyarr.gbm.vkgallry.common.model.photos.PhotoItem;
import com.mercyarr.gbm.vkgallry.common.model.users.Response;

import java.util.List;

import io.realm.Realm;

/**
 * Created by gbm19 on 20.02.2017.
 */

public class RealmManager {

    private RealmManager(){
        throw new UnsupportedOperationException();
    }

    public static void setUser(Response user){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

        System.out.println("DEBUG: setUser Realm");
    }

    public static Response getUser(){
        Realm realm = Realm.getDefaultInstance();

        Response user = realm.where(Response.class).findAll().first();

        System.out.println("DEBUG: getUser Realm");

        return user;
    }

    public static void setPhoto(PhotoItem photo){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(photo);
        realm.commitTransaction();

        System.out.println("DEBUG: setPhoto Realm");
    }

    public static void setPhoto(List<PhotoItem> photo){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(photo);
        realm.commitTransaction();

        System.out.println("DEBUG: setPhotoList Realm");
    }

    public static PhotoItem getPhoto(long id){
        Realm realm = Realm.getDefaultInstance();

        PhotoItem photo = realm.where(PhotoItem.class).equalTo("id",id).findFirst();

        return photo;
    }

    public static List<PhotoItem> getPhotos(){
        Realm realm = Realm.getDefaultInstance();

        List<PhotoItem> photoItemList = realm.where(PhotoItem.class).findAll();

        return photoItemList;
    }
}