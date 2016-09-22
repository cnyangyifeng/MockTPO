package com.mocktpo.config;

import com.alibaba.fastjson.JSON;
import com.mocktpo.util.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestsHomeConfigTest {

    @Test
    public void testEncode() {
        TestsHomeConfig model = new TestsHomeConfig();
        List<TestStatus> tests = new ArrayList<TestStatus>();
        tests.add(new TestStatus("TPO1", "TOEFL iBT Complete Practice Test V1", "download", "", ""));
        tests.add(new TestStatus("TPO2", "TOEFL iBT Complete Practice Test V2", "download", "", ""));
        model.setTests(tests);
        System.out.println(model);

        String json = JSON.toJSONString(model);
        System.out.println(json);
    }

    @Test
    public void testDecode() throws Exception {
        URL url = this.getClass().getResource(GlobalConstants.APPLICATION_DIR + "tests_home_config.json");
        String json = FileUtils.readFileToString(new File(url.toURI()));
        TestsHomeConfig model = JSON.parseObject(json, TestsHomeConfig.class);
        System.out.println(model);
    }
}
