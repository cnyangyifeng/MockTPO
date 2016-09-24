package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.User;
import com.mocktpo.util.DbUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTest {

    private UserMapper userMapper;
    private SqlSession session;

    @Test
    public void testSchema() {
        userMapper.schema();
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("eric");
        user.setEmail("account@qq.com");
        user.setMobile("13581651017");
        user.setLicense("1234-1234-1234-1234");
        user.setHardware("4321-4321-4321-4321");

        userMapper.insert(user);
    }


    @Test
    public void testCount() {
        long count = userMapper.count();
        System.out.println(count);
    }

    @Before
    public void setUp() {
        DbUtil.init();
        session = DbUtil.factory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }


    @After
    public void tearDown() {
        session.commit();
        session.close();
    }
}
