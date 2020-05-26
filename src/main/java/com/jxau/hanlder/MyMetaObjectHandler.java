package com.jxau.hanlder;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.fillStrategy(metaObject, "createTime", new Date());
		this.fillStrategy(metaObject, "updateTime", new Date());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.fillStrategy(metaObject, "updateTime", new Date());
	}
}