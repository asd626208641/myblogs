package com.jxau.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jxau.service.BlogService;
import com.jxau.service.TypeService;
import com.jxau.util.Constant;

@Controller
public class TypeShowController {

	@Autowired
	TypeService typeService;

	@Autowired
	BlogService blogService;

	@RequestMapping("/types/{id}")
	public String types(@PathVariable long id, Model model,
			@RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize) {
		List<Map<String, Long>> types = typeService.getTypesTop(Constant.INDEX_PAGESIZE_TYPESORTAGS);
		if (id == -1) {
			id = types.get(0).get("id");
		}
		model.addAttribute("types", types);
		model.addAttribute("blogs", blogService.getBlogsByType(pageNum, pageSize, id));
		model.addAttribute("activeTypeId", id);
		return "types";
	}

	@RequestMapping("/types/page")
	public String typesPage(long id, Model model, @RequestParam(defaultValue = Constant.INDEX_PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.INDEX_PAGESIZE) int pageSize) {
		model.addAttribute("blogs", blogService.getBlogsByType(pageNum, pageSize, id));
		return "types::blogList";
	}

}
