package com.sc.common.proxy;

import org.apache.ibatis.annotations.Select;

public interface InfoMapperInterface {
    @Select("select info_name from info")
    String getInfoName(String param);
}
