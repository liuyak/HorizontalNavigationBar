package com.liuyk.horizontalnavigationbar;

import android.graphics.Color;
import android.view.ViewGroup;

import com.liuyk.adapter.BaseAdapter;
import com.liuyk.widget.HorizontalNavigationItemView;

import java.util.List;

/**
 * DESC
 * <p>
 * Created by liuyakui on 2020/8/12.
 */
public class ChannelAdapter extends BaseAdapter<Channel, HorizontalNavigationItemView> {
    @Override
    public HorizontalNavigationItemView builderItemView(ViewGroup parent, int index) {
        return new HorizontalNavigationItemView(parent.getContext());
    }

    @Override
    public void bindItemView(HorizontalNavigationItemView itemView, int index) {
        Channel channel = getItem(index);
        itemView.setChannelTitle(channel.getChannelName());
        itemView.setChecked(channel.isSelect);
        itemView.setSplit(channel.isSelect);
        itemView.setClick(true);
        itemView.setSelectBold(true);
        itemView.setSelectTextSize(20f);
        itemView.setSplitColor(Color.RED);
    }

    @Override
    public void clickItem(int index) {
        if (getItems() == null) {
            return;
        }
        reset();
        getItem(index).isSelect = true;
        for (int i = 0; i < getViews().size(); i++) {
            HorizontalNavigationItemView itemView = getItemView(i);
            bindItemView(itemView, i);
        }
    }

    private void reset() {
        List<Channel> items = getItems();
        if (items == null || items.isEmpty()) {
            return;
        }
        for (Channel channel : items) {
            channel.isSelect = false;
        }
    }
}
