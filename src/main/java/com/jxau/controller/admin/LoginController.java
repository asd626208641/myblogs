package com.jxau.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jxau.entity.User;
import com.jxau.service.UserService;
import com.jxau.vo.VoQuery;

@Controller
@RequestMapping("/admin")
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/loginUI")
	public String loginUI() {
		return "admin/login";
	}

	@RequestMapping("/myPage")
	public String loginSuccess() {
		return "admin/index";
	}

	@RequestMapping("/login")
	public String login(VoQuery vo, HttpSession session, RedirectAttributes attributes) {
		User user = vo.getUser();
		if (user != null) {
			if (userService.checkUser(user)) {
				session.setAttribute("user", userService.getUser(user));
				return "redirect:/admin/myPage";
			} else {
				attributes.addFlashAttribute("message", "用户名和密码错误");
				return "redirect:/admin/loginUI";
			}
		}
		return "redirect:/admin/loginUI";
	}

	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/admin/loginUI";
	}
}
