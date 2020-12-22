/**
 * Copyright (c) 2019, zhonglian . All rights reserved. Use is subject to license terms.
 */
package com.sc.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity<ID> implements Serializable {

	private static final long serialVersionUID = 4864980818782455146L;
	
	//雪花策略
	@JsonSerialize(using = ToStringSerializer.class)
	@TableId
	private ID id;

	/** 日期类型保存到数据库和下面的两个注解没有关系，mysql-connector驱动包中默认使用SimpleDateFormat[yyyy-MM-dd][yyyy-MM-dd HH:mm:ss]将日期转化成String，数据库中存的日期字符串格式即此 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  //web中application/json 序列化与反序列化
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //web中form表单 序列化与反序列化
	//private Date createTime;
	private LocalDateTime createTime;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")  //web中application/json 序列化与反序列化
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //web中form表单 序列化与反序列化
	//private Date modifyTime;
	private LocalDateTime modifyTime;

}
