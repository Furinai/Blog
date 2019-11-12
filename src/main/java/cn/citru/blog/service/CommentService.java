package cn.citru.blog.service;

import cn.citru.blog.entity.Comment;
import cn.citru.blog.entity.User;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    int addComment(Comment comment, User user);

    PageInfo getComments(int articleId, int pageNumber, int pageSize);
}
