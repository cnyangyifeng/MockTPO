package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Update({
            "CREATE TABLE MT_USER (",
            "MT_USER_ID BIGINT AUTO_INCREMENT,",
            "MT_USER_NAME VARCHAR,",
            "MT_USER_EMAIL VARCHAR,",
            "MT_USER_MOBILE VARCHAR,",
            "MT_USER_LICENSE VARCHAR,",
            "MT_USER_HARDWARE VARCHAR",
            ")"
    })
    void schema();

    @Insert({
            "INSERT INTO MT_USER (",
            "MT_USER_ID, MT_USER_NAME, MT_USER_EMAIL, MT_USER_MOBILE, MT_USER_LICENSE, MT_USER_HARDWARE",
            ") VALUES (",
            "#{id}, #{name}, #{email}, #{mobile}, #{license}, #{hardware}",
            ")"
    })
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    void insert(User user);

    @Select(
            "SELECT COUNT(*) FROM MT_USER"
    )
    long count();
}
