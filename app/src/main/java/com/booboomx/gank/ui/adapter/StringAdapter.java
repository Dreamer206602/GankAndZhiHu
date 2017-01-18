package com.booboomx.gank.ui.adapter;

import com.booboomx.gank.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by booboomx on 17/1/18.
 */

public class StringAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public StringAdapter(List<String> data) {
        super(R.layout.item_zhihu,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s) {


        holder.setText(R.id.tv_title,s);

    }
}
