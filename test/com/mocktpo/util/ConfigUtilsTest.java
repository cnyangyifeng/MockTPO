package com.mocktpo.util;

import com.alibaba.fastjson.JSON;
import com.mocktpo.config.UserConfig;
import org.junit.Test;

public class ConfigUtilsTest {

    @Test
    public void testLoad() {
        UserConfig uc = ConfigUtils.load("user_config.json", UserConfig.class);
        System.out.println(uc);

        String ucs = JSON.toJSONString(uc);
        System.out.println(ucs);
    }

    @Test
    public void testSave() {
        UserConfig uc = ConfigUtils.load("user_config.json", UserConfig.class);
        uc.setUserId("nikita");
        ConfigUtils.save("user_config.json", uc);
    }
}
