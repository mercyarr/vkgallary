package com.mercyarr.gbm.vkgallry.features.profile.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mercyarr.gbm.vkgallry.common.model.photos.PhotoItem;
import com.mercyarr.gbm.vkgallry.features.photoviewer.PhotoViewerFragment;

import java.util.ArrayList;

/**
 * Created by gbm19 on 20.02.2017.
 */

public class PhotoPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<PhotoItem> myList;

    public PhotoPageAdapter(FragmentManager fm, ArrayList<PhotoItem> myList) {
        super(fm);
        this.myList = myList;
        System.out.println("DEBUG: List size = "+myList.size());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        fragment = PhotoViewerFragment.newInstance();
        ((PhotoViewerFragment)fragment).setData(myList.get(position));

        return fragment;
    }

    @Override
    public int getCount() {
        return myList.size();
    }
}