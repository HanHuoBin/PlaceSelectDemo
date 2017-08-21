package com.hb.demo.tools.singleFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例服务工厂
 * Created by hanbin on 2017/8/21.
 */

public class SingleFactory {

    /**
     * 服务实例容器
     */
    private Map<String, Object> services = new ConcurrentHashMap<>();

    private static SingleFactory instance;

    private SingleFactory() {

    }

    public static SingleFactory getInstance() {
        if (instance == null) {
            synchronized (SingleFactory.class) {
                if (instance == null) {
                    instance = new SingleFactory();
                }
            }
        }
        return instance;
    }

    public <T> T getService(Class<T> serviceName) {
        T result = null;
        String className = serviceName.getName();
        Object classInstance = services.get(className);

        if (classInstance != null) {
            result = serviceName.cast(classInstance);
        } else {
            try {
                classInstance = serviceName.getConstructor().newInstance();
                services.put(className, classInstance);
                result = serviceName.cast(classInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
