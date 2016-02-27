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
import java.util.zip.ZipInputStream;

public class TestHomeFrame extends JFrame implements ActionListener, MouseListener {

    // Constants

    public static final int GO_BACK_HOME_BUTTON_WIDTH = 84;
    public static final int GO_BACK_HOME_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int BODY_SCROLL_PANE_WIDTH = 1000;

    public static final String DOWNLOAD_THREAD_PREFIX = GlobalConstants.APPLICATION_NAME + "_T_";

    public static final String TEST_LABEL = "Test";
    public static final String READY_LABEL = "Ready";
    public static final String ERROR_LABEL = "Error";

    // Logger

    protected static final Logger logger = LogManager.getLogger();

    // Frames

    protected MainFrame mainFrame;
    protected TestFrame testFrame;

    // Components

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected JEditorPane sloganPane;
    protected MTable bodyTable;
    protected FooterPanel footerPanel;

    // Variables
    protected MockTPO mockTPO;
    protected volatile boolean[] markers; // Download markers
    protected volatile boolean redownload;
    private String title;

    /**************************************************
     * Constructors
     **************************************************/

    public TestHomeFrame(GraphicsConfiguration gc, MainFrame mainFrame, String title) {
        super(gc);
        this.mainFrame = mainFrame;
        this.title = title;
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        this.globalSettings();
        this.setLayout(null);

        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);

        this.setTitle(this.title);
    }

    /**************************************************
     * Set Header Panel
     **************************************************/

    protected void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();

        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);

        this.headerPanel.setLayout(null);

        this.setLogoLabel();
        this.setTitlePane();
        this.setGoBackHomeButton();

        this.getContentPane().add(this.headerPanel);
    }

    protected void setLogoLabel() {
        JLabel logoLabel = new JLabel();

        logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "logo.png"));
        logoLabel.setIcon(icon);

        this.headerPanel.add(logoLabel);
    }

    protected void setTitlePane() {
        JEditorPane titlePane = new JEditorPane();

        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        titlePane.setBounds(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT);

        titlePane.setEditable(false);
        titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Impact; font-size: 24px; font-weight: bold; color: #ffffff; }");
        titlePane.setEditorKit(kit);
        titlePane.setText("<div class='title'>MODEL TESTS</div>");

        this.headerPanel.add(titlePane);
    }

    protected void setGoBackHomeButton() {
        MButton goBackHomeButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - GO_BACK_HOME_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        goBackHomeButton.setBounds(x, y, GO_BACK_HOME_BUTTON_WIDTH, GO_BACK_HOME_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home.png"));
        goBackHomeButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home_hi.png"));
        goBackHomeButton.setRolloverIcon(rolloverIcon);
        goBackHomeButton.setText(null);
        goBackHomeButton.setMargin(new Insets(0, 0, 0, 0));
        goBackHomeButton.setBorder(null);
        goBackHomeButton.setBorderPainted(false);
        goBackHomeButton.setFocusPainted(false);
        goBackHomeButton.setContentAreaFilled(false);

        goBackHomeButton.setActionCommand("doGoBackHome");
        goBackHomeButton.addActionListener(this);

        this.headerPanel.add(goBackHomeButton);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    protected void setBodyPanel() {
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);

        this.setSloganPane();
        this.setBodyScrollPane();

        this.getContentPane().add(this.bodyPanel);
    }

    protected void setSloganPane() {
        this.sloganPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        this.sloganPane.setBounds(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT);

        this.sloganPane.setEditable(false);
        this.sloganPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".slogan { color: #333333; font-family: Roboto; font-size: 24px; text-align: center; }");
        this.sloganPane.setEditorKit(kit);
        this.sloganPane.setText("<div class='slogan'>TOEFL&reg; iBT MODEL TESTS</div>");
        this.sloganPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    logger.info("'Contact Us' link clicked.");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            ContactUsDialog contactUs = new ContactUsDialog(TestHomeFrame.this, "", true);
                            contactUs.setVisible(true);
                        }
                    });
                }
            }
        });

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
        this.footerPanel = new FooterPanel();

        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.getContentPane().add(this.footerPanel);
    }

    protected void setCopyrightPane() {
        JEditorPane copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        copyrightPane.setBounds(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT);

        copyrightPane.setEditable(false);
        copyrightPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".copyright { color: #ffffff; font-family: Roboto; font-size: 8px; font-weight: bold; text-align: center; }");
        copyrightPane.setEditorKit(kit);
        copyrightPane.setText("<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>");

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
                    MainFrame mainFrame = TestHomeFrame.this.getMainFrame();

                    TestHomeFrame.this.dispose();

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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                testFrame = new TestFrame(device.getDefaultConfiguration(), TestHomeFrame.this, testDescription);
                device.setFullScreenWindow(testFrame);
                testFrame.setVisible(true);
                setVisible(false);
            }
        });
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
