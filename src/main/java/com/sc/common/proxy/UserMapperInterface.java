package com.sc.common.proxy;

import org.apache.ibatis.annotations.Select;

public interface UserMapperInterface {
    @Select("select user_name from user")
    String getUserName(String param);
}
