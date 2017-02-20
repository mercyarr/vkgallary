package com.mercyarr.gbm.vkgallry.features.profile.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.features.profile.albums.AlbumsFragment;
import com.mercyarr.gbm.vkgallry.features.profile.albums.AllPhotoFragment;

/**
 * Created by gbm19 on 18.02.2017.
 */

public class TabPageAdapter extends FragmentStatePagerAdapter {

    Context context;

    public TabPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position)
        {
            case 0:
                fragment = AllPhotoFragment.newInstance();
                break;
            case 1:
                fragment = AlbumsFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return context.getString(R.string.profile_field_tab_all);
            case 1:
                return context.getString(R.string.profile_field_tab_albums);
            default:
                return super.getPageTitle(position);
        }
    }
}
