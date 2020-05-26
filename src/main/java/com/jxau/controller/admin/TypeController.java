package com.jxau.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxau.entity.Type;
import com.jxau.service.TypeService;
import com.jxau.util.Constant;
import com.jxau.vo.VoQuery;

@Controller
@RequestMapping("/admin")
public class TypeController {
	@Autowired
	private TypeService typeService;

	@RequestMapping("/type")
	public String typeUI(Model model, @RequestParam(defaultValue = Constant.PAGENUM) int pageNum,
			@RequestParam(defaultValue = Constant.PAGESIZE) int pageSize) {
		Page<Type> types = typeService.getTypes(pageNum, pageSize);
		model.addAttribute("types", types);
		return "admin/types";
	}

	@RequestMapping("/type/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		if (id != null) {
			typeService.deleteTypeById(id);
			attributes.addFlashAttribute("message", "删除成功");
		}
		return "redirect:/admin/type";
	}

	@RequestMapping("/type/inputUI")
	public String inputUI(Model model) {
		model.addAttribute("type", new Type());
		return "admin/types-input";
	}

	@RequestMapping("/type/input")
	public String input(VoQuery vo, RedirectAttributes attributes) {
		Type type = vo.getType();
		if (type != null) {
			boolean result = typeService.getTypeByName(type);
			if (result) {
				attributes.addFlashAttribute("message", "分类存在，请重新输入");
			} else {
				typeService.addType(type);
			}
		}
		return "redirect:/admin/type";
	}

	@RequestMapping("/type/editUI/{id}")
	public String editUI(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeService.getTypeById(id));
		return "admin/types-input";
	}

	@RequestMapping("/type/update/{id}")
	public String update(@PathVariable Long id, VoQuery vo, RedirectAttributes attributes) {
		Type type = vo.getType();
		type.setId(id);
		if (type != null) {
			boolean result = typeService.getTypeByName(type);
			if (result) {
				attributes.addFlashAttribute("message", "分类存在，请重新输入");
			} else {
				typeService.updateType(type);
			}
		}
		return "redirect:/admin/type";
	}

}
