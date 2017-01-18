package com.booboomx.gank.ui.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.booboomx.gank.R;
import com.booboomx.gank.widget.SplashView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.splashView)
    SplashView mSplashView;

    @Bind(R.id.tv_splash_info)
    TextView mTvSplashInfo;
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_welcome;
//    }


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        AssetManager manager=getAssets();
        Typeface fromAsset = Typeface.createFromAsset(manager, "fonts/rm_albion.ttf");
        mTvSplashInfo.setTypeface(fromAsset);
        startLoadingData();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        AssetManager manager=getAssets();
//        Typeface fromAsset = Typeface.createFromAsset(manager, "fonts/rm_albion.ttf");
//        mTvSplashInfo.setTypeface(fromAsset);
//        startLoadingData();
//    }

    private void startLoadingData() {
        Random random=new Random();
        Observable.timer(1000+random.nextInt(100), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        onLoadingDataEnd();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    private void onLoadingDataEnd(){

        mSplashView.splashAndDisappear(new SplashView.ISPlashListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onUpdate(float completionFraction) {

            }

            @Override
            public void onEnd() {
                mSplashView=null;
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }


}
