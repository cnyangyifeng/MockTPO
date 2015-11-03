package com.mocktpo.ui.widgets;

import com.mocktpo.ui.windows.MainFrame;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class MTable extends JTable {

    public static final int TABLE_WIDTH = 1000;
    public static final int TABLE_HEADER_HEIGHT = 40;
    public static final int TABLE_CELL_HEIGHT = 50;

    public MTable(TableModel tableModel) {
        super(tableModel);
        initComponents();
    }

    private void initComponents() {
        // Set table header

        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEADER_HEIGHT));
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        tableHeader.setForeground(new Color(102, 102, 102)); // #666666

        // Set table layout

        this.setRowHeight(TABLE_CELL_HEIGHT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel columnModel = this.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);

        // Set table styles

        this.setFont(new Font("Georgia", Font.PLAIN, 16));
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(51, 51, 51));
        this.setGridColor(new Color(245, 245, 245)); // #f5f5f5
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setCellSelectionEnabled(true);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (column == 2) { // "Download" Column
            String val = this.getValueAt(row, column).toString();
            if (MainFrame.READY_LABEL.equals(val)) {
                c.setBackground(new Color(255, 255, 255)); // #f5f5f5
                c.setForeground(new Color(153, 153, 153)); // #999999
            } else {
                c.setBackground(new Color(255, 255, 255)); // #f5f5f5
                c.setForeground(new Color(60, 77, 130)); // #3c4d82
            }
        } else if (column == 3) { // "Test" column
            c.setBackground(new Color(255, 255, 255)); // #f5f5f5
            c.setForeground(new Color(60, 77, 130)); // #3c4d82
        } else {
            c.setBackground(new Color(255, 255, 255)); // #f5f5f5
            c.setForeground(new Color(51, 51, 51)); // #333333
        }
        return c;
    }
}
