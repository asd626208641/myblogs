package com.jxau.vo;

import com.jxau.entity.Blog;
import com.jxau.entity.Comment;
import com.jxau.entity.Tag;
import com.jxau.entity.Type;
import com.jxau.entity.User;

public class VoQuery {
	private User user;
	private Type type;
	private Tag tag;
	private Blog blog;
	private Comment comment;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
