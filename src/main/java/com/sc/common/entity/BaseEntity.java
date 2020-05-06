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
import java.time.ZoneOffset;
import java.util.Date;

/**
 * 实体类基类
 * 
 * @author ruichen
 *
 */
@Getter
@Setter
public abstract class BaseEntity<ID> implements Serializable {

	private static final long serialVersionUID = 4864980818782455146L;
	
	//雪花策略
	@JsonSerialize(using = ToStringSerializer.class)
	@TableId
	private ID id;
	
	//@JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//private Date createTime = new Date();

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private LocalDateTime createTime = LocalDateTime.now(ZoneOffset.of("+8"));
	
	//@JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//private Date modifyTime = new Date();

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private LocalDateTime modifyTime = LocalDateTime.now(ZoneOffset.of("+8"));

}
