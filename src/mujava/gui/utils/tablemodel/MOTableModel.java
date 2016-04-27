/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.gui.utils.tablemodel;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import mujava.MutationSystem;

/**
 * <p>
 * Description: Template containing method-level mutation operators and
 * class-level mutation operators</p>
 * <p>
 * Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED </p>
 *
 * @author Yu-Seung Ma
 * @version 1.0
 */
public abstract class MOTableModel extends AbstractTableModel {

    String[] columnHeader = new String[]{"", "Operator"};
    String[] op;
    Object[][] data;

    static final int CMO = 0;
    static final int TMO = 1;

    abstract int getOperatorType();

    public MOTableModel() {
        if (getOperatorType() == CMO) {
            op = MutationSystem.cm_operators;
        } else {
            op = MutationSystem.tm_operators;
        }

        data = new Object[op.length][2];
        for (int i = 0; i < op.length; i++) {
            data[i][0] = new Boolean(false);
            data[i][1] = op[i];
        }
    }

    public void setAllSelectValue(boolean b) {
        for (int i = 0; i < data.length; i++) {
            data[i][0] = new Boolean(b);
        }
    }

    public String getColumnName(int col) {
        return columnHeader[col];
    }

    public int getColumnCount() {
        return columnHeader.length;
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public int getRowCount() {
        return data.length;
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setValueAt(Object value, int row, int col) {
        if (data[0][col] instanceof Integer
                && !(value instanceof Integer)) {
            try {
                data[row][col] = new Integer(value.toString());
                fireTableCellUpdated(row, col);
            } catch (NumberFormatException e) {    // do nothing?
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

    public String[] getSelectedOprators() {
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
}
