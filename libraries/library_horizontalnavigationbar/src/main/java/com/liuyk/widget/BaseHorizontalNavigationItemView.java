package com.liuyk.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * BaseHorizontalNavigationItemView
 * <p>
 * Created by liuyakui on 2020/4/27.
 */
public abstract class BaseHorizontalNavigationItemView extends LinearLayout implements Checkable {
    /**
     * 是否选中
     */
    protected boolean isChecked;

    //是否可以点击
    protected boolean isClick;

    public BaseHorizontalNavigationItemView(Context context) {
        this(context, null);
    }

    public BaseHorizontalNavigationItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseHorizontalNavigationItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setClick(boolean click) {
        isClick = click;
        setEnabled(click);
    }

    public boolean isClick() {
        return isClick;
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
    }
}
