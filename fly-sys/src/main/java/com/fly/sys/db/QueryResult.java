package com.fly.sys.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class QueryResult<T> implements Serializable {
    private int total;
    private List<T> rows = new ArrayList<T>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
