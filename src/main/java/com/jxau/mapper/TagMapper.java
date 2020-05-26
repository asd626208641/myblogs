package com.jxau.mapper;



import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.entity.Tag;

public interface TagMapper extends BaseMapper<Tag> {
	/*
	 * 查询标签及其数量，查询条数是sizeNum
	 */
	public List<Map<String, Long>> getTagsTop(int sizeNum);
	
}
