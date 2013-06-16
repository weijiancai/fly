package com.meteorite.dbtools.idea.common.ui.list;

import com.intellij.ui.DottedBorder;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class CheckBoxList<T extends Selectable> extends JList {
    public CheckBoxList(List<T> elements) {
        setCellRenderer(new CellRenderer());
        DefaultListModel model = new DefaultListModel();
        for (T element : elements) {
            model.addElement(new Entry<T>(element));
        }
        setModel(model);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isEnabled() && e.getButton() == MouseEvent.BUTTON1) {
                    int index = locationToIndex(e.getPoint());

                    if (index != -1) {
                        Entry entry = (Entry) getModel().getElementAt(index);
                        if ((e.getClickCount() == 1 && e.getX() < 20) || e.getClickCount() == 2) {
                            entry.switchSelection();
                        }
                    }
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    int[] indices = CheckBoxList.this.getSelectedIndices();
                    for (int index : indices) {
                        if (index >= 0) {
                            Entry entry = (Entry) getModel().getElementAt(index);
                            entry.switchSelection();
                        }
                    }
                }
            }
        });
    }

    public boolean isSelected(T presentable) {
        for (int i=0; i<getModel().getSize(); i++) {
            Entry<T> entry = (Entry<T>) getModel().getElementAt(i);
            if (entry.getPresentable().equals(presentable)) {
                return entry.isSelected();
            }
        }
        return false;
    }

    public void applyChanges(){
        ListModel model = getModel();
        for (int i=0; i<model.getSize(); i++) {
            Entry entry = (Entry) model.getElementAt(i);
            entry.updatePresentable();
        }

    }

    public void addActionListener(ActionListener actionListener) {
        DefaultListModel model = (DefaultListModel) getModel();
        for (Object o : model.toArray()) {
            Entry entry = (Entry) o;
            entry.checkBox.addActionListener(actionListener);
        }
    }

    public void removeActionListener(ActionListener actionListener) {
        DefaultListModel model = (DefaultListModel) getModel();
        for (Object o : model.toArray()) {
            Entry entry = (Entry) o;
            entry.checkBox.removeActionListener(actionListener);
        }
    }

    private class CellRenderer implements ListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Entry entry = (Entry) value;
            Selectable presentable = entry.presentable;
            entry.label.setForeground(presentable.isMasterSelected() ? UIUtil.getListForeground() : Color.GRAY);
            entry.checkBox.setEnabled(presentable.isMasterSelected());

            Color background = list.isEnabled() ? UIUtil.getListBackground() : UIUtil.getComboBoxDisabledBackground();
            //entry.setBackground(background);
            entry.textPanel.setBackground(background);
            entry.checkBox.setBackground(background);
            entry.setBorder(isSelected ? new DottedBorder(Color.BLACK) : new LineBorder(background));
            return entry;
        }
    }

    private class Entry<T extends Selectable> extends JPanel {
        private JPanel textPanel;
        private JCheckBox checkBox;
        private JLabel label;
        private JLabel errorLabel;
        private T presentable;

        private Entry(T presentable) {
            super(new BorderLayout());
            setBackground(UIUtil.getListBackground());
            this.presentable = presentable;
            checkBox = new JCheckBox("", presentable.isSelected());
            checkBox.setBackground(UIUtil.getListBackground());

            label = new JLabel(presentable.getName(), presentable.getIcon(), SwingConstants.LEFT);
            errorLabel = new JLabel();
            errorLabel.setForeground(Color.RED);
            add(checkBox, BorderLayout.WEST);

            textPanel = new JPanel(new BorderLayout(2, 0));
            textPanel.add(label, BorderLayout.WEST);
            textPanel.add(errorLabel, BorderLayout.CENTER);
            textPanel.setBackground(UIUtil.getListBackground());
            add(textPanel, BorderLayout.CENTER);

        }

        private void updatePresentable() {
            presentable.setSelected(checkBox.isSelected());
        }

        public T getPresentable() {
            return presentable;
        }

        public boolean isSelected() {
            return checkBox.isSelected();
        }

        private void switchSelection() {
            checkBox.setSelected(!checkBox.isSelected());
            CheckBoxList.this.repaint();
            for (ActionListener actionListener : checkBox.getActionListeners()) {
                actionListener.actionPerformed(new ActionEvent(checkBox, 0, "selectionChanged"));
            }
        }
    }

}
