package com.liuyk.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 水平滚动导航栏
 * <p>
 * Created by liuyakui on 2020/4/27.
 */
public class HorizontalNavigationItemView extends BaseHorizontalNavigationItemView {
    protected int mSplitColor = Color.RED;

    private View mChannelSplit;
    private TextView mChannelTitle;

    /**
     * 是否有下划线
     */
    private boolean isChannelSplit;

    public HorizontalNavigationItemView(Context context) {
        this(context, null);
    }

    public HorizontalNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View mItemView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_bar_layout, this);
        mChannelTitle = mItemView.findViewById(R.id.horizontal_bar_channel_title);
        mChannelSplit = mItemView.findViewById(R.id.horizontal_bar_channel_split);
    }

    /**
     * 设置标题
     *
     * @param channelTitle 标题
     */
    public void setChannelTitle(String channelTitle) {
        mChannelTitle.setText(channelTitle);
    }

    public boolean isChannelSplit() {
        return isChannelSplit;
    }

    public void setChannelSplit(boolean channelSplit) {
        isChannelSplit = channelSplit;
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked) {
            if (isChannelSplit) {
                mChannelSplit.setVisibility(View.VISIBLE);
                mChannelSplit.setBackgroundColor(mSplitColor);
            }
            mChannelTitle.setTextColor(Color.parseColor("#ffcc66"));
        } else {
            mChannelTitle.setTextColor(Color.BLACK);
            mChannelSplit.setVisibility(INVISIBLE);
        }
    }

    public void setSplitColor(int resId) {
        mSplitColor = resId;
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
