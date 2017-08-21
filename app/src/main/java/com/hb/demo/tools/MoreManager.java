package com.hb.demo.tools;

import com.hb.demo.tools.imageLoader.MoreImageLoader;
import com.hb.demo.tools.singleFactory.STManager;

/**
 * Created by hanbin on 2017/8/21.
 */

public class MoreManager {
    public static MoreImageLoader getImageLoader() {
        return STManager.getSingle(MoreImageLoader.class);
    }
}
