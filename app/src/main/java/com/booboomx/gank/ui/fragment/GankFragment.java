package com.booboomx.gank.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

import com.booboomx.gank.R;
import com.booboomx.gank.api.ApiFactory;
import com.booboomx.gank.bean.MeizhiBean;
import com.booboomx.gank.ui.adapter.MeiZhiAdapter;
import com.booboomx.gank.ui.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    public static final String  TAG="GankFragment";
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private int page=1;
    private MeiZhiAdapter mMeiZhiAdapter;
    private List<MeizhiBean.ResultsBean>mBeanList=new ArrayList<>();

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void loadData() {

        ApiFactory.getGankApi().getMeizhiData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeizhiBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MeizhiBean meizhiBean) {
                        getMeizhiData(meizhiBean.getResults());
                    }
                });
    }

    private void getMeizhiData(List<MeizhiBean.ResultsBean> results) {

        mBeanList.clear();
        if(results!=null&&results.size()>0){
            mBeanList.addAll(results);
            mMeiZhiAdapter=new MeiZhiAdapter(mBeanList);
            mRecyclerView.setAdapter(mMeiZhiAdapter);
            mMeiZhiAdapter.setOnLoadMoreListener(this);

        }
//        mMeiZhiAdapter.openLoadAnimation();


    }

    @Override
    protected void initData() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Observable.timer(1000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                loadData();
                                mSwipeRefreshLayout.setRefreshing(false);

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });

            }
        });

    }


    @Override
    protected void initView(View rootView) {

        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,R.color.refresh_progress_2,R.color.refresh_progress_3);
        mSwipeRefreshLayout.setProgressViewOffset(true,0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));


        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

    }

    @Override
    public void onLoadMoreRequested() {
//        mMeiZhiAdapter.loadMoreEnd(true);
        page++;
        ApiFactory.getGankApi().getMeizhiData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeizhiBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MeizhiBean meizhiBean) {

                        mMeiZhiAdapter.addData(meizhiBean.getResults());

//                        if(meizhiBean.getResults().size()>0){
//                        }
                    }
                });
    }
}
