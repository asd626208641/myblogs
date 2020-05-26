package com.jxau.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Type;

public interface TypeService {
	/*
	 * 分页查询所有分类
	 */
	public Page<Type> getTypes(int pageNum, int pageSize);
	
	/*
	 * 查询分类及其数量，查询条数是sizeNum
	 */
	public List<Map<String, Long>> getTypesTop(int sizeNum);
	
	/*
	 * 分页查询，首页推荐的分类
	 */
	public Page<Type> getTopTypes(int pageNum,int pageSize);

	/*
	 * 根据id查询分类
	 */
	public Type getTypeById(long id);
	
	/*
	 * 根据id修改分类
	 */
	public boolean updateType(Type type);

	/*
	 * 查询同名分类
	 */
	public boolean getTypeByName(Type type);

	/*
	 * 根据id删除分类
	 */
	public boolean deleteTypeById(long id);

	/*
	 * 增加分类
	 */
	public boolean addType(Type type);

}
