package com.hb.demo.placeselectdemo;

import com.hb.demo.base.BaseAppCatActivity;

public class MainActivity extends BaseAppCatActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar();

    }

    @Override
    protected void initData() {
        setActionTitle(getString(R.string.title));
    }

}
