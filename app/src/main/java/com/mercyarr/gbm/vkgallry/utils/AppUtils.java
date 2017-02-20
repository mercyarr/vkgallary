package com.mercyarr.gbm.vkgallry.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.mercyarr.gbm.vkgallry.common.model.VkDataModel;
import com.mercyarr.gbm.vkgallry.features.preferences.Preferences;

public final class AppUtils {

    private AppUtils(){
        throw new UnsupportedOperationException();
    }

    public static VkDataModel getUserInfoFromPrefference(Context context){
        Preferences preferences = new Preferences(context);
        VkDataModel dataModel = new VkDataModel();

        dataModel.setAccess_token(preferences.getAccessToken());
        dataModel.setClient_id(preferences.getUser());

        return dataModel;
    }
}
