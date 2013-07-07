package com.fly.sys.db.metadata;

import com.fly.sys.db.object.DBSchema;
import com.fly.sys.db.object.DBTable;

import java.util.List;

/**
 * 数据库元数据加载器
 *
 * @author weijiancai
 * @version 1.0.0
 */
public interface DBMetaDataLoader {
    List<DBSchema> loadSchemas();

    List<DBTable> loadTables(String ownerName);
}
