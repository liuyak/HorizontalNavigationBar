package com.liuyk.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import com.liuyk.observer.EDataSetObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航栏适配器
 * <p>
 * Created by liuyakui on 2020/5/3.
 */
public abstract class BaseAdapter<T, H extends View> {
    private final EDataSetObservable mDataSetObservable = new EDataSetObservable();
    private List<T> mItems;

    //缓存所有View，避免重复创建
    private List<H> mViews;

    protected BaseAdapter() {
        mViews = new ArrayList<>();
    }

    public abstract H builderItemView(ViewGroup parent, int index);

    public abstract void bindItemView(H itemView, int index);

    public abstract void clickItem(int index);

    public void setItems(List<T> mItems) {
        this.mItems = mItems;
    }

    public T getItem(int index) {
        return mItems == null ? null : mItems.get(index);
    }

    public List<T> getItems() {
        return mItems;
    }

    public int getChildCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void notifyDataChange() {
        mDataSetObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public List<H> getViews() {
        return mViews;
    }

    public H getItemView(int index) {
        return mViews.get(index);
    }

    public void cacheItemView(H itemView) {
        mViews.add(itemView);
    }
}
