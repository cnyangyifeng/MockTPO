package com.mocktpo.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class FTPUtils {

    public static boolean connect() {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(GlobalConstants.FTP_HOST);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            return ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ftp.disconnect();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
