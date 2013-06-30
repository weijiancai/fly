package com.fly.base.service;

import com.meteorite.framework.OID;

/**
 * 服务接口
 *
 * @author wei_jc
 * @version 0.0.1
 */
public interface IService<S> extends OID {
    /**
     * 启动服务
     */
    void startService();

    /**
     * 停止服务
     */
    void stopService();

    /**
     * 获取服务
     *
     * @param <T>
     * @return
     */
    <T> T getService();
}
