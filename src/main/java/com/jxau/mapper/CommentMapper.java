package com.jxau.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.entity.Blog;
import com.jxau.entity.Comment;

public interface CommentMapper extends BaseMapper<Comment> {
	/*
	 * 增加评论
	 */
	public int addComment(Comment comment);
}
