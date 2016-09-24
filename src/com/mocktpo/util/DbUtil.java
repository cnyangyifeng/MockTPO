package com.mocktpo.util;

import com.mocktpo.orm.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DbUtil {

    public static SqlSessionFactory factory = null;

    public static void init() {
        String conf = "db_conf.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(conf);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(UserMapper.class);
    }
}
