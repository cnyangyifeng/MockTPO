package com.mocktpo.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.InputStream;

public class FTPUtils {

    private static FTPClient ftp;

    public static InputStream download(String remoteFile) throws Exception {
        connect();
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        return ftp.retrieveFileStream(remoteFile);
    }

    public static long getFileSize(String remoteFile) throws Exception {
        connect();
        long fileSize = 0;
        FTPFile[] arr = ftp.listFiles(remoteFile);
        if (arr != null && arr.length > 0) {
            FTPFile file = arr[0];
            if (file != null) {
                fileSize = file.getSize();
            }
        }
        return fileSize;
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

    private static void connect() throws Exception {
        if (ftp == null || ftp.isConnected()) {
            ftp = new FTPClient();
            ftp.connect(GlobalConstants.FTP_HOST);
        }
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
        }
        ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);
    }
}
