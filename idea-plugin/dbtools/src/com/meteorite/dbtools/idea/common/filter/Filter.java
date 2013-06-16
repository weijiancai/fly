package com.meteorite.dbtools.idea.common.filter;

import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public abstract class Filter<T> {
    public static final Filter NO_FILTER = new Filter() {
        @Override
        public boolean accepts(Object object) {
            return true;
        }

        @Override
        public boolean acceptsAll(List objects) {
            return true;
        }
    };

    public abstract boolean accepts(T object);

    public boolean acceptsAll(List<T> objects) {
        for (T object : objects) {
            if (!accepts(object)) return false;
        }
        return true;
    }
}
