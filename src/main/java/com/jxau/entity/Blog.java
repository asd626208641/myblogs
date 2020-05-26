package com.jxau.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by limi on 2017/10/14.
 */
@Entity
@Table(name = "blog")
@TableName(resultMap = "getBlogMap")
public class Blog implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // mysql的主键自增策略
	@TableId(type = IdType.AUTO) // mp添加数据的自增
	private Long id;
	private String title;
	@Lob
	private String content;
	private String firstPicture;
	private String flag;
	private Integer viewsAccount;
	private boolean appreciation;
	private boolean shareStatement;
	private boolean commentabled;
	private boolean published;
	private boolean recommend;
	@Temporal(TemporalType.TIMESTAMP)
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@ManyToOne
	private Type type;

	@ManyToMany
	@TableField(exist = false) // 由于blogs字段在数据表中不存在，不加这个注解会映射blogs字段，但是数据表不存在会报错
	private List<Tag> tags = new ArrayList<>();

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "blog")
	@TableField(exist = false) // 由于blogs字段在数据表中不存在，不加这个注解会映射blogs字段，但是数据表不存在会报错
	private List<Comment> comments = new ArrayList<>();

	@Transient
	@TableField(exist = false) // 由于blogs字段在数据表中不存在，不加这个注解会映射blogs字段，但是数据表不存在会报错
	private String tagIds; // 这个注解表示不生成数据库字段

	@Transient
	private String typeId; // 这个注解表示不生成数据库字段

	private String description;

	public Blog() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstPicture() {
		return firstPicture;
	}

	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getViewsAccount() {
		return viewsAccount;
	}

	public void setViewsAccount(Integer viewsAccount) {
		this.viewsAccount = viewsAccount;
	}

	public boolean isAppreciation() {
		return appreciation;
	}

	public void setAppreciation(boolean appreciation) {
		this.appreciation = appreciation;
	}

	public boolean isShareStatement() {
		return shareStatement;
	}

	public void setShareStatement(boolean shareStatement) {
		this.shareStatement = shareStatement;
	}

	public boolean isCommentabled() {
		return commentabled;
	}

	public void setCommentabled(boolean commentabled) {
		this.commentabled = commentabled;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", firstPicture=" + firstPicture
				+ ", flag=" + flag + ", viewsAccount=" + viewsAccount + ", appreciation=" + appreciation
				+ ", shareStatement=" + shareStatement + ", commentabled=" + commentabled + ", published=" + published
				+ ", recommend=" + recommend + ", createTime=" + createTime + ", updateTime=" + updateTime + ", type="
				+ type + ", tags=" + tags + ", user=" + user + ", comments=" + comments + ", tagIds=" + tagIds
				+ ", typeId=" + typeId + ", description=" + description + "]";
	}

}
