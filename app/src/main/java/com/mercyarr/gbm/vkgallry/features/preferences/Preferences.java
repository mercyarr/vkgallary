package com.mercyarr.gbm.vkgallry.features.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class Preferences {

    private static final String APP_KEY = "vk_client_";
    private static final String PREFERENCES_NAME = APP_KEY + "shared_preferences_";
    private static final String KEY_ACCESS_TOKEN = PREFERENCES_NAME + "access_token";
    private static final String KEY_USER_ID = "user_id";
    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public SharedPreferences getReader() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
    }
    public String getAccessToken() {
        return getReader().getString(KEY_ACCESS_TOKEN,
                "7a6fa4dff77a228eeda56603b8f53806c883f011c40b72630bb50df056f6479e52a");
    }

    public void setAccessToken(String token) {
        getEditor().putString(KEY_ACCESS_TOKEN, token).apply();
    }

    public void setUser(long userId) {
        getEditor().putLong(KEY_USER_ID, userId).apply();
    }

    public long getUser() {
        return getReader().getLong(KEY_USER_ID, 0);
    }

    private <T> void putObject(String key, T object) {
        getEditor()
                .putString(
                        key,
                        new GsonBuilder().create().toJson(object))
                .commit();
    }

    private <T> T getObject(String key, Type type) {
        String json = getReader().getString(key, null);
        return new GsonBuilder().create().fromJson(json, type);
    }

    public void clear() {
        getEditor().clear().commit();
    }
}