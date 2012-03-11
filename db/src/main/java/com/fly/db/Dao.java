package com.fly.db;

/**
 * @author weijiancai
 */
public interface Dao {
    <T> void save(T t);
    
    <T> void update(T t);
    
    <T> void delete(T t);
    
    <T> void query(T t);
}
