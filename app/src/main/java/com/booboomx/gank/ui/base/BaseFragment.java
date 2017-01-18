package com.booboomx.gank.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by booboomx on 17/1/16.
 */

public abstract class BaseFragment extends Fragment {

    public abstract int getFragmentLayoutId();

//    public SwipeRefreshLayout mSwipeRefreshLayout;



//    public boolean mIsRequestDataRefresh=false;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(getFragmentLayoutId(),container,false);
        ButterKnife.bind(this,rootView);

        initView(rootView);
        initData();
        loadData();
//        if(isSetRefresh()){
//            setupRefresh(rootView);
//        }


        return rootView;
    }

    protected abstract void loadData();

    protected abstract void initData();


    private void setupRefresh(View view) {
//        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
//        if(mSwipeRefreshLayout!=null){
//            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,R.color.refresh_progress_2,R.color.refresh_progress_3);
//
//            mSwipeRefreshLayout.setProgressViewOffset(true,0,
//                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
//
//
//            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    requestDataRefresh();
//                }
//            });
//
//        }
    }


    public void setRefresh(boolean isRequestDataRefresh){
//        if(mSwipeRefreshLayout==null){
//            return;
//        }
//
//        if(!isRequestDataRefresh){
//            mIsRequestDataRefresh=false;
//
//            Observable.timer(1000, TimeUnit.MILLISECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Action1<Long>() {
//                        @Override
//                        public void call(Long aLong) {
//                            mSwipeRefreshLayout.setRefreshing(false);
//
//                        }
//                    }, new Action1<Throwable>() {
//                        @Override
//                        public void call(Throwable throwable) {
//
//                        }
//                    });
//
//        }else{
//            mSwipeRefreshLayout.setRefreshing(true);
//        }
    }


    public void requestDataRefresh(){
//        mIsRequestDataRefresh=true;
    }

    public  Boolean isSetRefresh(){
        return true;
    }
    protected abstract void initView(View rootView);



}
