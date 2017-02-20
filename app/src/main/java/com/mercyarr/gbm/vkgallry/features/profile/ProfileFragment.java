package com.mercyarr.gbm.vkgallry.features.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eftimoff.viewpagertransformers.ZoomOutSlideTransformer;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.VkDataModel;
import com.mercyarr.gbm.vkgallry.common.model.users.Response;
import com.mercyarr.gbm.vkgallry.common.model.users.Users;
import com.mercyarr.gbm.vkgallry.common.network.api.client.RestVkClient;
import com.mercyarr.gbm.vkgallry.common.network.api.services.ProfileService;
import com.mercyarr.gbm.vkgallry.features.database.RealmManager;
import com.mercyarr.gbm.vkgallry.features.main.MainActivity;
import com.mercyarr.gbm.vkgallry.features.profile.adapters.TabPageAdapter;
import com.mercyarr.gbm.vkgallry.features.profile.views.CounterTV;
import com.mercyarr.gbm.vkgallry.utils.AppUtils;


public class ProfileFragment extends Fragment{


    private ProfileService profileService;
    private VkDataModel vkDataModel;
    private Call<Users> call;

    private Toolbar toolbar;
    private CollapsingToolbarLayout colToolbar;
    private SimpleDraweeView sdvAvatar;
    private CounterTV ctvPhoto, ctvFriends, ctvFollowing;
    private TextView tvBirthday, tvStatus;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private AppCompatActivity myContext;

    private String TAG = "ProfileFragment";

    public ProfileFragment(){

    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getVkData();
        initService();
        getProfileInfo(vkDataModel.getClient_id(),vkDataModel.getAccess_token());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        myContext = (AppCompatActivity) getActivity();

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);

        sdvAvatar = (SimpleDraweeView) v.findViewById(R.id.sdvAvatar);
        ctvPhoto = (CounterTV) v.findViewById(R.id.ctvPhoto);
        ctvFriends = (CounterTV) v.findViewById(R.id.ctvFriends);
        ctvFollowing = (CounterTV) v.findViewById(R.id.ctvFollowing);
        tvBirthday = (TextView) v.findViewById(R.id.tvBirthday);
        tvStatus = (TextView) v.findViewById(R.id.tvStatus);

        viewPager = (ViewPager) v.findViewById(R.id.vPager);
        tabLayout = (TabLayout) v.findViewById(R.id.tlAlbumsTabs);
        viewPager.setAdapter(new TabPageAdapter(myContext.getSupportFragmentManager(), myContext));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());

        return v;
    }
    //----------------------------------------------------------------------------------------------
    private void getVkData(){
        this.vkDataModel = AppUtils.getUserInfoFromPrefference(getActivity());
    }
    //----------------------------------------------------------------------------------------------
    private void initService(){
        profileService = RestVkClient.makeService(ProfileService.class);
    }
    //----------------------------------------------------------------------------------------------
    private synchronized void getProfileInfo(long user_ids, String access_token)
    {
        call = profileService.getInfo(user_ids,
                access_token,
                "is_friend,photo_max_orig,sex,online,city,counters,bdate,status","Nom");

        call.enqueue(setDataCallback);
    }
    //----------------------------------------------------------------------------------------------
    private void showData(){
        Response user = RealmManager.getUser();

        sdvAvatar.setImageURI(user.getPhotoMaxOrig());
        ctvPhoto.setFieldName(R.string.profile_field_photo);
        ctvPhoto.setCount(user.getCounters().photos);
        ctvFriends.setFieldName(R.string.profile_field_friends);
        ctvFriends.setCount(user.getCounters().friends);
        ctvFollowing.setFieldName(R.string.profile_field_followers);
        ctvFollowing.setCount(user.getCounters().followers);

        toolbar.setTitle(user.getLastName()+" "+user.getFirstName());

        if (user.getBdate() != null && user.getBdate().length()>0){
            setBirthdayData(user.getBdate());
        }
        if (user.getStatus() != null && user.getStatus().length()>0)
            tvStatus.setText(getString(R.string.profile_field_status)+user.getStatus());
    }
    //----------------------------------------------------------------------------------------------
    Callback setDataCallback = new Callback<Users>() {
        @Override
        public void onResponse(Call<Users> call, retrofit2.Response<Users> response) {
            if (response.body().getResponse() != null && response.body().getResponse().size()>0)
            {
                Response users = response.body().getResponse().get(0);

                RealmManager.setUser(users);

                showData();

                System.out.println("DEBUG: Data loaded completed");
            } else {
                ((MainActivity)getActivity()).startLoginActivity();
            }
        }
        /*@Override
        public void onResponse(Call<Users> call, Response<Users> response) {
            if (response.body().getResponse() != null && response.body().getResponse().size()>0)
            {
                Response users = response.body().getResponse().get(0);
                RealmManager.setUser(users);
                showData();
                System.out.println("DEBUG: Data loaded completed");
            } else {
                ((MainActivity)getActivity()).startLoginActivity();
            }
        }*/

        @Override
        public void onFailure(Call<Users> call, Throwable t) {
            showData();
        }
    };

    private void setBirthdayData(String birthdayData){
        int day = -1, month = -1, year = -1;
        Date bDate = null;
        try {
            SimpleDateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy");
            bDate = dateFormat.parse(birthdayData);

            day = bDate.getDate();
            month = bDate.getMonth()+1;
            year = bDate.getYear();

            if (day > -1 && month > -1 && year > -1){
                String sDay = ""+day;
                String sMonth = ""+month;
                if (day<10) sDay = "0"+day;
                if (month<10) sMonth = "0"+month;

                tvBirthday.setText(getString(R.string.profile_field_birthday)
                        + sDay+"."+sMonth+"."+year);
            }
        } catch (Exception ignore) { }

        if (bDate == null)
            try {
                SimpleDateFormat dateFormat= new SimpleDateFormat("dd.MM");
                bDate = dateFormat.parse(birthdayData);

                day = bDate.getDate();
                month = bDate.getMonth()+1;
                year = bDate.getYear();

                if (day > -1 && month > -1){
                    String sDay = ""+day;
                    String sMonth = ""+month;
                    if (day<10) sDay = "0"+day;
                    if (month<10) sMonth = "0"+month;

                    tvBirthday.setText(getString(R.string.profile_field_birthday)
                            + sDay+"."+sMonth);
                }
            } catch (Exception ignore) { }
    }
}