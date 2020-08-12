package com.liuyk.horizontalnavigationbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuyk.widget.HorizontalNavigationBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HorizontalNavigationBar.OnHorizontalNavigationSelectListener {
    private HorizontalNavigationBar mHorizontalNavigationBar;
    private ChannelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mHorizontalNavigationBar = findViewById(R.id.horizontal_navigation);
        mHorizontalNavigationBar.addOnHorizontalNavigationSelectListener(this);
        adapter = new ChannelAdapter();
        adapter.setItems(getData());
        mHorizontalNavigationBar.setAdapter(adapter);
        mHorizontalNavigationBar.setCurrentChannelItem(0);
    }

    private ArrayList<Channel> getData() {
        final ArrayList<Channel> items = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            final Channel channel = new Channel();
            if (i == 0) {
                channel.isSelect = true;
            }
            channel.setChannelName("选项" + (i + 1));
            items.add(channel);
        }
        return items;
    }

    @Override
    public void select(int index) {
        adapter.clickItem(index);
    }
}
