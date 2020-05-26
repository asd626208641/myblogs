package com.jxau.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Type;
import com.jxau.mapper.TypeMapper;
import com.jxau.service.TypeService;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

	@Autowired
	TypeMapper typeMapper;

	@Override
	public Page<Type> getTypes(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Page<Type> page = new Page<>(pageNum, pageSize);
		typeMapper.selectPage(page, null);
		return page;
	}
	
	@Override
	public List<Map<String, Long>> getTypesTop(int sizeNum) {
		// TODO Auto-generated method stub
		return typeMapper.getTypesTop(sizeNum);
	}
	
	@Override
	public Page<Type> getTopTypes(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		Page<Type> page = new Page<>(pageNum, pageSize);
		typeMapper.selectPage(page, null);
		return page;
	}

	@Override
	@Transactional
	public boolean deleteTypeById(long id) {
		// TODO Auto-generated method stub
		typeMapper.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public boolean addType(Type type) {
		// TODO Auto-generated method stub
		typeMapper.insert(type);
		return true;
	}

	@Override
	public boolean getTypeByName(Type type) {
		// TODO Auto-generated method stub
		QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", type.getName());
		Type t = typeMapper.selectOne(queryWrapper);
		if (t != null) {
			return true;
		}
		return false;
	}

	@Override
	public Type getTypeById(long id) {
		// TODO Auto-generated method stub
		return typeMapper.selectById(id);
	}

	@Override
	@Transactional
	public boolean updateType(Type type) {
		// TODO Auto-generated method stub
		typeMapper.updateById(type);
		return true;
	}
}
