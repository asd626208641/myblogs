package com.jxau.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by limi on 2017/10/14.
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //jpa的建表自增
	@TableId(type=IdType.AUTO) //mp添加数据的自增
	private Long id;
	private String name;

	@TableField(exist = false) //由于blogs字段在数据表中不存在，不加这个注解会映射blogs字段，但是数据表不存在会报错
	@ManyToMany(mappedBy = "tags")
	private List<Blog> blogs = new ArrayList<>();

	public Tag() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", blogs=" + blogs + "]";
	}

}
