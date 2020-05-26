package com.jxau.service;

import java.util.List;

import com.jxau.entity.Comment;

public interface CommentService {
	/*
	 * 增加评论
	 */
	public boolean addComment(Comment comment);

	/*
	 * 根据博客id查询对应的留言
	 */
	public List<Comment> getCommentsByBlogId(long id);
}
