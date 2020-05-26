package com.jxau.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jxau.entity.Comment;
import com.jxau.entity.User;
import com.jxau.service.CommentService;
import com.jxau.vo.VoQuery;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;

	@Value("${comment.avatar}")
	private String avatar;

	@RequestMapping("/blog/comments/{id}")
	public String comments(@PathVariable long id, Model model) {
		model.addAttribute("comments", commentService.getCommentsByBlogId(id));
		return "blog::commentList";
	}

	@RequestMapping("/blog/comments")
	public String post(VoQuery vo, HttpSession session) {
		Comment comment = vo.getComment();
		Long bid = comment.getBlog().getId();
		vo.getComment();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			comment.setAvatar(user.getAvatar());
			comment.setAdminComment(true);
		} else {
			comment.setAvatar(avatar);
		}
		commentService.addComment(comment);
		return "redirect:/blog/comments/" + bid;
	}

}
