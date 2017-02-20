package com.mercyarr.gbm.vkgallry.features.profile.views;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercyarr.gbm.vkgallry.R;

/**
 * Created by gbm19 on 18.02.2017.
 */

public class CounterTV extends LinearLayout {

    private static int LAYOUT = R.layout.custom_counter_tv;

    private TextView tvCount;
    private TextView tvField;
    private Context context;

    public CounterTV(Context context) {
        super(context);
        init(context);
    }

    public CounterTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CounterTV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(LAYOUT, this, true);

        this.context = context;

        tvCount = (TextView) v.findViewById(R.id.tvCount);
        tvField = (TextView) v.findViewById(R.id.tvField);
    }

    public void setCount(long count){
        tvCount.setText(String.valueOf(count));
    }

    public void setFieldName(@StringRes int title){
        tvField.setText(context.getString(title));
    }

    public void setFieldName(String title){
        tvField.setText(title);
    }
}
