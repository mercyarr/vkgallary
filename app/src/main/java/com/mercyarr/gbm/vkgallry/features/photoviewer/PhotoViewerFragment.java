package com.mercyarr.gbm.vkgallry.features.photoviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.photos.PhotoItem;

public class PhotoViewerFragment extends Fragment {

    private View v;
    private SimpleDraweeView sdvPhoto;
    private TextView tvTags;
    private CheckBox cbLike;
    private PhotoItem item;

    public static PhotoViewerFragment newInstance() {
        return new PhotoViewerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_photo_viewer, container, false);

        initView();
        updateDataUI();

        return v;
    }

    public void setData(PhotoItem item){
        this.item = item;
    }

    private void initView(){
        sdvPhoto = (SimpleDraweeView) v.findViewById(R.id.sdvPhoto);
        tvTags = (TextView) v.findViewById(R.id.tvTags);
        cbLike = (CheckBox) v.findViewById(R.id.cbLike);
    }

    private void updateDataUI(){
        sdvPhoto.setImageURI(item.getPhotoBig());
        tvTags.setText(item.getText());
        System.out.println("DEBUG: "+item.getLikes().count);
        if (item.getLikes().userLikes == 1)cbLike.setChecked(true);
        cbLike.setText(item.getLikes().count+" "+getActivity().getString(R.string.photo_field_likes));
    }
}