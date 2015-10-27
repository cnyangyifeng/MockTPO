package com.mocktpo.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FTPUtils {

    public static void download(String remoteFile, String localFile) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect(GlobalConstants.FTP_HOST);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
        }
        ftp.login(GlobalConstants.FTP_USERNAME, GlobalConstants.FTP_PASSWORD);

        File file = new File(localFile);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.retrieveFile(remoteFile, os);
            ftp.logout();
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public static void main(String[] args) {
        String remoteFile = "/MockTPO/tests/TPO25.zip";
        String localFile = FTPUtils.class.getResource(GlobalConstants.TESTS_DIR).getPath() + "TPO25.zip";
        try {
            FTPUtils.download(remoteFile, localFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
