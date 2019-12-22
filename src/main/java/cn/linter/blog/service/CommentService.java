package cn.linter.blog.service;

import cn.linter.blog.entity.Comment;
import cn.linter.blog.entity.User;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    int addComment(Comment comment, User user);

    PageInfo<?> getComments(int articleId, int pageNumber, int pageSize);
}
