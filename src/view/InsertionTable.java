package view;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 12:28 PM
 */
public class InsertionTable extends JTable {


    public InsertionTable() {
        super(new TableModelForInsertion(new Object[][]{{0.0, 0.0}}));

    }

    public Object[][] getData() {
        TableModel tableModel = getModel();
        return ((TableModelForInsertion) tableModel).getData();

    }


    public JTable getJTable() {
        return this;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        super.tableChanged(e);
        if (e.getType() == TableModelEvent.UPDATE) {
            if (e.getLastRow() >= getModel().getRowCount() - 1) {
                setModel(((TableModelForInsertion) getModel()).increaseRow(1));
            }
        }
    }

    public void resetValues() {
        setModel(new TableModelForInsertion(new Object[][]{{0,0}}));
    }
}