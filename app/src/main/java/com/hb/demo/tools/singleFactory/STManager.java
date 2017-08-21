package com.hb.demo.tools.singleFactory;

/**
 * Created by hanbin on 2017/8/21.
 */

public class STManager {
    public static <T> T getSingle(Class<T> serviceName) {
        return SingleFactory.getInstance().getService(serviceName);
    }
}
