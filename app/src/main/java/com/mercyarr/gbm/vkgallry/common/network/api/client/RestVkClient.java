package com.mercyarr.gbm.vkgallry.common.network.api.client;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestVkClient implements Interceptor

    {

        private Retrofit retrofit;
        private Object service;

        private static String TAG = "Retrofit2";

        public static RestVkClient instanse = new RestVkClient();

    public static RestVkClient getInstanse() {
        return instanse;
    }

    private RestVkClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(this)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl.Builder urlBuilder = originalUrl.newBuilder();
        urlBuilder.addQueryParameter("v", Config.VERSION);

        Request.Builder requestBuilder = original.newBuilder()
                .url(urlBuilder.build());

        Request request = requestBuilder.build();

        Log.d(TAG, "url: "+request);

        return chain.proceed(request);
    }

    public static <T> T makeService(Class<T> serviceClass) {
        RestVkClient client = getInstanse();
        if (client.service == null || !serviceClass.isInstance(client.service)) {
            client.service = client.retrofit.create(serviceClass);
        }

        return (T) client.service;
    }
}