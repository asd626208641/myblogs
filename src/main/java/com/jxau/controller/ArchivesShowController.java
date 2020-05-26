package com.jxau.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Blog;
import com.jxau.service.BlogService;
import com.jxau.util.Constant;


@Controller
public class ArchivesShowController {

	@Autowired
	BlogService blogService;

	@RequestMapping("/archives")
	public String archives(Model model) {
		Map<Long, List<Blog>> map = blogService.getBlogByYears(1,Constant.BLOGSIZE);
		Long count = blogService.getBlogs(1, Constant.INDEX_PAGESIZE_TYPESORTAGS).getTotal();
		model.addAttribute("archivesMap", map);
		model.addAttribute("totalCount", count);
		return "archives";
	}
	
	// 查询输入年份的全部博客
	@RequestMapping("/blogs/{year}")
	public String indexYear(Model model, @RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize,@PathVariable Long year) {
		Page<Blog> blogs = blogService.getBlogByYear(pageNum, pageSize, year);
		model.addAttribute("blogs", blogs);
		model.addAttribute("year", year);
		return "indexYear";
	}
}
