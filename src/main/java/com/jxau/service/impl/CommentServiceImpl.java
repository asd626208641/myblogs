package com.jxau.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxau.entity.Comment;
import com.jxau.mapper.CommentMapper;
import com.jxau.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
	
	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		Long id = comment.getParentComment().getId(); // 获得父类的id
		if (id == -1) {
			comment.setParentComment(null);
		}
		commentMapper.addComment(comment);
		return true;
	}

	@Override
	public List<Comment> getCommentsByBlogId(long bid) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().isNull("parent_comment_id").eq("blog_id", bid));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname = comment.getNickname();
            List<Comment> childComments = commentMapper.selectList(new QueryWrapper<Comment>().eq("parent_comment_id", id));
            //查询出子评论
            combineChildren(childComments, parentNickname);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
		return comments;
	}
	
    /**
     * @Description: 查询出子评论
     * @Date: 17:31 2020/4/14
     * @Param: childComments：所有孩子评论
     * @Param: parentNickname：父评论的姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments, String parentNickname) {
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String childNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询二级以及所有子集回复
                recursively(childId, childNickname);
            }
        }
    }
    
    /**
     * @Description: 循环迭代找出子集回复
     * @Date: 17:33 2020/4/14
     * @Param: childId：子评论的id
     * @Param: parentNickname1：子评论的姓名
     * @Return:
     */
    private void recursively(Long childId, String childNickname) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.selectList(new QueryWrapper<Comment>().eq("parent_comment_id", childId));
        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(childNickname);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }
    
}








