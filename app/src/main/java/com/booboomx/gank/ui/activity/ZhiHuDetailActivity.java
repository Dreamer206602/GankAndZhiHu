package com.booboomx.gank.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.gank.R;
import com.booboomx.gank.api.ApiFactory;
import com.booboomx.gank.bean.ZhiHuDetailBean;
import com.booboomx.gank.ui.base.BaseActivity;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.booboomx.gank.R.id.webView;

public class ZhiHuDetailActivity extends BaseActivity {

    @Bind(webView)
    WebView mWebView;
    @Bind(R.id.iv_web_img)
    ImageView mImageView;
    @Bind(R.id.tv_img_title)
    TextView mTvImgTitle;
    @Bind(R.id.tv_img_source)
    TextView mTvImgSource;


    private String id;


    @Override
    public int getLayoutId() {
        return R.layout.activity_zhi_hu_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         id = getIntent().getStringExtra("id");

        ApiFactory.getZhiHuApi().getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHuDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhiHuDetailBean zhiHuDetailBean) {
                        getDetail(zhiHuDetailBean);

                    }
                });


    }

    /**
     *
     * @param zhiHuDetailBean
     */
    private void getDetail(ZhiHuDetailBean zhiHuDetailBean) {


        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\""+zhiHuDetailBean.getCss().get(0)+"\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html =head + zhiHuDetailBean.getBody().replace(img," ");
        mWebView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
        Glide.with(ZhiHuDetailActivity.this).load(zhiHuDetailBean.getImage()).centerCrop().into(mImageView);


        mTvImgTitle.setText(zhiHuDetailBean.getTitle());
        mTvImgSource.setText(zhiHuDetailBean.getImage_source());


    }

    @Override
    protected void onDestroy() {
        if(mImageView!=null){
            Glide.clear(mImageView);
        }
        super.onDestroy();

    }
}
