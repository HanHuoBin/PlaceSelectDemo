# PlaceSelectDemo
沉浸式状态栏，城市选择，万能适配器，Sqlite操作

## 沉浸式状态栏
    使用toolbar，实现系统的状态栏颜色与actionbar颜色一致（这种实现方式非常简单）

一、去除actionbar
    1) 自定义Theme，需要继承 NoActionBar

    ```
        <style name="AppBaseTheme" parent="Theme.AppCompat.Light.NoActionBar">
            <!-- 状态栏颜色 -->
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        </style>
        <style name="AppTheme" parent="AppBaseTheme"></style>
    ```

    支持4.4及以上，设置状态栏为透明
    values-v19

    ```
        <?xml version="1.0" encoding="utf-8"?>
        <resources>
            <style name="AppTheme" parent="@style/AppBaseTheme">
                <item name="android:windowTranslucentNavigation">true</item>
                <item name="android:windowTranslucentStatus">true</item>
            </style>
        </resources>
    ```

    values-v21
    ```
        <?xml version="1.0" encoding="utf-8"?>
        <resources>
            <style name="AppTheme" parent="@style/AppBaseTheme">
                <!--透明状态栏-->
                <item name="android:windowTranslucentStatus">true</item>
                <!--透明导航栏-->
                <item name="android:windowTranslucentNavigation">true</item>
                <!--使状态栏，导航栏可绘制-->
                <item name="android:windowDrawsSystemBarBackgrounds">true</item>
            </style>
        </resources>
    ```

    2) 引入v7包，创建头布局
        `compile 'com.android.support:appcompat-v7:25.0.0'`

    ```
        <?xml version="1.0" encoding="utf-8"?>
        <android.support.v7.widget.Toolbar xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@drawable/shape_title"
            android:fitsSystemWindows="true"
            android:navigationIcon="@mipmap/action_back"
            app:navigationIcon="@mipmap/action_back">

            <TextView
                android:id="@+id/tv_action_title"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_centerInParent="true"
                android:layout_gravity="center"
                android:singleLine="true"
                android:textColor="@color/white"
                android:textSize="@dimen/action_title" />


        </android.support.v7.widget.Toolbar>
    ```
    注意：
        去掉 android:navigationIcon="@mipmap/action_back"
             app:navigationIcon="@mipmap/action_back"
        可去掉后退按钮
    3) 在activity中设置toolbar
    ```
        public void initToolBar() {
                mToolbar = findView(R.id.toolbar);
                setSupportActionBar(mToolbar);//通过toolbar来替代系统的状态栏
                getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示标题
            }
    ```

    如果需要设置后退按钮
    ```
        public void setTooBarBackBtn() {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IMEUtils.hideIME(BaseAppCatActivity.this);
                    finish();
                }
            });
        }
    ```
