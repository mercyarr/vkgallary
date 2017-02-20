package com.mercyarr.gbm.vkgallry.features.photoviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.photos.PhotoItem;
import com.mercyarr.gbm.vkgallry.features.profile.adapters.PhotoPageAdapter;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    private ViewPager viewPagerPhotoViewer;
    private ArrayList<PhotoItem> myList;
    private int numberPhotoShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        myList = this.getIntent().getParcelableArrayListExtra("myphotos");
        numberPhotoShow = getIntent().getIntExtra("numberPhotoShow", 0);

        viewPagerPhotoViewer = (ViewPager) findViewById(R.id.viewPagerPhotoViewer);
        viewPagerPhotoViewer.setAdapter(new PhotoPageAdapter(getSupportFragmentManager(), myList));
        viewPagerPhotoViewer.setCurrentItem(numberPhotoShow);
    }
}
