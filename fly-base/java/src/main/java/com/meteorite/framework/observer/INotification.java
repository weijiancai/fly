package com.meteorite.framework.observer;

import com.meteorite.framework.OID;

/**
 * 通知<br/>
 * 1. 通知实例的Id，由系统按一定的规则生成<br/>
 *
 * @author wei_jc
 * @version 0.0.1
 */
public interface INotification<B> extends OID {
    /**
     * 设置通知的内容
     *
     * @param body 通知内容
     */
    void setBody(B body);

    /**
     * 获取通知内容
     *
     * @return 返回通知内容
     */
    B getBody();

    /**
     * 设置通知的类型
     *
     * @param type 通知类型
     */
    void setType(String type);

    /**
     * 获取通知的类型
     *
     * @return 返回通知的类型
     */
    String getType();
}
