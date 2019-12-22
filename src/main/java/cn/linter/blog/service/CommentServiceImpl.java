package cn.linter.blog.service;

import cn.linter.blog.entity.Comment;
import cn.linter.blog.entity.User;
import cn.linter.blog.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public int addComment(Comment comment, User user) {
        comment.setUser(user);
        long time = System.currentTimeMillis();
        comment.setCreateTime(new Timestamp(time));
        return commentMapper.insertComment(comment);
    }

    @Override
    public PageInfo<?> getComments(int articleId, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Comment> comments = commentMapper.selectComments(articleId);
        return new PageInfo<>(comments);
    }
}
