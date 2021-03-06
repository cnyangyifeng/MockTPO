package com.mocktpo.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class ConfigUtils {

    private static final Logger logger = LogManager.getLogger();

    public static <T> T load(String fileName, Class<T> clazz) {
        try {
            URL url = ConfigUtils.class.getResource(GlobalConstants.CONFIG_DIR + fileName);
            String json = FileUtils.readFileToString(new File(url.toURI()));
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(String fileName, Object object) {
        try {
            URL url = ConfigUtils.class.getResource(GlobalConstants.CONFIG_DIR + fileName);
            File file = new File(url.toURI());
            if (!file.exists()) {
                if (file.createNewFile()) {
                    logger.debug("File '" + fileName + "' saved successfully.");
                }
            }
            FileUtils.writeStringToFile(file, JSON.toJSONString(object));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
