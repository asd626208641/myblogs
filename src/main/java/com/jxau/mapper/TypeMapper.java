package com.jxau.mapper;



import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.entity.Type;

public interface TypeMapper extends BaseMapper<Type> {
	/*
	 * 查询分类及其数量，查询条数是sizeNum
	 */
	public List<Map<String, Long>> getTypesTop(int sizeNum);
	
}
