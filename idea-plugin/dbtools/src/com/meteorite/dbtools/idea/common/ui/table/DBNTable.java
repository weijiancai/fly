package com.meteorite.dbtools.idea.common.ui.table;

import com.intellij.openapi.project.Project;
import com.intellij.util.ui.UIUtil;
import com.meteorite.dbtools.idea.common.ui.DBNColor;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class DBNTable extends JTable {
    private static final int MAX_COLUMN_WIDTH = 30;
    private static final int MIN_COLUMN_WIDTH = 10;
    public static final DBNColor GRID_COLOR = new DBNColor(Color.LIGHT_GRAY, Color.DARK_GRAY);
    private Project project;

    public DBNTable(Project project, TableModel tableModel, boolean showHeader) {
        super(tableModel);
        this.project = project;
        setGridColor(GRID_COLOR);
        setFont(UIUtil.getLabelFont());
        if (!showHeader) {
            JTableHeader tableHeader = getTableHeader();
            tableHeader.setVisible(false);
            tableHeader.setPreferredSize(new Dimension(-1, 0));
            tableHeader.setFont(UIUtil.getLabelFont());
        }
    }

    public Project getProject() {
        return project;
    }

    public Object getValueAtMouseLocation() {
        Point location = MouseInfo.getPointerInfo().getLocation();
        location.setLocation(location.getX() - getLocationOnScreen().getX(), location.getY() - getLocationOnScreen().getY());
        return getValueAtLocation(location);
    }

    private Object getValueAtLocation(Point point) {
        int columnIndex = columnAtPoint(point);
        int rowIndex = rowAtPoint(point);
        return columnIndex > -1  && rowIndex > -1 ? getModel().getValueAt(rowIndex, columnIndex) : null;
    }

    public void accommodateColumnSize() {
        for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
            accommodateColumnSize(columnIndex, 22);
        }
    }

    private void accommodateColumnSize(int colIndex, int span) {
        TableColumn column = getColumnModel().getColumn(colIndex);
        int columnIndex = column.getModelIndex();
        int preferredWidth = 0;

        // header
        JTableHeader tableHeader = getTableHeader();
        if (tableHeader != null) {
            Object headerValue = column.getHeaderValue();
            TableCellRenderer headerCellRenderer = column.getCellRenderer();
            if (headerCellRenderer == null) {
                headerCellRenderer = tableHeader.getDefaultRenderer();
            }
            Component headerComponent = headerCellRenderer.getTableCellRendererComponent(this, headerValue, false, false, 0, columnIndex);
            if (headerComponent.getPreferredSize().width > preferredWidth) {
                preferredWidth = headerComponent.getPreferredSize().width;
            }
        }

        // rows
        for (int rowIndex = 0; rowIndex < getModel().getRowCount(); rowIndex++) {
            if (preferredWidth > MAX_COLUMN_WIDTH) {
                break;
            }
            Object value = getModel().getValueAt(rowIndex, columnIndex);
            TableCellRenderer renderer = getCellRenderer(rowIndex, columnIndex);

            if (renderer != null) {
                Component component = renderer.getTableCellRendererComponent(this, value, false, false, rowIndex, columnIndex);
                if (component.getPreferredSize().width > preferredWidth) {
                    preferredWidth = component.getPreferredSize().width;
                }
            }
        }

        if (preferredWidth > MAX_COLUMN_WIDTH) {
            preferredWidth = MAX_COLUMN_WIDTH;
        }

        if (preferredWidth < MIN_COLUMN_WIDTH) {
            preferredWidth = MIN_COLUMN_WIDTH;
        }

        preferredWidth = preferredWidth + span;

        if (column.getPreferredWidth() != preferredWidth) {
            column.setPreferredWidth(preferredWidth);
        }
    }

    public void selectCell(int rowIndex, int columnIndex) {
        scrollRectToVisible(getCellRect(rowIndex, columnIndex, true));
        setRowSelectionInterval(rowIndex, rowIndex);
        setColumnSelectionInterval(columnIndex, columnIndex);
    }
}
