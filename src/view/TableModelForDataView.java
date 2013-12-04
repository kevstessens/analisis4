package view;

import javax.swing.table.AbstractTableModel;

/**
 */
class TableModelForDataView extends AbstractTableModel {
    final static String[] COLUMN_NAMES = {"X","Y"};

    private Object[][] data;

    TableModelForDataView(Object[][] data) {
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
        return false;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }


}
