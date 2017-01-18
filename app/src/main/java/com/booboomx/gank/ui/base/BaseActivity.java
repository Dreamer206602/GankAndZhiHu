package com.booboomx.gank.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.booboomx.gank.R;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mAppBarLayout= (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar= (Toolbar) findViewById(R.id.toolBar);
        if(mToolbar!=null&&mAppBarLayout!=null){
            setSupportActionBar(mToolbar);
            if(canBack()){
                ActionBar actionBar = getSupportActionBar();
                if(actionBar!=null){
                    // 设置ActionBar一个返回的箭头，主界面没有，次界面有
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }

            if(Build.VERSION.SDK_INT>=21){
                //Z 轴浮动
                mAppBarLayout.setElevation(10.6f);
            }
       }


       if(isSetRefresh()){
           setupSwipeRefresh();
       }



    }


    private void setupSwipeRefresh() {

    }


    /**
     * 判断子Activity 是否需要刷新的功能
     * @return
     */
    public Boolean isSetRefresh(){
        return  false;
    }

    /**
     * 判断当前的Activity 是否允许返回
     * 主界面不允许返回，次界面允许返回
     * @return
     */
    public boolean canBack(){
        return false;
    }
}
