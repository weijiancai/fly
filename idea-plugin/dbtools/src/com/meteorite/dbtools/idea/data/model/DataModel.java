package com.meteorite.dbtools.idea.data.model;

import com.intellij.openapi.Disposable;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface DataModel<T extends DataModelRow> extends TableModel, ListModel, Disposable {
}
