package com.jxau.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.util.MarkdownUtils;
import com.jxau.entity.Blog;
import com.jxau.entity.Tag;
import com.jxau.mapper.BlogMapper;
import com.jxau.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogMapper blogMapper;

	@Override
	public Page<Blog> getBlogs(int pageNum, int pageSize) {
		Page<Blog> page = new Page<>(pageNum, pageSize);
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("update_time");
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}
	
	@Override
	public Page<Blog> getBlogsByTitle(int pageNum, int pageSize, String title) {
		// TODO Auto-generated method stub
		Page<Blog> page = new Page<>(pageNum, pageSize);
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("title", title).eq("published", 1).orderByDesc("update_time");
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}

	@Override
	public Page<Blog> getBlogsTop(int pageSize) {
		// TODO Auto-generated method stub
		Page<Blog> page = new Page<>(1, pageSize);
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("id", "title").eq("recommend", 1).eq("published", 1).orderByDesc("update_time");
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}

	@Override
	public Page<Blog> getBlogsByPublished(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Page<Blog> page = new Page<>(pageNum, pageSize);
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("published", 1).orderByDesc("update_time");
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}

	@Override
	public Page<Blog> getBlogsByInfo(int pageNum, int pageSize, Blog blog) {
		// TODO Auto-generated method stub
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("update_time");
		if (blog.getTitle() != null && !blog.getTitle().equals("")) {
			queryWrapper.like("title", blog.getTitle());
		}
		if (blog.getType().getId() != null) {
			queryWrapper.eq("type_id", blog.getType().getId());
		}
		if (blog.isRecommend()) {
			queryWrapper.eq("recommend", blog.isRecommend());
		}
		Page<Blog> page = new Page<>(pageNum, pageSize);
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}

	@Override
	public Blog getBlogById(long id) {
		// TODO Auto-generated method stub
		Blog blog = blogMapper.selectById(id);
		if (blog != null) {
			StringBuilder str = new StringBuilder();
			List<Tag> tags = blog.getTags();
			for (Tag tag : tags) {
				str.append(tag.getId()).append(",");
			}
			blog.setTagIds(str.substring(0, str.length() - 1));
		}
		return blog;
	}

	@Override
	public Page<Blog> getBlogsByType(int pageNum, int pageSize, long id) {
		// TODO Auto-generated method stub
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type_id", id).eq("published", 1).orderByDesc("update_time");
		Page<Blog> page = new Page<>(pageNum, pageSize);
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}

	@Override
	public Page<Blog> getBlogsByTag(int pageNum, int pageSize, long id) {
		List<Long> blogIds = blogMapper.getBlogIds(id);
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		Page<Blog> page = new Page<>(pageNum, pageSize);
		if (blogIds.size() != 0) {
			queryWrapper.eq("published", 1);
			for (int i = 0; i < blogIds.size(); i++) {
				if (i == 0) {
					queryWrapper.and(wrapper -> wrapper.eq("id", blogIds.get(0)));
				} else {
					queryWrapper.or().eq("id", blogIds.get(i));
				}
			}
			queryWrapper.orderByDesc("update_time");
			blogMapper.selectPage(page, queryWrapper);
		}
		return page;
	}

	@Override
	@Transactional
	public boolean addBlog(Blog blog) {
		// TODO Auto-generated method stub
		blog.setViewsAccount(0);
		// 添加博客
		blogMapper.addBlog(blog);
		// 添加中间表
		this.addBlogAndTags(blog);
		return true;
	}

	@Override
	@Transactional
	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		blog.setUpdateTime(new Date());
		// 修改博客
		blogMapper.updateBlog(blog);
		// 刪除中间表
		this.deleteBlogAndTags(blog.getId());
		// 添加中间表
		this.addBlogAndTags(blog);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteBlog(long id) {
		// TODO Auto-generated method stub
		// 先刪除中间表
		this.deleteBlogAndTags(id);
		// 删除博客
		blogMapper.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public void addBlogAndTags(Blog blog) {
		// TODO Auto-generated method stub
		Long blogId = blog.getId();
		List<Long> tagIds = new ArrayList<Long>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringTokenizer st = new StringTokenizer(blog.getTagIds(), ",");
		while (st.hasMoreTokens()) {
			tagIds.add(Long.parseLong(st.nextToken()));
		}
		map.put("blogId", blogId);
		map.put("tagIds", tagIds);
		blogMapper.addBlogAndTags(map);
	}

	@Override
	@Transactional
	public void deleteBlogAndTags(long id) {
		// TODO Auto-generated method stub
		blogMapper.deleteBlogAndTags(id);
	}

	@Override
	public Blog getAndConvert(Long id) {
		// TODO Auto-generated method stub
		blogMapper.updateViewAccount(id);
		Blog blog = blogMapper.selectById(id);
		if (blog != null) {
			String content = blog.getContent();
			blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		}
		return blog;
	}

	@Override
	public Map<Long, List<Blog>> getBlogByYears(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		List<Long> years = blogMapper.getAllYears();
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		Page<Blog> page = new Page<>(pageNum, pageSize);
		LinkedHashMap<Long, List<Blog>> map = new LinkedHashMap<>(); //这里不使用HashMap是因为它会自动升序排序，不符合我们的查询要求
		for (Long year : years) {
			queryWrapper.select("id","title","create_time","flag").eq("year(create_time)", year).orderByDesc("create_time");
			map.put(year, blogMapper.selectPage(page, queryWrapper).getRecords());
			queryWrapper.clear();
		}
		return map;
	}

	@Override
	public Page<Blog> getBlogByYear(int pageNum, int pageSize, long year) {
		// TODO Auto-generated method stub
		QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
		Page<Blog> page = new Page<>(pageNum, pageSize);
		queryWrapper.eq("year(create_time)", year).orderByDesc("create_time");
		blogMapper.selectPage(page, queryWrapper);
		return page;
	}

}
