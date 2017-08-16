package com.hb.demo.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hb.demo.Navigation;
import com.hb.demo.base.BaseAppCatActivity;
import com.hb.demo.event.SelectSuccessEvent;
import com.hb.demo.placeselectdemo.R;
import com.hb.demo.utils.RxBus.RxBus;
import com.hb.demo.utils.helper.RxSchedulers;

import butterknife.Bind;
import rx.Subscriber;

public class MainActivity extends BaseAppCatActivity implements View.OnClickListener {
    @Bind(R.id.tv_select_content)
    TextView selectContentTv;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar();
        setOnClick(R.id.ll_city_select);
        registerRxBus();
        initPermission();
    }

    /**
     * 动态设置权限
     */
    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.i("TEST", "Granted");
            //init(barcodeScannerView, getIntent(), null);
        } else {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);//1 can be another integer
        }
    }
    private void registerRxBus() {
        addSubscription(RxBus.getDefault().toObserverable(SelectSuccessEvent.class).compose(RxSchedulers.io_main()).subscribe(new Subscriber<SelectSuccessEvent>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SelectSuccessEvent selectSuccessEvent) {
                selectContentTv.setText(selectSuccessEvent.address);
            }
        }));
    }

    @Override
    protected void initData() {
        setActionTitle(getString(R.string.title));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_city_select:
                Navigation.goPlaceSelectPage(this, -1, "");
                break;
        }
    }
}
