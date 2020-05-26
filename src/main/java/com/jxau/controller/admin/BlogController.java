package com.jxau.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Blog;
import com.jxau.service.BlogService;
import com.jxau.service.TagService;
import com.jxau.service.TypeService;
import com.jxau.util.Constant;
import com.jxau.vo.VoQuery;

@Controller
@RequestMapping("/admin")
public class BlogController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private TagService tagService;

	@RequestMapping("/blogs")
	public String blogUI(Model model, @RequestParam(defaultValue = Constant.PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.PAGESIZE) int pageSize) {
		Page<Blog> blogs = blogService.getBlogs(pageNum, pageSize);
		model.addAttribute("types", typeService.getTypes(1, 1000));  // 第一页1000个等价于查询所有
		model.addAttribute("blogs", blogs);
		return "admin/blogs";
	}

	@RequestMapping("/blogs/search")
	public String blogSearchUI(Model model, @RequestParam(defaultValue = Constant.PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.PAGESIZE) int pageSize, VoQuery vo) {
		Page<Blog> blogs = blogService.getBlogsByInfo(pageNum, pageSize, vo.getBlog());
		model.addAttribute("blogs", blogs);
		return "admin/blogs::blogList";
	}

	@RequestMapping("/blogs/inputUI")
	public String inputUI(Model model) {
		model.addAttribute("types", typeService.getTypes(1, 1000));
		model.addAttribute("tags", tagService.getTags(1, 1000));
		return "admin/blogs-input";
	}
	
	@RequestMapping("/blogs/input")
	public String input(VoQuery vo, RedirectAttributes attributes) {
		Blog blog = vo.getBlog();
		if(blog!=null) {
			blogService.addBlog(blog);
		}
		return "redirect:/admin/blogs";
	}
	
	@RequestMapping("/blogs/editUI/{id}")
	public String editUI(@PathVariable Long id, Model model) {
		model.addAttribute("blog", blogService.getBlogById(id));
		model.addAttribute("types", typeService.getTypes(1,1000));
		model.addAttribute("tags", tagService.getTags(1,1000));
		return "admin/blogs-edit";
	}
	
	@RequestMapping("/blogs/update")
	public String edit(Model model,VoQuery vo) {
		Blog blog = vo.getBlog();
		System.out.println(blog);
		if(blog!=null) {
			// 更新博客
			blogService.updateBlog(blog);
		}
		return "redirect:/admin/blogs";
	}
	
	@RequestMapping("/blogs/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		if (id != null) {
			blogService.deleteBlog(id);
			attributes.addFlashAttribute("message", "删除成功");
		}
		return "redirect:/admin/blogs";
	}
}
