package com.liuyk.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * HorizontalNavigationBar
 * <p>
 * Created by liuyakui on 2020/4/27.
 */
public class HorizontalNavigationItemView extends BaseHorizontalNavigationItemView {
    //split color
    protected int mSplitColor = Color.BLACK;

    private View mChannelSplit;
    private TextView mChannelTitle;

    private boolean isSplit;
    private float selectTextSize;
    private boolean isBoldSelect;

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

    public boolean isSplit() {
        return isSplit;
    }

    public void setSplit(boolean isSplit) {
        this.isSplit = isSplit;
        mChannelSplit.setVisibility(isSplit ? VISIBLE : GONE);
    }

    /**
     * 选择后的字体大小
     *
     * @param size size
     */
    public void setSelectTextSize(float size) {
        selectTextSize = size;
    }

    /**
     * 选中是否加粗
     *
     * @param bold isBold
     */
    public void setSelectBold(boolean bold) {
        isBoldSelect = bold;
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked) {
            mChannelSplit.setVisibility(isSplit ? VISIBLE : GONE);
            mChannelSplit.setBackgroundColor(mSplitColor);
            mChannelTitle.setTextColor(Color.parseColor("#ffcc66"));
            mChannelTitle.setTextSize(selectTextSize);
            mChannelTitle.getPaint().setFakeBoldText(isBoldSelect);
        } else {
            mChannelSplit.setVisibility(INVISIBLE);
            mChannelTitle.setTextColor(Color.BLACK);
            mChannelTitle.setTextSize(17);
            mChannelTitle.getPaint().setFakeBoldText(false);
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
