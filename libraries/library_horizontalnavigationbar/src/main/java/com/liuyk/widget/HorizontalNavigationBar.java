package com.liuyk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 水平滚动导航栏
 * <p>
 * Created by liuyakui on 2020/4/27.
 */
public abstract class HorizontalNavigationBar<T, H extends BaseHorizontalNavigationItemView> extends HorizontalScrollView {
    private int mCurrentPosition = -1;
    private LinearLayout mItemViewContainer;
    private OnHorizontalNavigationSelectListener mOnHorizontalNavigationSelectListener;

    private ArrayList<T> mItems;

    public HorizontalNavigationBar(Context paramContext) {
        this(paramContext, null);
    }

    public HorizontalNavigationBar(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public HorizontalNavigationBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_navigation_container, this);
        mItemViewContainer = view.findViewById(R.id.horizontal_navigation_container);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setItems(ArrayList<T> items) {
        if (items == null) return;

        this.mItems = items;
        this.mItemViewContainer.removeAllViews();
        for (int i = 0; i < items.size(); i++) {
            final H itemView = createItemView(this, i);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            renderingItemView(itemView, i, mCurrentPosition);

            final int index = i;
            itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    HorizontalNavigationBar.this.setCurrentChannelItem(index);
                    if (mOnHorizontalNavigationSelectListener != null) {
                        mOnHorizontalNavigationSelectListener.select(index);
                    }
                }
            });
            this.mItemViewContainer.addView(itemView);
        }
        scrollTo(0, 0);
    }

    public void setCurrentChannelItem(int index) {
        int childCount = this.mItemViewContainer.getChildCount();
        if (index > childCount - 1) {
            throw new RuntimeException("position more size");
        }
        if (index == this.mCurrentPosition) {
            return;
        }
        this.mCurrentPosition = index;
        for (int i = 0; i < childCount; i++) {
            H itemView = (H) this.mItemViewContainer.getChildAt(i);
            itemView.setChecked(i == mCurrentPosition);
        }
        if (mCurrentPosition == 0) {
            scrollTo(0, 0);
        } else {
            int left = this.mItemViewContainer.getChildAt(index).getLeft();
            smoothScrollTo(left, 0);
        }
    }

    public void addOnHorizontalNavigationSelectListener(OnHorizontalNavigationSelectListener onHorizontalNavigationSelectListener) {
        this.mOnHorizontalNavigationSelectListener = onHorizontalNavigationSelectListener;
    }

    public T getItem(int position) {
        return mItems == null ? null : mItems.get(position);
    }

    public abstract void renderingItemView(H itemView, int index, int currentPosition);

    public abstract H createItemView(HorizontalNavigationBar navigationBar, int position);

    public interface OnHorizontalNavigationSelectListener {
        void select(int index);
    }
}
