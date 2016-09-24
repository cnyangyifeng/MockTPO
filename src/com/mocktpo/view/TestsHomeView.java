package com.mocktpo.view;

import com.mocktpo.config.TestStatus;
import com.mocktpo.config.TestsHomeConfig;
import com.mocktpo.listener.ExitApplicationListener;
import com.mocktpo.listener.ToIndexViewListener;
import com.mocktpo.util.*;
import com.mocktpo.widget.button.ImageButton;
import com.mocktpo.widget.panel.BodyPanel;
import com.mocktpo.widget.panel.FooterPanel;
import com.mocktpo.widget.panel.HeaderPanel;
import com.mocktpo.widget.panel.StyledLabelPane;
import com.mocktpo.widget.table.MTable;
import com.mocktpo.window.AppWindow;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Vector;
import java.util.zip.ZipInputStream;

public class TestsHomeView extends MyView implements MouseListener {

    /* Constants */

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int GO_BACK_HOME_BUTTON_WIDTH = 84;
    public static final int GO_BACK_HOME_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int BODY_SCROLL_PANE_WIDTH = 1000;

    public static final String DOWNLOAD_THREAD_PREFIX = GlobalConstants.APPLICATION_NAME + "_T_";

    public static final String TEST_LABEL = "Test";
    public static final String READY_LABEL = "Ready";
    public static final String ERROR_LABEL = "Error";

    /* Components */

    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    private ImageButton exitApplicationButton;
    private ImageButton goBackHomeButton;
    private JEditorPane sloganPane;
    private MTable bodyTable;

    /* Variables */

    private TestsHomeConfig testsHomeConfig;
    private volatile boolean[] markers; // Download markers
    private volatile boolean redownload;

    /**************************************************
     * Constructors
     **************************************************/

    public TestsHomeView(AppWindow owner) {
        super(owner);
        this.configData();
        this.initComponents();
    }

    /**************************************************
     * Configure Data
     **************************************************/

    private void configData() {
        TestsHomeConfig thc = ConfigUtils.load("tests_home_config.json", TestsHomeConfig.class);
        this.setTestsHomeConfig(thc);
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    private void setHeaderPanel() {
        /* Initialize component */
        this.headerPanel = new HeaderPanel();
        /* Set bounds */
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        /* Set layout */
        this.headerPanel.setLayout(null);
        /* Set children components */
        this.setTitlePane();
        this.setExitApplicationButton();
        this.setGoBackHomeButton();
        /* Add to the parent component */
        this.add(this.headerPanel);
    }

    private void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: " + FontsConstants.LOGO_FONT + "; font-size: 20px; font-weight: bold; color: #ffffff; }";
        String html = "<div class='title'>" + GlobalConstants.TESTS_HOME_TITLE + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    private void setExitApplicationButton() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - EXIT_APPLICATION_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application_hi.png"));
        this.exitApplicationButton = new ImageButton(x, y, EXIT_APPLICATION_BUTTON_WIDTH, EXIT_APPLICATION_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.exitApplicationButton.addActionListener(new ExitApplicationListener(this.getAppWindow()));
        /* Add to the parent component */
        this.headerPanel.add(this.exitApplicationButton);
    }

    private void setGoBackHomeButton() {
        /* Initialize component */
        int x = this.exitApplicationButton.getX() + EXIT_APPLICATION_BUTTON_WIDTH + LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - GO_BACK_HOME_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home_hi.png"));
        this.goBackHomeButton = new ImageButton(x, y, GO_BACK_HOME_BUTTON_WIDTH, GO_BACK_HOME_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.goBackHomeButton.addActionListener(new ToIndexViewListener(this.getAppWindow()));
        /* Add to the parent component */
        this.headerPanel.add(this.goBackHomeButton);
    }

    /**************************************************
     * Body Panel Settings
     **************************************************/

    private void setBodyPanel() {
        /* Initialize component */
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);
        /* Set children components */
        this.setSloganPane();
        this.setBodyScrollPane();
        /* Add to the parent component */
        this.add(this.bodyPanel);
    }

    private void setSloganPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        String css = ".slogan { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 24px; text-align: center; }";
        String html = "<div class='slogan'>" + GlobalConstants.TESTS_HOME_TITLE + "</div>";
        this.sloganPane = new StyledLabelPane(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.bodyPanel.add(this.sloganPane);
    }

    private void setBodyScrollPane() {
        /* Initialize table model schema */
        String[] columnNames = {"Number" /* Index */, "Description" /* Name */, "Download", "Test" /* Next */, "Reports"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnNames);
        /* Initialize table model data */
        java.util.List<TestStatus> tests = this.getTestsHomeConfig().getTests();
        for (TestStatus test : tests) {
            Vector<String> v = new Vector<String>();
            v.add(0, test.getId()); // "Number"
            v.add(1, test.getName()); // "Description"
            v.add(2, test.getDownload());
            v.add(3, test.getNext()); // "Test"
            v.add(4, test.getReports());
            tableModel.addRow(v);
        }
        /* Initialize download markers */
        this.markers = new boolean[tests.size()];
        /* Initialize table */
        this.bodyTable = new MTable(tableModel);
        /* Add mouse listener to the table */
        this.bodyTable.addMouseListener(this);
        /* Initialize body scroll pane */
        JScrollPane bodyScrollPane = new JScrollPane();
        /* Set bounds of body scroll pane */
        int x = this.sloganPane.getX();
        int y = this.sloganPane.getY() + this.sloganPane.getHeight() + LayoutConstants.MARGIN * 5;
        int height = this.bodyPanel.getHeight() - y - LayoutConstants.MARGIN * 10;
        bodyScrollPane.setBounds(x, y, BODY_SCROLL_PANE_WIDTH, height);
        /* Set viewport view of body scroll pane */
        bodyScrollPane.setViewportView(this.bodyTable);
        /* Add body scroll pane to the parent component */
        this.bodyPanel.add(bodyScrollPane);
    }

    /**************************************************
     * Footer Panel Settings
     **************************************************/

    private void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
        /* Set layout */
        this.footerPanel.setLayout(null);
        /* Set children components */
        this.setCopyrightPane();
        /* Add to the parent component */
        this.add(this.footerPanel);
    }

    private void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>" + GlobalConstants.COPYRIGHT_INFO + "</div>";
        StyledLabelPane copyrightPane = new StyledLabelPane(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.footerPanel.add(copyrightPane);
    }

    /**************************************************
     * Listener Implementations
     **************************************************/

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

    public void doDownload(final int selectedRow, final int selectedColumn) {
        String testIndex = this.bodyTable.getValueAt(selectedRow, 0).toString();
        String remoteFile = GlobalConstants.REMOTE_TESTS_DIR + testIndex + GlobalConstants.POSTFIX_ZIP;
        String localFile = this.getClass().getResource(GlobalConstants.TESTS_DIR).getPath() + testIndex + GlobalConstants.POSTFIX_ZIP;

        // Mark other threads to interrupt if necessary

        if (!markers[selectedRow]) {
            for (int i = 0; i < markers.length; i++) {
                markers[i] = false;
            }
            markers[selectedRow] = true;
        }

        for (Thread t : Thread.getAllStackTraces().keySet()) {
            String var = DOWNLOAD_THREAD_PREFIX + testIndex;
            if (t.getName().equals(var)) {
                this.redownload = true;
                break;
            }
        }

        // Time-consuming task

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        bodyTable.setValueAt("0%", selectedRow, selectedColumn); // "Download" column
                        bodyTable.setValueAt("", selectedRow, selectedColumn + 1); // "Test" column
                    }
                });
                InputStream is = null;
                OutputStream os = null;
                FTPClientWrapper ftp = new FTPClientWrapper();
                try {
                    Thread.sleep(200);
                    is = ftp.download(remoteFile);
                    os = new BufferedOutputStream(new FileOutputStream(new File(localFile)));
                    byte[] bytes = new byte[524288]; // 512k
                    int c;
                    long fileSize = ftp.getFileSize(remoteFile);
                    if (fileSize <= 0) {
                        logger.error("Downloadable file not found.");
                        return;
                    }
                    int step = (int) fileSize / 100;
                    int localSize = 0;
                    while ((c = is.read(bytes)) != -1) {
                        if (!markers[selectedRow]) {
                            logger.info("Previous download thread stopped. New download.");
                            return;
                        }
                        if (redownload) {
                            logger.info("Previous download thread stopped. Restart download.");
                            redownload = false;
                            return;
                        }
                        os.write(bytes, 0, c);
                        localSize += c;
                        int downloadProgress = localSize / step;
                        if (downloadProgress <= 100) {
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    logger.debug("Downloading {}: {}%", testIndex, downloadProgress);
                                    bodyTable.setValueAt(downloadProgress + "%", selectedRow, selectedColumn); // "Download" column
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            bodyTable.setValueAt(ERROR_LABEL, selectedRow, selectedColumn); // "Download" column
                            bodyTable.setValueAt("", selectedRow, selectedColumn + 1); // "Test" column
                        }
                    });
                    return;
                } finally {
                    IOUtils.closeQuietly(os);
                    IOUtils.closeQuietly(is);
                    ftp.disconnect();
                }
                // Unzip
                ZipInputStream zis = null;
                try {
                    zis = new ZipInputStream(new FileInputStream(localFile));
                    String localPath = this.getClass().getResource(GlobalConstants.TESTS_DIR).getPath();
                    UnzipUtils.unzip(zis, localPath);
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            bodyTable.setValueAt(READY_LABEL, selectedRow, selectedColumn); // "Download" column
                            bodyTable.setValueAt(TEST_LABEL, selectedRow, selectedColumn + 1); // "Test" column
                            TestsHomeConfig conf = TestsHomeView.this.getTestsHomeConfig();
                            TestStatus test = conf.getTests().get(selectedRow);
                            test.setDownload(READY_LABEL);
                            test.setNext(TEST_LABEL);
                            // TODO save to tests_home_config.json file
                            // XMLUtils.save(conf);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            bodyTable.setValueAt(ERROR_LABEL, selectedRow, selectedColumn); // "Download" column
                            bodyTable.setValueAt("", selectedRow, selectedColumn + 1); // "Test" column
                        }
                    });
                } finally {
                    IOUtils.closeQuietly(zis);
                }
            }
        });
        thread.setName(DOWNLOAD_THREAD_PREFIX + testIndex);
        thread.start();
    }

    public void doNext(int selectedRow) {
//        String testIndex = this.bodyTable.getValueAt(selectedRow, 0).toString();
//        String testDescription = this.bodyTable.getValueAt(selectedRow, 1).toString();
//        MyApplication.settings.put("testIndex", testIndex);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AppWindow win = TestsHomeView.this.getAppWindow();
                win.setContentPane(win.getTestView());
//                testFrame = new TestFrame(device.getDefaultConfiguration(), TestsHomeFrame.this, testDescription);
            }
        });
    }

    public void doReports(int selectedRow) {
        logger.info("Reports in row {}.", selectedRow);
    }

    /**************************************************
     * Getters and Setters
     **************************************************/

    public TestsHomeConfig getTestsHomeConfig() {
        return testsHomeConfig;
    }

    public void setTestsHomeConfig(TestsHomeConfig testsHomeConfig) {
        this.testsHomeConfig = testsHomeConfig;
    }
}
