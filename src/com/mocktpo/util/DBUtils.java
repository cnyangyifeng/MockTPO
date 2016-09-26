package com.mocktpo.util;

import com.mocktpo.orm.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtils {

    private static SqlSessionFactory factory;

    public static void init() {
        factory = new SqlSessionFactoryBuilder().build(DBUtils.class.getResourceAsStream(GlobalConstants.APPLICATION_DIR + "db_conf.xml"));
        factory.getConfiguration().addMapper(UserMapper.class);
    }

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}
