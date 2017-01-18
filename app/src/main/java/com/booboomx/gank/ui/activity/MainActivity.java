package com.booboomx.gank.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.booboomx.gank.R;
import com.booboomx.gank.ui.adapter.ViewPagerFgAdapter;
import com.booboomx.gank.ui.base.BaseActivity;
import com.booboomx.gank.ui.fragment.DailyFragment;
import com.booboomx.gank.ui.fragment.GankFragment;
import com.booboomx.gank.ui.fragment.ZhiHuFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private ArrayList<Fragment>mFragments=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabView();
    }

    private void initTabView() {
        mFragments.add(new ZhiHuFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new DailyFragment());
        Log.i("ssssss", "initTabView: "+mFragments.size());
        // 设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new ViewPagerFgAdapter(getSupportFragmentManager(),"main_view_pager",mFragments));
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
