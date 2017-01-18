package com.booboomx.gank.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by booboomx on 17/1/17.
 */

public class ViewPagerFgAdapter extends FragmentPagerAdapter {
    private String tag;
    private ArrayList<Fragment>mFragments;

    public ViewPagerFgAdapter(FragmentManager fm, String tag, ArrayList<Fragment> fragments) {
        super(fm);
        this.tag = tag;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
//        if(mFragments!=null){
//            return mFragments.size();
//        }
//        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tag.equals("main_view_pager")){
            switch (position){
                case 0:
                    return "知乎";
                case 1:
                    return "干货";
                case 2:
                    return "满足你的好奇心";
            }
        }

        return null;
    }
}
