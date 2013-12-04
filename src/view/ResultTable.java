package view;

import javax.swing.*;

public class ResultTable extends JTable {


    public ResultTable() {
        super(new TableModelForDataView(new Object[][]{}));
    }

    public void refreshTable(Object[][] data) {

        TableModelForDataView tableModel = new TableModelForDataView(data);
        setModel(tableModel);
    }


    public JTable getJTable() {
        return this;
    }
}
