package com.mocktpo.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FTPUtils {

    public static void download(String remoteFile, String localFile) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(GlobalConstants.FTP_HOST);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);
            File file = new File(localFile);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.retrieveFile(remoteFile, os);
            IOUtils.closeQuietly(os);
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InputStream download(String remoteFile) {
        FTPClient ftp = new FTPClient();
        InputStream is = null;
        try {
            ftp.connect(GlobalConstants.FTP_HOST);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            is = ftp.retrieveFileStream(remoteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public static long getFileSize(String remoteFile) {
        FTPClient ftp = new FTPClient();
        long fileSize = 0;
        try {
            ftp.connect(GlobalConstants.FTP_HOST);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);
            FTPFile[] arr = ftp.listFiles(remoteFile);
            if (arr != null && arr.length > 0) {
                FTPFile file = arr[0];
                if (file != null) {
                    fileSize = file.getSize();
                }
            }
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileSize;
    }

    public static void main(String[] args) {
        String remoteFile = "/MockTPO/tests/TPO25.zip";
        String localFile = FTPUtils.class.getResource(GlobalConstants.TESTS_DIR).getPath() + "TPO25.zip";
        FTPUtils.download(remoteFile, localFile);
    }
}
