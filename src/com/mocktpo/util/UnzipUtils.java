package com.mocktpo.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtils {

    public static boolean unzip(String zipFile, String localPath) {
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                File file = new File(localPath, entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    FileOutputStream fos = new FileOutputStream(file);
                    IOUtils.copy(zis, fos);
                    IOUtils.closeQuietly(fos);
                }
                zis.closeEntry();
                entry = zis.getNextEntry();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String zipFile = FTPUtils.class.getResource(GlobalConstants.TESTS_DIR).getPath() + "TPO25.zip";
        String localPath = FTPUtils.class.getResource(GlobalConstants.TESTS_DIR).getPath();
        try {
            UnzipUtils.unzip(zipFile, localPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
