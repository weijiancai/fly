package com.meteorite.dbtools.idea.data.ui.table.sortable;

import com.intellij.openapi.diagnostic.Logger;
import com.meteorite.dbtools.idea.common.LogFactory;
import com.meteorite.dbtools.idea.common.ui.table.basic.BasicTable;
import com.meteorite.dbtools.idea.data.model.sortable.SortableDataModel;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class SortableTable extends BasicTable {
    protected Logger logger = LogFactory.createLogger();

    public SortableTable(SortableDataModel dataModel, boolean enableSpeedSearch) {
        super(dataModel.getProject(), dataModel);
        this.logger = logger;
    }
}
