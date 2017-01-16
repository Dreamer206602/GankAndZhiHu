package com.booboomx.gank.ui;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.booboomx.gank.MainActivity;
import com.booboomx.gank.R;
import com.booboomx.gank.base.BaseActivity;
import com.booboomx.gank.widget.SplashView;

import java.util.Random;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.splashView)
    SplashView mSplashView;

    @Bind(R.id.tv_splash_info)
    TextView mTvSplashInfo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    protected void onStart() {
        super.onStart();
        AssetManager manager=getAssets();
        Typeface fromAsset = Typeface.createFromAsset(manager, "fonts.rm_albion.ttf");
        mTvSplashInfo.setTypeface(fromAsset);
        startLoadingData();
    }

    private Handler mHandler =new Handler();
    private void startLoadingData() {


        Random random=new Random();
//        mHandler.postDelayed(this::onLoadingDataEnd,1000+random.nextInt(100));
        onLoadingDataEnd();

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


                finish();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }
}
