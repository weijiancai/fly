package com.meteorite.dbtools.idea.data.model.sortable;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.data.model.basic.BasicDataModel;
import com.meteorite.dbtools.idea.data.model.basic.SortableDataModelRow;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class SortableDataModel<T extends SortableDataModelRow> extends BasicDataModel<T> {

    protected SortableDataModel(Project project) {
        super(project);
    }
}
