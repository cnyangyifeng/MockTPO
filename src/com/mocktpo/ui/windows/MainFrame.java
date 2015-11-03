package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.MTest;
import com.mocktpo.model.MockTPO;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.FooterPanel;
import com.mocktpo.ui.widgets.HeaderPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.*;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.List;
import java.util.Vector;

public class MainFrame extends JFrame implements ActionListener, MouseListener {

    // Constants

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int BODY_SCROLL_PANE_WIDTH = 1000;

    public static final String DOWNLOAD_LABEL = "Download";
    public static final String TEST_LABEL = "Test";
    public static final String REPORTS_LABEL = "Reports";
    public static final String READY_LABEL = "Ready";

    // Logger

    private static final Logger logger = LogManager.getLogger();

    // Components

    private TestFrame testFrame;
    private HeaderPanel headerPanel;
    private JLabel logoLabel;
    private JEditorPane titlePane;
    private MButton exitApplicationButton;
    private BodyPanel bodyPanel;
    private JEditorPane sloganPane;

    private JScrollPane bodyScrollPane;
    private JTable bodyTable;

    private FooterPanel footerPanel;
    private JEditorPane copyrightPane;

    // Variables

    private MockTPO mockTPO;

    public MainFrame(GraphicsConfiguration gc) {
        super(gc);
        initComponents();
    }

    private void initComponents() {
        this.globalSettings();
        this.setLayout(null);

        this.setHeaderPanel();
        this.setBodyPanel();
        this.setFooterPanel();
    }

    private void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);

        this.setTitle("MockTPO");
    }

    /**************************************************
     * Set Header Panel
     **************************************************/

    private void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();

        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);

        this.headerPanel.setLayout(null);

        this.setLogoLabel();
        this.setTitlePane();
        this.setExitApplicationButton();

        this.getContentPane().add(this.headerPanel);
    }

    private void setLogoLabel() {
        this.logoLabel = new JLabel();

        this.logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "logo.png"));
        this.logoLabel.setIcon(icon);

        this.headerPanel.add(this.logoLabel);
    }

    private void setTitlePane() {
        this.titlePane = new JEditorPane();

        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        this.titlePane.setBounds(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT);

        this.titlePane.setEditable(false);
        this.titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Arial; font-size: 24px; font-weight: bold; color: #ffffff; }");
        this.titlePane.setEditorKit(kit);
        this.titlePane.setText("<div class='title'>MockTPO</div>");

        this.headerPanel.add(this.titlePane);
    }

    private void setExitApplicationButton() {
        this.exitApplicationButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - EXIT_APPLICATION_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.exitApplicationButton.setBounds(x, y, EXIT_APPLICATION_BUTTON_WIDTH, EXIT_APPLICATION_BUTTON_HEIGHT);
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application.png"));

        this.exitApplicationButton.setIcon(icon);
        this.exitApplicationButton.setText(null);
        this.exitApplicationButton.setMargin(new Insets(0, 0, 0, 0));
        this.exitApplicationButton.setBorder(null);
        this.exitApplicationButton.setBorderPainted(false);
        this.exitApplicationButton.setFocusPainted(false);
        this.exitApplicationButton.setContentAreaFilled(false);

        this.exitApplicationButton.setActionCommand("doExitApplication");
        this.exitApplicationButton.addActionListener(this);

        this.headerPanel.add(this.exitApplicationButton);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    private void setBodyPanel() {
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);

        this.setSloganPane();
        this.setBodyScrollPane();

        this.getContentPane().add(this.bodyPanel);
    }

    private void setSloganPane() {
        this.sloganPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 5;
        this.sloganPane.setBounds(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT);

        this.sloganPane.setEditable(false);
        this.sloganPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".slogan { color: #666666; font-family: Arial; font-size: 24px; font-weight: bold; text-align: center; } .slogan-desc { color: #999999; font-family: Arial; font-size: 16px; font-weight: normal; margin-top: 10px; text-align: center; } span.highlighted { color: #333333; } a.author { color: #333333; } ");
        this.sloganPane.setEditorKit(kit);
        this.sloganPane.setText("<div class='slogan'>MockTPO is a TOEFL Practice Offline Application</div" +
                "<div class='slogan-desc'> only for <span class='highlighted'>noncommercial</span> use. Please contact <a href='' class='author'>us</a> to report bugs and check updates.</div>");

        this.bodyPanel.add(this.sloganPane);
    }

    private void setBodyScrollPane() {
        String[] columnNames = {"TPO" /* Index */, "Description" /* Name */, "Download", "Test" /* Next */, "Reports"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnNames);
        this.mockTPO = XMLUtils.load();
        List<MTest> tests = this.mockTPO.getTests();
        for (MTest test : tests) {
            Vector<String> v = new Vector<String>();
            v.add(0, test.getIndex());
            v.add(1, test.getName());
            v.add(2, test.getDownload());
            v.add(3, test.getNext()); // "Next"
            v.add(4, test.getReports());
            tableModel.addRow(v);
        }

        this.bodyTable = new JTable(tableModel);

        // Set table header

        JTableHeader tableHeader = this.bodyTable.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(BODY_SCROLL_PANE_WIDTH, 40));
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        tableHeader.setForeground(new Color(102, 102, 102)); // #666666

        // Set table layout

        this.bodyTable.setRowHeight(50);
        this.bodyTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel columnModel = this.bodyTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);

        // Set table styles

        this.bodyTable.setFont(new Font("Georgia", Font.PLAIN, 16));
        this.bodyTable.setForeground(new Color(51, 51, 51));
        this.bodyTable.setGridColor(new Color(245,/**/ 245, 245)); // #f5f5f5
        this.bodyTable.setFocusable(false);
        this.bodyTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.bodyTable.setSelectionBackground(new Color(245, 245, 245)); // #f5f5f5
        this.bodyTable.setSelectionForeground(new Color(60, 77, 130)); // #3c4d82

        // Add mouse listener

        this.bodyTable.addMouseListener(this);

        this.bodyScrollPane = new JScrollPane();
        int x = this.sloganPane.getX();
        int y = this.sloganPane.getY() + this.sloganPane.getHeight() + LayoutConstants.MARGIN * 5;
        int height = this.bodyPanel.getHeight() - y - LayoutConstants.MARGIN * 10;
        this.bodyScrollPane.setBounds(x, y, BODY_SCROLL_PANE_WIDTH, height);

        this.bodyScrollPane.setViewportView(this.bodyTable);

        this.bodyPanel.add(this.bodyScrollPane);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    private void setFooterPanel() {
        this.footerPanel = new FooterPanel();

        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.getContentPane().add(this.footerPanel);
    }

    private void setCopyrightPane() {
        this.copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        this.copyrightPane.setBounds(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT);

        this.copyrightPane.setEditable(false);
        this.copyrightPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".copyright { color: #ffffff; font-family: Arial; font-size: 8px; font-weight: bold; text-align: center; }");
        this.copyrightPane.setEditorKit(kit);
        this.copyrightPane.setText("<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>");

        this.footerPanel.add(this.copyrightPane);
    }

    /**************************************************
     * Listeners
     **************************************************/

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("doExitApplication".equals(e.getActionCommand())) {
            logger.info("'Exit Application' button pressed.");
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int column = this.bodyTable.columnAtPoint(e.getPoint());
        int row = this.bodyTable.rowAtPoint(e.getPoint());
        if (column >= 0 && row >= 0) {
            logger.debug("Table cell ({}, {}) clicked.", row, column);
        }

        if (column == 2) {
            // Download
            doDownload(row, column);
        } else if (column == 3) {
            // Next
            String val = this.bodyTable.getValueAt(row, column).toString();
            if (TEST_LABEL.equals(val)) {
                doNext(row);
            }
        } else if (column == 4) {
            // Reports
            doReports(row);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**************************************************
     * Actions
     **************************************************/

    private void doDownload(final int selectedRow, final int selectedColumn) {
        String testIndex = this.bodyTable.getValueAt(selectedRow, 0).toString();
        String remoteFile = GlobalConstants.REMOTE_TESTS_DIR + testIndex + GlobalConstants.POSTFIX_ZIP;
        String localFile = this.getClass().getResource(GlobalConstants.TESTS_DIR).getPath() + testIndex + GlobalConstants.POSTFIX_ZIP;
        this.bodyTable.setValueAt("0%", selectedRow, selectedColumn);

        // Time-consuming task

        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = FTPUtils.download(remoteFile);
                File file = new File(localFile);
                OutputStream os = null;
                try {
                    os = new BufferedOutputStream(new FileOutputStream(file));
                    byte[] bytes = new byte[65536]; // 64k
                    int c;
                    long fileSize = FTPUtils.getFileSize(remoteFile);
                    if (fileSize <= 0) {
                        logger.info("Downloadable file not found.");
                        return;
                    }
                    long step = fileSize / 100;
                    long localSize = 0L;
                    while ((c = is.read(bytes)) != -1) {
                        os.write(bytes, 0, c);
                        localSize += c;
                        int downloadProgress = (int) (localSize / step);
                        logger.info("Downloading {}: {}%", testIndex, downloadProgress);
                        if (downloadProgress <= 100) {
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    bodyTable.setValueAt(downloadProgress + "%", selectedRow, selectedColumn);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(os);
                    IOUtils.closeQuietly(is);
                }

                // Unzip
                String localPath = this.getClass().getResource(GlobalConstants.TESTS_DIR).getPath();
                logger.info(localPath);
                boolean unzipped = UnzipUtils.unzip(localFile, localPath);
                if (unzipped) {
                    // Set test enabled
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            bodyTable.setValueAt(READY_LABEL, selectedRow, selectedColumn);
                            MTest test = mockTPO.getTests().get(selectedRow);
                            test.setDownload(READY_LABEL);
                            test.setNext(TEST_LABEL);
                            XMLUtils.save(mockTPO);
                        }
                    });
                }
            }
        }).start();
    }

    private void doNext(int selectedRow) {
        String testIndex = this.bodyTable.getValueAt(selectedRow, 0).toString();
        MApplication.settings.put("testIndex", testIndex);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                testFrame = new TestFrame(device.getDefaultConfiguration(), MainFrame.this);
                device.setFullScreenWindow(testFrame);
                testFrame.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void doReports(int selectedRow) {
        logger.info("Reports.");
    }
}
