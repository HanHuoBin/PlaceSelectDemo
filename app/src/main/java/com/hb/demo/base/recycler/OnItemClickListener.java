package com.hb.demo.base.recycler;

import android.view.View;

/**
 * Created by hb on 15/12/10.
 */
public interface OnItemClickListener<T> {
    void onClick(View view, T item);
}
