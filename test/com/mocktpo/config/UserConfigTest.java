package com.mocktpo.config;

import com.alibaba.fastjson.JSON;
import com.mocktpo.util.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class UserConfigTest {

    @Test
    public void testEncode() {
        UserConfig model = new UserConfig();
        model.setUserId("eric");
        model.setMobile("13581651017");
        model.setSecret("1234-1234-1234-1234");
        System.out.println(model);

        String json = JSON.toJSONString(model);
        System.out.println(json);
    }

    @Test
    public void testDecode() throws Exception {
        URL url = this.getClass().getResource(GlobalConstants.APPLICATION_DIR + "user_config.json");
        String json = FileUtils.readFileToString(new File(url.toURI()));
        UserConfig model = JSON.parseObject(json, UserConfig.class);
        System.out.println(model);
    }
}
