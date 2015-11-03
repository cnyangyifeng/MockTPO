package com.mocktpo.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.InputStream;

public class FTPUtils {

    private static FTPClient ftp;

    public static InputStream download(String remoteFile) {
        connect();
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            return ftp.retrieveFileStream(remoteFile);
        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        }
        return null;
    }

    public static long getFileSize(String remoteFile) {
        connect();
        long fileSize = 0;
        try {
            FTPFile[] arr = ftp.listFiles(remoteFile);
            if (arr != null && arr.length > 0) {
                FTPFile file = arr[0];
                if (file != null) {
                    fileSize = file.getSize();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return fileSize;
    }

    public static boolean connect() {
        try {
            ftp = new FTPClient();
            ftp.connect(GlobalConstants.FTP_HOST);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            return ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        }
        return false;
    }

    public static void disconnect() {
        try {
            if (ftp != null) {
                ftp.disconnect();
                ftp = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
