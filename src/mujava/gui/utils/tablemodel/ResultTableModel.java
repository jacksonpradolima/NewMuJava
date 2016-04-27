/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.gui.utils.tablemodel;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jackson Lima
 */
public class ResultTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 109L;

    String[] columnHeader = new String[]{" Operator ", " value "};

    // This part is used during implementation and testing
    Object[][] data = {
        {"  Live Mutants # ", ""},
        {"  Killed Mutants # ", ""},
        {"  Total Mutants # ", ""},
        {"  Mutant Score ", ""}
    };

    /**
     * AbstractTable Implementation �Լ�
     */
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

    /*
   * JTable uses this method to determine the default renderer/
   * editor for each cell.  If we didn't implement this method,
   * then the last column would contain text ("true"/"false"),
   * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
   * Don't need to implement this method unless your table's
   * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }
}
