package com.fly.fxsys.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class BaseService<T> extends Service<T> implements IService<T> {
    @Override
    protected Task<T> createTask() {

        return null;
    }
}
