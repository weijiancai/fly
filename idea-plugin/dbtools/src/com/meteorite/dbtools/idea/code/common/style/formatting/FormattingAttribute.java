package com.meteorite.dbtools.idea.code.common.style.formatting;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface FormattingAttribute<T> {
    T getValue();

    static abstract class Loader<T> {
        abstract T load();
    }
}
