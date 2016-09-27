package com.mocktpo.util;

import com.mocktpo.orm.mapper.AppMapper;
import com.mocktpo.orm.mapper.UserMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbUtils {

    private static SqlSessionFactory factory;

    public static void init() {
        factory = new SqlSessionFactoryBuilder().build(DbUtils.class.getResourceAsStream(GlobalConstants.CONFIG_DIR + "ds.xml"));
        Configuration c = factory.getConfiguration();

        c.addMapper(AppMapper.class);
        c.addMapper(UserMapper.class);
    }

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}
