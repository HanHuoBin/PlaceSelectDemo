package com.hb.demo.base.recycler;

import android.view.View;

/**
 * Created by hb on 15/12/10.
 */
public interface OnItemLongClickListener<T> {
    void onLongClick(View view, T item);
}
