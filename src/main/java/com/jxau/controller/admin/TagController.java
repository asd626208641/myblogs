package com.jxau.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Tag;
import com.jxau.service.TagService;
import com.jxau.util.Constant;
import com.jxau.vo.VoQuery;

@Controller
@RequestMapping("/admin")
public class TagController {
	@Autowired
	private TagService tagService;

	@RequestMapping("/tag")
	public String tagUI(Model model, @RequestParam(defaultValue = Constant.PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.PAGESIZE) int pageSize) {
		Page<Tag> tags = tagService.getTags(pageNum, pageSize);
		model.addAttribute("tags", tags);
		return "admin/tags";
	}

	@RequestMapping("/tag/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		if (id != null) {
			tagService.deleteTagById(id);
			attributes.addFlashAttribute("message", "删除成功");
		}
		return "redirect:/admin/tag";
	}

	@RequestMapping("/tag/inputUI")
	public String inputUI(Model model) {
		model.addAttribute("tag", new Tag());
		return "admin/tags-input";
	}

	@RequestMapping("/tag/input")
	public String input(VoQuery vo, RedirectAttributes attributes) {
		Tag tag = vo.getTag();
		if (tag != null) {
			boolean result = tagService.getTagByName(tag);
			if (result) {
				attributes.addFlashAttribute("message", "分类存在，请重新输入");
			} else {
				tagService.addTag(tag);
			}
		}
		return "redirect:/admin/tag";
	}

	@RequestMapping("/tag/editUI/{id}")
	public String editUI(@PathVariable Long id, Model model) {
		model.addAttribute("tag", tagService.getTagById(id));
		return "admin/tags-input";
	}

	@RequestMapping("/tag/update/{id}")
	public String update(@PathVariable Long id, VoQuery vo, RedirectAttributes attributes) {
		Tag tag = vo.getTag();
		tag.setId(id);
		if (tag != null) {
			boolean result = tagService.getTagByName(tag);
			if (result) {
				attributes.addFlashAttribute("message", "分类存在，请重新输入");
			} else {
				tagService.updateTag(tag);
			}
		}
		return "redirect:/admin/tag";
	}

}
