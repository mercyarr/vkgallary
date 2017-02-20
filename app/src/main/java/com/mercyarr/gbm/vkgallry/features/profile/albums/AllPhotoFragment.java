package com.mercyarr.gbm.vkgallry.features.profile.albums;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.VkDataModel;
import com.mercyarr.gbm.vkgallry.common.model.photos.PhotoItem;
import com.mercyarr.gbm.vkgallry.common.model.photos.Photo;
import com.mercyarr.gbm.vkgallry.common.network.api.client.RestVkClient;
import com.mercyarr.gbm.vkgallry.common.network.api.services.PhotoService;
import com.mercyarr.gbm.vkgallry.features.database.RealmManager;
import com.mercyarr.gbm.vkgallry.features.main.MainActivity;
import com.mercyarr.gbm.vkgallry.features.photoviewer.PhotoActivity;
import com.mercyarr.gbm.vkgallry.features.profile.adapters.IOnItemClickListener;
import com.mercyarr.gbm.vkgallry.features.profile.adapters.RecyclerViewPhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPhotoFragment extends Fragment {

    private PhotoService photoService;
    private VkDataModel vkDataModel;
    private Call<Photo> call;
    private ArrayList<PhotoItem> photo;
    private RecyclerView rView;

    private GridLayoutManager lLayout;
    private View v;

    public static AllPhotoFragment newInstance() {
        return new AllPhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photo = new ArrayList<>();

        getVkData();
        initService();
        getAllPhoto(vkDataModel.getClient_id(),vkDataModel.getAccess_token());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_photo, container, false);

        lLayout = new GridLayoutManager(getActivity(), 3);

        rView = (RecyclerView)v.findViewById(R.id.rvPhoto);
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
    private void getAllPhoto(long user_ids, String access_token)
    {
        call = photoService.getAllPhoto(user_ids,
                access_token,
                "wall",
                1,
                0,
                200);//33

        call.enqueue(setPhotoCallback);
    }
    //----------------------------------------------------------------------------------------------
    IOnItemClickListener onItemClickListener = new IOnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(getActivity(), PhotoActivity.class);

            intent.putParcelableArrayListExtra("myphotos", photo);
            intent.putExtra("numberPhotoShow", position);

            startActivity(intent);
        }
    };
    //----------------------------------------------------------------------------------------------
    private void showData(){
        photo.addAll(RealmManager.getPhotos());

        RecyclerViewPhotoAdapter rcAdapter = new RecyclerViewPhotoAdapter(photo, getActivity(),onItemClickListener);
        rView.setAdapter(rcAdapter);
    }
    //----------------------------------------------------------------------------------------------
    Callback<Photo> setPhotoCallback = new Callback<Photo>() {
        @Override
        public void onResponse(Call<Photo> call, Response<Photo> response) {
            if (response.body().getResponse() != null && response.body().getItems().size()>0) {
                RealmManager.setPhoto(response.body().getItems());
                showData();
            }
        }

        @Override
        public void onFailure(Call<Photo> call, Throwable t) {
        }
    };
}