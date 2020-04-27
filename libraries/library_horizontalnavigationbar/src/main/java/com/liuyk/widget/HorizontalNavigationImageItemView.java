package com.liuyk.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * 图片类型的滚动栏
 * <p>
 * Created by liuyakui on 2020/4/27.
 */
public class HorizontalNavigationImageItemView extends BaseHorizontalNavigationItemView {
    private ImageView imageView;

    public HorizontalNavigationImageItemView(Context context) {
        this(context, null);
    }

    public HorizontalNavigationImageItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalNavigationImageItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View mItemView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_bar_image_layout, this);
        imageView = mItemView.findViewById(R.id.image);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}