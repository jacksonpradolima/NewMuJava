/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.gui.utils.tablemodel;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jackson Lima
 */
public class FileTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 106L;

    String[] columnHeader = new String[]{"", "File"};
    String[] op;

    Object[][] data;

    public FileTableModel(Vector value) {
        data = new Object[value.size()][2];
        for (int i = 0; i < value.size(); i++) {
            data[i][0] = new Boolean(false);
            data[i][1] = value.get(i);
        }
    }

    public String getColumnName(int col) {
        return columnHeader[col];
    }

    public int getColumnCount() {
        return columnHeader.length;
    }

    public void setAllSelectValue(boolean b) {
        for (int i = 0; i < data.length; i++) {
            data[i][0] = new Boolean(b);
        }
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public int getRowCount() {
        return data.length;
    }

    public String[] getSelectedFiles() {
        Vector set = new Vector();
        int numRows = getRowCount();
        int i;

        for (i = 0; i < numRows; i++) {
            if (data[i][0].toString().equals("true")) {
                set.add(data[i][1]);
            }
        }

        String[] names = new String[set.size()];
        if (set.size() > 0) {
            for (i = 0; i < set.size(); i++) {
                names[i] = set.get(i).toString();
            }
            return names;
        } else {
            return null;
        }
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setValue(Object[] value) {
        data = new Object[value.length][2];
        for (int i = 0; i < value.length; i++) {
            data[i][0] = new Boolean(true);
            data[i][1] = value[i];
        }
    }

    public void setValueAt(Object value, int row, int col) {
        if (data[0][col] instanceof Integer
                && !(value instanceof Integer)) {
            try {
                data[row][col] = new Integer(value.toString());
                fireTableCellUpdated(row, col);
            } catch (NumberFormatException e) {  // do nothing?
            }
        } else {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears on screen.
        if (col < 1) {
            return true;
        } else {
            return false;
        }
    }
}