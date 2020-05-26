package com.jxau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Blog;
import com.jxau.service.BlogService;
import com.jxau.service.TagService;
import com.jxau.service.TypeService;
import com.jxau.util.Constant;
import com.jxau.vo.VoQuery;

@Controller
public class IndexController {
	@Autowired
	private TypeService typeService;

	@Autowired
	private TagService tagService;

	@Autowired
	private BlogService blogService;

	// 显示主页面
	@RequestMapping("/index")
	public String index(Model model, @RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize) {
		Page<Blog> blogs = blogService.getBlogsByPublished(pageNum, pageSize);
		if (pageNum > blogs.getPages()) {
			pageNum = (int) blogs.getPages();
		}
		model.addAttribute("blogs", blogService.getBlogsByPublished(pageNum, pageSize));
		model.addAttribute("tblogs", blogService.getBlogsTop(Constant.TYPESIZE));
		model.addAttribute("types", typeService.getTypesTop(Constant.TYPESIZE));
		model.addAttribute("tags", tagService.getTagsTop(Constant.TAGSIEZE));
		return "index";
	}

	// 点击查看具体博客内容
	@RequestMapping("/blog/{id}")
	public String blogs(Model model, @PathVariable Long id) {
		Blog blog = blogService.getAndConvert(id);
		if (blog == null) {
			return "redirect:/index";
		}
		model.addAttribute("blog", blog);
		return "blog";
	}
	
	//根据博客标题查询博客
	@RequestMapping("/search")
	public String search(Model model, @RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize,VoQuery vo) {
		if(vo.getBlog().getTitle()==""||vo.getBlog().getTitle()==null ) {
			return "redirect:index";
		}
		Page<Blog> blogs = blogService.getBlogsByTitle(pageNum, pageSize, vo.getBlog().getTitle());
		model.addAttribute("blogs", blogs);
		model.addAttribute("queryInfo", vo.getBlog().getTitle());
		return "search";
	}
	
	//根据博客标题查询分页
	@RequestMapping("/search/page")
	public String searchPage(Model model, @RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize,VoQuery vo) {
		Page<Blog> blogs = blogService.getBlogsByTitle(pageNum, pageSize, vo.getBlog().getTitle());
		model.addAttribute("blogs", blogs);
		return "search::blogList";
	}
}
