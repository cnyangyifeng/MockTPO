package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.MTest;
import com.mocktpo.model.MockTPO;
import com.mocktpo.ui.dialogs.ContactUsDialog;
import com.mocktpo.ui.widgets.*;
import com.mocktpo.util.*;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipInputStream;

public class PracticesHomeFrame extends JFrame implements ActionListener, MouseListener {

    /* Constants */

    public static final int GO_BACK_HOME_BUTTON_WIDTH = 84;
    public static final int GO_BACK_HOME_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int BODY_SCROLL_PANE_WIDTH = 1000;

    public static final String DOWNLOAD_THREAD_PREFIX = GlobalConstants.APPLICATION_NAME + "_T_";

    public static final String TEST_LABEL = "Test";
    public static final String READY_LABEL = "Ready";
    public static final String ERROR_LABEL = "Error";

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Frames */

    protected MainFrame mainFrame;
    protected TestFrame testFrame;

    /* Components */

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected StyledLabelPane sloganPane;
    protected MTable bodyTable;
    protected FooterPanel footerPanel;

    /* Variables */

    protected MockTPO mockTPO;
    protected volatile boolean[] markers; // Download markers
    protected volatile boolean redownload;
    private String title;

    /**************************************************
     * Constructors
     **************************************************/

    public PracticesHomeFrame(GraphicsConfiguration gc, MainFrame mainFrame, String title) {
        super(gc);
        this.mainFrame = mainFrame;
        this.title = title;
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Global settings */
        this.globalSettings();
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
        /* Set bounds */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);
        /* Set maximized, unresizable and undecorated */
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);
        /* Set title */
        this.setTitle(this.title);
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    protected void setHeaderPanel() {
        /* Initialize component */
        this.headerPanel = new HeaderPanel();
        /* Set bounds */
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        /* Set layouts */
        this.headerPanel.setLayout(null);
        /* Set children components */
        this.setTitlePane();
        this.setGoBackHomeButton();
        /* Add to the parent component */
        this.getContentPane().add(this.headerPanel);
    }

    protected void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: " + FontsConstants.LOGO_FONT + "; font-size: 20px; font-weight: bold; color: #ffffff; }";
        String html = "<div class='title'>" + GlobalConstants.APPLICATION_NAME + " " + GlobalConstants.PRACTICES_HOME_TITLE + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    protected void setGoBackHomeButton() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - GO_BACK_HOME_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home_hi.png"));
        ImageButton goBackHomeButton = new ImageButton(x, y, GO_BACK_HOME_BUTTON_WIDTH, GO_BACK_HOME_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        goBackHomeButton.setActionCommand("doGoBackHome");
        goBackHomeButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(goBackHomeButton);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    protected void setBodyPanel() {
        /* Initialize component */
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);
        /* Set children components */
        this.setSloganPane();
        this.setBodyScrollPane();
        /* Add to the parent component */
        this.getContentPane().add(this.bodyPanel);
    }

    protected void setSloganPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        String css = ".slogan { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 24px; text-align: center; }";
        String html = "<div class='slogan'>TOEFL&reg; iBT PRACTICES</div>";
        this.sloganPane = new StyledLabelPane(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT, css, html);
        /* Add hyperlink listener */
        this.sloganPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    logger.info("'Contact Us' link clicked.");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            ContactUsDialog contactUs = new ContactUsDialog(PracticesHomeFrame.this, "", true);
                            contactUs.setVisible(true);
                        }
                    });
                }
            }
        });
        /* Add to the parent component */
        this.bodyPanel.add(this.sloganPane);
    }

    protected void setBodyScrollPane() {
        String[] columnNames = {"Number" /* Index */, "Description" /* Name */, "Download", "Test" /* Next */, "Reports"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnNames);
        this.mockTPO = XMLUtils.load();
        if (this.mockTPO == null) {
            logger.error("Configuration file 'mocktpo.xml' not found.");
            System.exit(-1);
        }
        List<MTest> tests = this.mockTPO.getTests();
        for (MTest test : tests) {
            Vector<String> v = new Vector<String>();
            v.add(0, test.getIndex()); // "TPO"
            v.add(1, test.getName()); // "Description"
            v.add(2, test.getDownload());
            v.add(3, test.getNext()); // "Test"
            v.add(4, test.getReports());
            tableModel.addRow(v);
        }
        this.markers = new boolean[tests.size()];

        this.bodyTable = new MTable(tableModel);

        this.bodyTable.addMouseListener(this);

        JScrollPane bodyScrollPane = new JScrollPane();
        int x = this.sloganPane.getX();
        int y = this.sloganPane.getY() + this.sloganPane.getHeight() + LayoutConstants.MARGIN * 5;
        int height = this.bodyPanel.getHeight() - y - LayoutConstants.MARGIN * 10;
        bodyScrollPane.setBounds(x, y, BODY_SCROLL_PANE_WIDTH, height);

        bodyScrollPane.setViewportView(this.bodyTable);

        this.bodyPanel.add(bodyScrollPane);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    protected void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
        /* Set layout */
        this.footerPanel.setLayout(null);
        /* Set children components */
        this.setCopyrightPane();
        /* Add to the parent component */
        this.getContentPane().add(this.footerPanel);
    }

    protected void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>";
        StyledLabelPane copyrightPane = new StyledLabelPane(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.footerPanel.add(copyrightPane);
    }

    /**************************************************
     * Listener Implementations
     **************************************************/

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("doGoBackHome".equals(e.getActionCommand())) {
            logger.info("'Go Back Home' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    MainFrame mainFrame = PracticesHomeFrame.this.getMainFrame();

                    PracticesHomeFrame.this.dispose();

                    if (mainFrame == null) {
                        mainFrame = new MainFrame(device.getDefaultConfiguration());
                    }
                    device.setFullScreenWindow(mainFrame);
                    mainFrame.setVisible(true);
                }
            });
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
                            MTest test = mockTPO.getTests().get(selectedRow);
                            test.setDownload(READY_LABEL);
                            test.setNext(TEST_LABEL);
                            XMLUtils.save(mockTPO);
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
        String testIndex = this.bodyTable.getValueAt(selectedRow, 0).toString();
        String testDescription = this.bodyTable.getValueAt(selectedRow, 1).toString();
        MApplication.settings.put("testIndex", testIndex);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                testFrame = new TestFrame(device.getDefaultConfiguration(), PracticesHomeFrame.this, testDescription);
//                device.setFullScreenWindow(testFrame);
//                testFrame.setVisible(true);
//                setVisible(false);
//            }
//        });
    }

    public void doReports(int selectedRow) {
        logger.info("Reports in row {}.", selectedRow);
    }

    /**************************************************
     * Getters and setters
     **************************************************/

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
