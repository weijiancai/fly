package com.meteorite.framework.observer.impl;

import com.meteorite.framework.observer.INotification;

/**
 * 基本通知
 *
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class BaseNotification<B> implements INotification {
    @Override
    public String getDesc() {
        return "B";
    }
}
