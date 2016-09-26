package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.User;
import com.mocktpo.util.DatabaseUtils;
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
        User user = new User();
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
        DatabaseUtils.init();
        session = DatabaseUtils.getSqlSession();
        userMapper = session.getMapper(UserMapper.class);
    }


    @After
    public void tearDown() {
        session.commit();
        session.close();
    }
}
