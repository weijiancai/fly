package com.fly.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务管理器
 *
 * @author wei_jc
 * @version 0.0.1
 */
public class ServiceManager {
    private static ServiceManager instance;
    private static Map<Class<?>, List<IService>> serviceMap = new HashMap<Class<?>, List<IService>>();

    private ServiceManager() {}

    /**
     * 获取服务管理器实例
     *
     * @return 返回服务管理器实例
     */
    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }

        return instance;
    }

    /**
     * 注册服务
     *
     * @param service 服务
     * @return 返回服务管理器
     */
    public ServiceManager registerService(IService service) {
        Class<?> clazz = service.getClass();
        List<IService> list = serviceMap.get(clazz);
        if (list == null) {
            list = new ArrayList<IService>();
        }
        list.add(service);

        return this;
    }

    /**
     * 初始化
     */
    public void init() {

    }
}
