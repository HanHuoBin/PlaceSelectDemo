package com.hb.demo.ui;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hb.demo.Navigation;
import com.hb.demo.base.BaseAppCatActivity;
import com.hb.demo.base.adapter.CommonAdapter;
import com.hb.demo.base.adapter.ViewHolder;
import com.hb.demo.event.SelectSuccessEvent;
import com.hb.demo.model.db.place.City;
import com.hb.demo.placeselectdemo.R;
import com.hb.demo.utils.RxBus.RxBus;
import com.hb.demo.utils.db.DBManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by hanbin on 2017/8/16.
 */

public class CitySelectActivity extends BaseAppCatActivity{
    public static final String  EXTRA_ID     = "provinceId";
    public static final String  EXTRA_NAME   = "name";
    @Bind(R.id.listView)
    ListView mListView;
    private CommonAdapter<City> mAdapter     = null;
    private List<City> provinceList = new ArrayList<>();
    private int                 provinceId   = -1;
    private String              provinceName = "";
    private String              cityName     = "";
    private DBManager dbManager    = null;
    private SQLiteDatabase db           = null;

    @Override
    protected int initLayout() {
        return R.layout.activity_city_select;
    }

    @Override
    protected void initView() {
        initToolBar();
        setTooBarBackBtn();
        mAdapter = new CommonAdapter<City>(this, provinceList, R.layout.item_place) {
            @Override
            public void convert(ViewHolder helper, City item) {
                helper.setText(R.id.tv_place, item.getName());

            }
        };
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City model = provinceList.get(position);
                if (provinceId < 0) {
                    Navigation.goPlaceSelectPage(CitySelectActivity.this, model.getId(),
                            model.getName());
                } else {
                    String saveContent = provinceName + " " + model.getName() + " "
                            + model.getCityNum();
                    RxBus.getDefault()
                            .post(new SelectSuccessEvent(saveContent));
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData() {
        setActionTitle("城市选择");
        registerRxBus();
        provinceId = getIntent().getIntExtra(EXTRA_ID, 0);
        provinceName = getIntent().getStringExtra(EXTRA_NAME);
        if (provinceName != null) {
            cityName = cityName + provinceName;
        }
        getCityList();
    }

    private void registerRxBus() {
        addSubscription(RxBus.getDefault().toObserverable(SelectSuccessEvent.class)
                .subscribe(new Subscriber<SelectSuccessEvent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SelectSuccessEvent selectSuccessEvent) {
                        finish();
                    }
                }));
    }

    private void getCityList() {
        if (dbManager == null) {
            dbManager = new DBManager(CitySelectActivity.this);
        }
        if (db == null) {
            db = dbManager.DBManager();
        }
        provinceList.clear();
        if (provinceId == -1) {
            provinceList.addAll(dbManager.queryProvinces(db, new String[] { "_id", "name" },
                    "_id >= ?", new String[] { "0" }));
        } else {
            provinceList.addAll(
                    dbManager.queryCitysByProvinceId(db, new String[] { "_id", "name", "city_num" },
                            "province_id = ?", new String[] { String.valueOf(provinceId - 1) }));
        }

        mAdapter.notifyDataSetChanged();
    }
}
