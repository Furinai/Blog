package cn.linter.blog.service;

import cn.linter.blog.entity.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    int addComment(Comment comment);

    int updateComment(Comment comment);

    int deleteComment(int[] ids);

    PageInfo<Comment> listComments(int articleId, int pageNum, int pageSize);
}
