package com.mocktpo;

import com.mocktpo.util.DbUtils;
import com.mocktpo.util.FTPUtils;
import com.mocktpo.util.FontsUtils;
import com.mocktpo.window.AppWindow;
import com.mocktpo.window.SplashWindow;
import org.apache.ibatis.session.SqlSession;

import java.awt.*;

public class MyApplication {

    private SqlSession sqlSession;
    private boolean connected = false;

    public void init() {
        SplashWindow splash = new SplashWindow();
        splash.setVisible(true);

        long t1 = System.currentTimeMillis();
        // TODO
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        splash.update(2);

        long t3 = System.currentTimeMillis();
        int times = 3;
        while (times > 0) {
            System.out.println("ftp connection: " + times);
            if (FTPUtils.connect()) {
                this.setConnected(true);
                break;
            }
            this.sleep(1000);
            times--;
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3);
        splash.update(27);

        long t5 = System.currentTimeMillis();
        DbUtils.init();
        this.setSqlSession(DbUtils.getSqlSession());
        long t6 = System.currentTimeMillis();
        System.out.println(t6 - t5);
        splash.update(67);

        long t7 = System.currentTimeMillis();
        FontsUtils.loadFonts();
        long t8 = System.currentTimeMillis();
        System.out.println(t8 - t7);
        splash.update(97);

        long t9 = System.currentTimeMillis();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        AppWindow win = new AppWindow();
        win.setApplication(this);
        long t10 = System.currentTimeMillis();
        System.out.println(t10 - t9);
        splash.update(100);

        /* Close splash window */
        this.sleep(100);
        splash.close();

        /* Set application window visible */
        this.sleep(100);
        win.setOpacity(0);
        win.setVisible(true);
        device.setFullScreenWindow(win);
    }

    private void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**************************************************
     * Getters and Setters
     **************************************************/

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**************************************************
     * The Application Main Method
     **************************************************/

    public static void main(String[] args) {
        new MyApplication().init();
    }
}
