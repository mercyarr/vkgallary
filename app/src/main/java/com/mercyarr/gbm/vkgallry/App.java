package com.mercyarr.gbm.vkgallry;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
/**
 * Created by gbm19 on 16.12.2016.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        Realm.init(this);
    }
}
