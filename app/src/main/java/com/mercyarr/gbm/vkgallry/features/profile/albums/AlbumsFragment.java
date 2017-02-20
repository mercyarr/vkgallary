package com.mercyarr.gbm.vkgallry.features.profile.albums;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.VkDataModel;
import com.mercyarr.gbm.vkgallry.common.model.photosAlbum.Item;
import com.mercyarr.gbm.vkgallry.common.model.photosAlbum.PhotoAlbum;
import com.mercyarr.gbm.vkgallry.common.network.api.client.RestVkClient;
import com.mercyarr.gbm.vkgallry.common.network.api.services.PhotoService;
import com.mercyarr.gbm.vkgallry.features.main.MainActivity;
import com.mercyarr.gbm.vkgallry.features.profile.adapters.RecyclerViewAlbumAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gbm19 on 18.02.2017.
 */

public class AlbumsFragment extends Fragment {

    private PhotoService photoService;
    private VkDataModel vkDataModel;
    private Call<PhotoAlbum> call;
    private RecyclerView rView;
    private GridLayoutManager lLayout;

    private View v;

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVkData();
        initService();
        getAlbums(vkDataModel.getClient_id(),vkDataModel.getAccess_token());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_albums, container, false);

        lLayout = new GridLayoutManager(getActivity(), 2);

        rView = (RecyclerView)v.findViewById(R.id.rvPhotoAlbum);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        return v;
    }
    //----------------------------------------------------------------------------------------------
    private void getVkData(){
        this.vkDataModel = ((MainActivity)getActivity()).getVkDataModel();
    }
    //----------------------------------------------------------------------------------------------
    private void initService(){
        photoService = RestVkClient.makeService(PhotoService.class);
    }
    //----------------------------------------------------------------------------------------------
    private void getAlbums(long user_ids, String access_token)
    {
        call = photoService.getAllbums(
                user_ids,
                access_token,
                1
        );

        call.enqueue(setPhotoCallback);
    }
    //----------------------------------------------------------------------------------------------
    Callback setPhotoCallback = new Callback<PhotoAlbum>() {
        @Override
        public void onResponse(Call<PhotoAlbum> call, Response<PhotoAlbum> response) {
            if (response.body().getResponse() != null && response.body().getItems().size()>0) {
                List<Item> photo = response.body().getItems();

                RecyclerViewAlbumAdapter rcAdapter = new RecyclerViewAlbumAdapter(photo, getActivity());
                rView.setAdapter(rcAdapter);
            } else {
                ((MainActivity)getActivity()).startLoginActivity();
            }
        }

        @Override
        public void onFailure(Call<PhotoAlbum> call, Throwable t) {
        }
    };
}
