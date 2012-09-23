package com.fly.sys.db.util;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public abstract class ExceptionWrapper<T> {
    public abstract T wrapper() throws Exception;

    public T process() {
        try {
            return wrapper();
        } catch (AbstractMethodError e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
