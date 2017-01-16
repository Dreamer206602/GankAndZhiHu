package com.booboomx.gank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * Created by booboomx on 17/1/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public AppBarLayout mAppBarLayout;
    public Toolbar mToolbar;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private  boolean mIsRequestDataRefresh=false;

    public abstract  int getLayoutId();






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);


    }
}
