package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.UserModel;
import com.mocktpo.util.DbUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTest {

    private UserMapper userMapper;
    private SqlSession session;

    @Test
    public void testSchema() {
        userMapper.drop();
        userMapper.schema();
    }

    @Test
    public void testInsert() {
        UserModel user = new UserModel();
        user.setName("eric");
        user.setEmail("account@qq.com");
        user.setLicense("1234-1234-1234-1234");

        userMapper.insert(user);
    }

    @Test
    public void testCount() {
        long count = userMapper.count();
        System.out.println(count);
    }

    @Before
    public void setUp() {
        DbUtils.init();
        session = DbUtils.getSqlSession();
        userMapper = session.getMapper(UserMapper.class);
    }


    @After
    public void tearDown() {
        session.commit();
        session.close();
    }
}
