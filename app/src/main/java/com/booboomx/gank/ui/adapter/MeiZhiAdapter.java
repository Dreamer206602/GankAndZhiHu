package com.booboomx.gank.ui.adapter;

import android.widget.ImageView;

import com.booboomx.gank.R;
import com.booboomx.gank.bean.MeizhiBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by booboomx on 17/1/18.
 */

public class MeiZhiAdapter extends BaseQuickAdapter<MeizhiBean.ResultsBean,BaseViewHolder> {
    public MeiZhiAdapter(List<MeizhiBean.ResultsBean> data) {
        super(R.layout.item_gank,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, MeizhiBean.ResultsBean item) {

        String desc = item.getDesc();
        String url = item.getUrl();

        holder.setText(R.id.tv_meizhi_title,desc);

        ImageView view = holder.getView(R.id.iv_meizhi);

        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .fitCenter()
                .into(view);

    }
}
