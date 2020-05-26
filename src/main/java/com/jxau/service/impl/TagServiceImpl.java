package com.jxau.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Tag;
import com.jxau.mapper.TagMapper;
import com.jxau.service.TagService;

@Service("tagService")
public class TagServiceImpl implements TagService {

	@Autowired
	TagMapper tagMapper;

	@Override
	public Page<Tag> getTags(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Page<Tag> page = new Page<>(pageNum, pageSize);
		tagMapper.selectPage(page, null);
		return page;
	}
	
	@Override
	public List<Map<String, Long>> getTagsTop(Integer sizeNum) {
		// TODO Auto-generated method stub
		return tagMapper.getTagsTop(sizeNum);
	}

	@Override
	@Transactional
	public boolean deleteTagById(long id) {
		// TODO Auto-generated method stub
		tagMapper.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public boolean addTag(Tag tag) {
		// TODO Auto-generated method stub
		tagMapper.insert(tag);
		return true;
	}

	@Override
	public boolean getTagByName(Tag tag) {
		// TODO Auto-generated method stub
		QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", tag.getName());
		Tag t = tagMapper.selectOne(queryWrapper);
		if (t != null) {
			return true;
		}
		return false;
	}

	@Override
	public Tag getTagById(long id) {
		// TODO Auto-generated method stub
		return tagMapper.selectById(id);
	}

	@Override
	@Transactional
	public boolean updateTag(Tag tag) {
		// TODO Auto-generated method stub
		tagMapper.updateById(tag);
		return true;
	}
}
