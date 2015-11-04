package com.mocktpo.util;

import com.mocktpo.model.MTest;
import com.mocktpo.model.MockTPO;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

public class XMLUtils {

    private static String LOCAL_FILE = GlobalConstants.APPLICATION_DIR + GlobalConstants.MOCKTPO_CONF_FILE;

    private static final Logger logger = LogManager.getLogger();

    public static MockTPO load() {
        XStream xs = new XStream();
        try {
            xs.alias("mocktpo", MockTPO.class);
            xs.alias("test", MTest.class);
            URL xml = XMLUtils.class.getResource(LOCAL_FILE);
            return (MockTPO) xs.fromXML(new File(xml.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(MockTPO mockTPO) {
        XStream xs = new XStream();
        OutputStream os = null;
        try {
            xs.alias("mocktpo", MockTPO.class);
            xs.alias("test", MTest.class);
            URL xml = XMLUtils.class.getResource(LOCAL_FILE);
            File file = new File(xml.toURI());
            if (!file.exists()) {
                if (file.createNewFile()) {
                    logger.debug("File 'mocktpo.xml' saved successfully.");
                }
            }
            os = new FileOutputStream(file);
            xs.toXML(mockTPO, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
}
