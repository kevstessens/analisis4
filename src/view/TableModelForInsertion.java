package view;

import javax.swing.table.AbstractTableModel;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 12:15 PM
 */
class TableModelForInsertion extends AbstractTableModel {
    final static String[] COLUMN_NAMES = {"X", "Y"};

    private Object[][] data;

    TableModelForInsertion(Object[][] data) {
        this.data = data;
    }

    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public Object[][] getData() {
        return data;
    }

    public TableModelForInsertion increaseRow(int i) {
        Object[][] auxData = new Object[getRowCount() + i][getColumnCount()];
        for (int j = 0; j < getColumnCount(); j++) {
            for (int k = 0; k < getRowCount(); k++) {
                auxData[k][j] = data[k][j];
            }
        }
        for (int j = 0; j < getColumnCount(); j++) {
            for (int k = getRowCount(); k < getRowCount() + i; k++) {
                auxData[k][j] = 0.0;
            }
        }

        return new TableModelForInsertion(auxData);
    }
}
