package com.hb.demo.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hb.demo.R;
import com.hb.demo.base.BaseAppCatActivity;
import com.hb.demo.base.recycler.BaseRecyclerViewAdapter;
import com.hb.demo.base.recycler.RecyclerViewHolder;
import com.hb.demo.base.recycler.SpacesItemDecoration;
import com.hb.demo.base.recycler.wrapper.EmptyWrapper;
import com.hb.demo.model.ImageModel;
import com.hb.demo.tools.MoreManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tech60 on 2017/8/21.
 */

public class GlideDemoActivity extends BaseAppCatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView                                mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout                          mRefreshLayout;
    private BaseRecyclerViewAdapter<ImageModel> mAdapter = null;
    private List<ImageModel>                    dataList = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_glide_demo;
    }

    @Override
    protected void initView() {
        initToolBar();
        setTooBarBackBtn();
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //设置下拉样式
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataList.clear();
                addImageUrl();
            }
        });
        mAdapter = new BaseRecyclerViewAdapter<ImageModel>(this, dataList, R.layout.item_picture) {
            @Override
            protected void bindDataToItemView(RecyclerViewHolder vh, ImageModel item) {
                vh.setText(R.id.tv_title, item.getTitle());
                MoreManager.getImageLoader().display(GlideDemoActivity.this,
                        vh.getView(R.id.img_icon), item.getUrl());
            }
        };
        EmptyWrapper wrapper = new EmptyWrapper(mAdapter);
        wrapper.setEmptyView(R.layout.layout_empty);
        mRecyclerView.setAdapter(wrapper);
    }

    @Override
    protected void initData() {
        setActionTitle(R.string.glide_demo);
        addImageUrl();
    }

    private void addImageUrl() {
        String[] imageUrls = new String[] {
                "http://img.lanrentuku.com/img/allimg/1706/14985263002979.jpg",
                "http://img.lanrentuku.com/img/allimg/1609/14747974667766.jpg",
                "http://img.lanrentuku.com/img/allimg/1606/14660381353675.jpg",
                "http://img.lanrentuku.com/img/allimg/1606/14650011332598.jpg",
                "http://img.lanrentuku.com/img/allimg/1605/14647062747107.jpg",
                "http://img.lanrentuku.com/img/allimg/1606/14663793246403.jpg",
                "http://img.lanrentuku.com/img/allimg/1605/14644853335596.jpg",
                "http://img.lanrentuku.com/img/allimg/1508/14404988373006.jpg",
                "http://img.lanrentuku.com/img/allimg/1205/5_120517115745_1.jpg",
                "http://pic.qiantucdn.com/58pic/26/45/20/80z58PICNws_1024.png!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center" };
        for (int i = 0; i < 10; i++) {
            ImageModel model = new ImageModel();
            model.setTitle("item" + i);
            model.setUrl(imageUrls[i]);
            dataList.add(model);
        }
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }
}
