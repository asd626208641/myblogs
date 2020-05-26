package com.jxau.mapper;



import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.entity.Blog;

public interface BlogMapper extends BaseMapper<Blog> {
	
	/*
	 * 增加博客
	 */
	public int addBlog(Blog blog);

	/*
	 * 添加中间表数据
	 */
	public int addBlogAndTags(Map<String, Object> map);
	
	/*
	 * 删除中间表
	 */
	public int updateBlog(Blog blog);
	
	/*
	 * 添加中间表数据
	 */
	public int deleteBlogAndTags(long id);
	
	/*
	 * 通过中间表的tag_id查询blogs_id
	 */
	public List<Long> getBlogIds(long id);

	/*
	 * 更新浏览次数
	 */
	public boolean updateViewAccount(Long id);
	
	/*
	 * 查询所有年份
	 */
	public List<Long> getAllYears();
}
