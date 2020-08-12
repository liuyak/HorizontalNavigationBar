package com.liuyk.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.liuyk.adapter.BaseAdapter;

/**
 * HorizontalNavigationBar
 * <p>
 * Created by liuyakui on 2020/4/27.
 */
public class HorizontalNavigationBar extends HorizontalScrollView {
    private int mCurrentPosition = -1;
    private LinearLayout mItemViewContainer;
    private OnHorizontalNavigationSelectListener mOnHorizontalNavigationSelectListener;
    private DataSetObserver mDataSetObserver;
    private BaseAdapter mAdapter;

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

    public void setAdapter(BaseAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAdapter != null && mDataSetObserver == null) {
            mDataSetObserver = new MyDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mAdapter.notifyDataChange();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }
    }

    private class MyDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            setItems();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public void setItems() {
        if (mAdapter == null || mAdapter.getItems() == null || mAdapter.getItems().isEmpty()) {
            return;
        }

        mItemViewContainer.removeAllViews();
        mAdapter.getViews().clear();

        final int size = mAdapter.getItems().size();

        for (int i = 0; i < size; i++) {
            final View itemView = mAdapter.builderItemView(this, i);
//            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));

            mAdapter.cacheItemView(itemView);

            final int index = i;
            itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    if (mOnHorizontalNavigationSelectListener != null) {
                        mOnHorizontalNavigationSelectListener.select(index);
                    }
                }
            });
            mAdapter.bindItemView(itemView, index);
            mItemViewContainer.addView(itemView);
        }
        scrollTo(0, 0);
    }

    private void cacheView() {

    }

    public void setCurrentChannelItem(int index) {
        int childCount = this.mItemViewContainer.getChildCount();
        if (index > childCount - 1) {
            return;
        }
        if (index == this.mCurrentPosition) {
            return;
        }
        this.mCurrentPosition = index;
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

    public interface OnHorizontalNavigationSelectListener {
        void select(int index);
    }
}
