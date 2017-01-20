package com.booboomx.gank.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.booboomx.gank.R;
import com.booboomx.gank.api.ApiFactory;
import com.booboomx.gank.bean.NewsTimeLineBean;
import com.booboomx.gank.ui.activity.ZhiHuDetailActivity;
import com.booboomx.gank.ui.adapter.NetworkImageHolderView;
import com.booboomx.gank.ui.adapter.ZhiHuAdapter;
import com.booboomx.gank.ui.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

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
 * 知乎界面
 */
public class ZhiHuFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {


    public static final String  TAG="ZhiHuFragment";
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ZhiHuAdapter mAdapter;
    public   String date;

    private ConvenientBanner mBanner;
    private TextView mTvTopTitle;
    private List<String>urls=new ArrayList<>();
    private ArrayList<NewsTimeLineBean.StoriesBean>mStoriesBeen=new ArrayList<>();
    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_zhi_hu;
    }

    @Override
    protected void loadData() {
        ApiFactory.getZhiHuApi().getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsTimeLineBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsTimeLineBean newsTimeLineBean) {
                            getZhiHuData(newsTimeLineBean.getStories(),newsTimeLineBean.getDate(),newsTimeLineBean.getTop_stories());
                    }
                });

    }

    private void getZhiHuData(final List<NewsTimeLineBean.StoriesBean> stories, String time, List<NewsTimeLineBean.TopStoriesBean> top_stories) {

        date=time;
        mStoriesBeen.clear();
        if(stories!=null&&stories.size()>0){
            mStoriesBeen.addAll(stories);
            mAdapter=new ZhiHuAdapter(mStoriesBeen);
            mAdapter.addHeaderView(getHeadView());
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.setOnLoadMoreListener(this);

        mRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent=new Intent();
                intent.putExtra("id",stories.get(position).getId()+"");
                intent.setClass(getContext(), ZhiHuDetailActivity.class);
                startActivity(intent);


            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });



        urls.clear();
        for (int i = 0; i <top_stories.size() ; i++) {
            urls.add(top_stories.get(i).getImage());
        }
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },urls)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);


        /*8
        transformerList.add(DefaultTransformer.class.getSimpleName());
        transformerList.add(AccordionTransformer.class.getSimpleName());
        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
        transformerList.add(CubeInTransformer.class.getSimpleName());
        transformerList.add(CubeOutTransformer.class.getSimpleName());
        transformerList.add(DepthPageTransformer.class.getSimpleName());
        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
        transformerList.add(RotateDownTransformer.class.getSimpleName());
        transformerList.add(RotateUpTransformer.class.getSimpleName());
        transformerList.add(StackTransformer.class.getSimpleName());
        transformerList.add(ZoomInTransformer.class.getSimpleName());
        transformerList.add(ZoomOutTranformer.class.getSimpleName());
         */
        mBanner.startTurning(2000);
        mBanner.setPageTransformer(new StackTransformer());

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


    }


    private View getHeadView(){

        View view = getActivity().getLayoutInflater().inflate(R.layout.item_zhihu_top, null);

        mBanner= (ConvenientBanner) view.findViewById(R.id.banner);
        mTvTopTitle= (TextView) view.findViewById(R.id.tv_top_title);
        return view;

    }

    @Override
    public void onLoadMoreRequested() {
        Log.i(TAG, "onLoadMoreRequested: "+date);
        Log.i(TAG, "onLoadMoreRequested: getItemCount"+mAdapter.getItemCount());
        ApiFactory.getZhiHuApi().getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsTimeLineBean>() {
                        @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onNext(NewsTimeLineBean newsTimeLineBean) {
                        if(newsTimeLineBean.getStories().size()>0){
                            date="";
                            date=newsTimeLineBean.getDate();
                            Log.i(TAG, "onLoadMoreNext: "+date);
                            mAdapter.addData(newsTimeLineBean.getStories());
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }
}
