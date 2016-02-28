package com.mocktpo.ui.widgets;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class MReadingReviewTable extends JTable {

    /* Constants */

    public static final int TABLE_WIDTH = 1000;
    public static final int TABLE_HEADER_HEIGHT = 24;
    public static final int TABLE_CELL_HEIGHT = 24;

    /**************************************************
     * Constructors
     **************************************************/

    public MReadingReviewTable(TableModel tableModel) {
        super(tableModel);
        initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    protected void initComponents() {
        /* Set table header */
        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEADER_HEIGHT));
        tableHeader.setFont(new Font("Roboto", Font.BOLD, 14));
        tableHeader.setForeground(new Color(51, 51, 51));
        tableHeader.setBackground(new Color(191, 136, 107));
        /* Set table layout */
        this.setRowHeight(TABLE_CELL_HEIGHT);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel columnModel = this.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(800);
        columnModel.getColumn(2).setPreferredWidth(180);
        /* Set table styles */
        this.setFont(new Font("Roboto", Font.PLAIN, 14));
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(51, 51, 51));
        this.setGridColor(new Color(245, 245, 245)); // #f5f5f5
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setRowSelectionAllowed(true);
    }
}
