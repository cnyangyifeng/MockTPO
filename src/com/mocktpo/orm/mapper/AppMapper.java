package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.AppModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AppMapper {

    @Update({
            "CREATE TABLE MT_APP (",
            "MT_APP_FIRST_RUN BOOLEAN",
            ")"
    })
    void schema();


    @Update({
            "DROP TABLE MT_APP IF EXISTS"
    })
    void drop();

    @Insert({
            "INSERT INTO MT_APP (",
            "MT_APP_FIRST_RUN",
            ") VALUES (",
            "#{firstRun}",
            ")"
    })
    void insert(AppModel app);

    @Select({
            "SELECT * FROM MT_APP"
    })
    @Results({
            @Result(column = "MT_APP_FIRST_RUN", property = "firstRun", jdbcType = JdbcType.BOOLEAN)
    })
    List<AppModel> find();

    @Update({
            "UPDATE MT_APP",
            "SET MT_APP_FIRST_RUN = #{firstRun}"
    })
    int update(AppModel app);
}
