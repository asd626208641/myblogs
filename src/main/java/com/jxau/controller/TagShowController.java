package com.jxau.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jxau.entity.Blog;
import com.jxau.service.BlogService;
import com.jxau.service.TagService;
import com.jxau.util.Constant;

@Controller
public class TagShowController {

	@Autowired
	TagService tagService;

	@Autowired
	BlogService blogService;

	@RequestMapping("/tags/{id}")
	public String tags(@PathVariable long id, Model model,
			@RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize) {
		List<Map<String, Long>> tags = tagService.getTagsTop(Constant.INDEX_PAGESIZE_TYPESORTAGS);
		if (id == -1) {
			id = tags.get(0).get("id");
		}
		model.addAttribute("tags", tags);
		model.addAttribute("blogs", blogService.getBlogsByTag(pageNum, pageSize, id));
		model.addAttribute("activeTagId", id);
		return "tags";
	}

	@RequestMapping("/tags/page")
	public String tagsPage(long id, Model model, @RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize) {
		model.addAttribute("blogs", blogService.getBlogsByTag(pageNum, pageSize, id));
		return "tags::blogList";
	}
}
