package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.AppModel;
import com.mocktpo.util.DbUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppMapperTest {

    private AppMapper appMapper;
    private SqlSession session;

    @Test
    public void testSchema() {
        appMapper.drop();
        appMapper.schema();
    }

    @Test
    public void testInsert() {
        AppModel app = new AppModel();
        System.out.println(app.isFirstRun());
        app.setFirstRun(false);
        appMapper.insert(app);
    }

    @Test
    public void testFind() {
        AppModel app = appMapper.find().get(0);
        System.out.println(app.isFirstRun());
    }

    @Test
    public void testUpdate() {
        AppModel app = new AppModel();
        app.setFirstRun(true);
        appMapper.update(app);
    }

    @Before
    public void setUp() {
        DbUtils.init();
        session = DbUtils.getSqlSession();
        appMapper = session.getMapper(AppMapper.class);
    }


    @After
    public void tearDown() {
        session.commit();
        session.close();
    }
}
