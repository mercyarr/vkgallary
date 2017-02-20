package com.mercyarr.gbm.vkgallry.features.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mercyarr.gbm.vkgallry.R;
import com.mercyarr.gbm.vkgallry.common.model.VkDataModel;
import com.mercyarr.gbm.vkgallry.features.login.LoginActivity;
import com.mercyarr.gbm.vkgallry.features.profile.ProfileFragment;
import com.mercyarr.gbm.vkgallry.features.preferences.Preferences;
import com.mercyarr.gbm.vkgallry.utils.AppUtils;

public class MainActivity extends AppCompatActivity {

    private final int REGUEST_CODE_LOGIN_ACTIVITY = 1;
    private VkDataModel vkDataModel = new VkDataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserInfoFromDB();
        replaceFragment(ProfileFragment.newInstance());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            switch (requestCode)
            {
                case REGUEST_CODE_LOGIN_ACTIVITY:
                    vkDataModel.setClient_id(data.getIntExtra(LoginActivity.VK_FILED_USER_ID,0));
                    vkDataModel.setAccess_token(data.getStringExtra(LoginActivity.VK_FILED_ACCESS_TOKEN));

                    Preferences preferences = new Preferences(this);
                    preferences.setAccessToken(vkDataModel.getAccess_token());
                    preferences.setUser(vkDataModel.getClient_id());

                    break;
            }
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();
    }

    public VkDataModel getVkDataModel(){
        return this.vkDataModel;
    }

    public void startLoginActivity(){
        Intent questionIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(questionIntent, REGUEST_CODE_LOGIN_ACTIVITY);
    }

    @Override
    public void onBackPressed() {
        if (getBackStackCount()>1)
        {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    private int getBackStackCount(){
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    private void getUserInfoFromDB(){

        vkDataModel = AppUtils.getUserInfoFromPrefference(this);
    }


}