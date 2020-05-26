package com.jxau.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Tag;

public interface TagService {
	/*
	 * 分页查询所有标签
	 */
	public Page<Tag> getTags(int pageNum, int pageSize);
	
	/*
	 * 查询分类及其数量，查询条数是sizeNum
	 */
	public List<Map<String, Long>> getTagsTop(Integer sizeNum);

	/*
	 * 根据id查询标签
	 */
	public Tag getTagById(long id);

	/*
	 * 根据id修改标签
	 */
	public boolean updateTag(Tag tag);

	/*
	 * 查询同名标签
	 */
	public boolean getTagByName(Tag tag);

	/*
	 * 根据id删除标签
	 */
	public boolean deleteTagById(long id);

	/*
	 * 增加标签
	 */
	public boolean addTag(Tag tag);

}
