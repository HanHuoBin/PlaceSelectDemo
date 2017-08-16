package com.hb.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hb.demo.ui.CitySelectActivity;
import com.hb.demo.ui.MainActivity;


/**
 * 负责页面间跳转参数的封装
 */
public class Navigation {

    private Navigation() {

    }

    public static <T> void goPage(Context context, Class<T> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static <T> void goPage4Result(Activity activity, Class<T> cls, int requestCode) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 登录
     *
     * @param context
     */
    public static void login(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        //                Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到城市选择页
     *
     * @param activity
     * @param provinceId
     */
    public static void goPlaceSelectPage(Activity activity, int provinceId,String name) {
        Intent intent = new Intent(activity, CitySelectActivity.class);
        intent.putExtra(CitySelectActivity.EXTRA_ID, provinceId);
        intent.putExtra(CitySelectActivity.EXTRA_NAME, name);
        activity.startActivity(intent);
    }
}
