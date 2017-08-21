package com.hb.demo.utils.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.hb.demo.model.db.place.City;
import com.hb.demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 2017/7/14.
 */

public class DBManager {
    private String DB_NAME = "place.db";
    private Context mContext;
    public List<City> mProvinceList = new ArrayList<>();
    public List<City> mCityList = new ArrayList<>();

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 把raw目录下的db文件复制到dbpath下
     *
     * @return
     */
    public SQLiteDatabase DBManager() {
        String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/"
                + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                boolean flag = new File(
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/")
                        .mkdirs();
                boolean newFile = new File(dbPath).createNewFile();
                try {
                    FileOutputStream out = new FileOutputStream(dbPath);
                    InputStream in = mContext.getResources().openRawResource(R.raw.db_weather);
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1)
                        out.write(buffer, 0, readBytes);
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }

    /**
     * 查询省
     *
     * @param sqliteDB
     * @param columns
     * @param selection
     * @param selectionArgs
     * @return
     */
    public List<City> queryProvinces(SQLiteDatabase sqliteDB, String[] columns,
                                     String selection, String[] selectionArgs) {
        City bean = null;
        try {
            String table = "provinces";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null,
                    null);
            mProvinceList.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                bean = new City();
                bean.setId(id);
                bean.setName(name);
                mProvinceList.add(bean);
            }
            cursor.close();
        } catch (Exception e) {

        }
        return mProvinceList;
    }

    /**
     * 通过省获取市
     *
     * @param sqliteDB
     * @param columns
     * @param selection
     * @param selectionArgs
     * @return
     */
    public List<City> queryCitysByProvinceId(SQLiteDatabase sqliteDB, String[] columns,
                                             String selection, String[] selectionArgs) {
        City bean = null;
        try {
            String table = "citys";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null,
                    null);
            mCityList.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String city_num = cursor.getString(cursor.getColumnIndex("city_num"));
                bean = new City();
                bean.setId(id);
                bean.setName(name);
                bean.setCityNum(city_num);
                mCityList.add(bean);
            }
        } catch (Exception e) {

        }
        return mCityList;
    }
}
