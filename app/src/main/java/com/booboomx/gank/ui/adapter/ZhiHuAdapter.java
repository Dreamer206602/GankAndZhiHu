package com.booboomx.gank.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.booboomx.gank.R;
import com.booboomx.gank.bean.NewsTimeLineBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by booboomx on 17/1/17.
 */

public class ZhiHuAdapter extends BaseQuickAdapter<NewsTimeLineBean.StoriesBean,BaseViewHolder> {

    public ZhiHuAdapter(List<NewsTimeLineBean.StoriesBean> data) {
        super(R.layout.item_zhihu,data);
    }
    @Override
    protected void convert(BaseViewHolder holder, NewsTimeLineBean.StoriesBean bean) {

        String imgUrl = bean.getImages().get(0);
        String title = bean.getTitle();
        if(!TextUtils.isEmpty(title)){
            holder.setText(R.id.tv_title,title);
        }else{
            holder.setText(R.id.tv_title,"");
        }
        ImageView  view = holder.getView(R.id.iv);

        if(!TextUtils.isEmpty(imgUrl)){
            Glide.with(mContext)
                    .load(imgUrl)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .centerCrop()
                    .into(view);

        }

    }
}
