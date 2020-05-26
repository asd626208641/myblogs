package com.jxau.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Blog;

public interface BlogService {
	/*
	 * 通过分页获取blog
	 */
	public Page<Blog> getBlogs(int pageNum, int pageSize);
	
	/*
	 * 根据博客标题查询博客
	 */
	public Page<Blog> getBlogsByTitle(int pageNum, int pageSize,String title);

	/*
	 * 通过分页获取推荐的blogs
	 */
	public Page<Blog> getBlogsTop(int pageSize);
	
	/*
	 * 通过分页获取发布的blogs
	 */
	public Page<Blog> getBlogsByPublished(int pageNum, int pageSize);
	
	/*
	 * 通过id获取blog
	 */
	public Blog getBlogById(long id);

	/*
	 * 通过分页和查询条件获取blog
	 */
	public Page<Blog> getBlogsByInfo(int pageNum, int pageSize,Blog blog);
	
	/*
	 * 通过分页和分类id进行查询 (前端显示)
	 */
	public Page<Blog> getBlogsByType(int pageNum, int pageSize,long id);
	
	/*
	 * 通过分页和标签id进行查询 (前端显示)
	 */
	public Page<Blog> getBlogsByTag(int pageNum, int pageSize,long id);
	
	/*
	 * 增加博客
	 */
	public boolean addBlog(Blog blog);
	
	/*
	 * 修改博客
	 */
	public boolean updateBlog(Blog blog);
	
	/*
	 * 刪除博客
	 */
	public boolean deleteBlog(long id);	
	
	/*
	 * 添加中间表数据
	 */
	public void addBlogAndTags(Blog blog);
	
	/*
	 * 刪除中间表数据
	 */
	public void deleteBlogAndTags(long id);

	/*
	 * 把文本格式转化为html格式
	 */
	public Blog getAndConvert(Long id);

	/*
	 * 根据所有年份统计博客 
	 */
	public Map<Long, List<Blog>> getBlogByYears(int pageNum, int pageSize);

	/*
	 * 根据传入的年份统计博客 
	 */
	public Page<Blog> getBlogByYear(int pageNum, int pageSize,long year);
}
