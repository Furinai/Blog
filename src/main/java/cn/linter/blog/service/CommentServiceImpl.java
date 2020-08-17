package cn.linter.blog.service;

import cn.linter.blog.entity.Comment;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final ArticleMapper articleMapper;

    public CommentServiceImpl(CommentMapper commentMapper, ArticleMapper articleMapper) {
        this.commentMapper = commentMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addComment(Comment comment) {
        int id = comment.getArticleId();
        articleMapper.increaseCommentCount(id);
        comment.setCreatedTime(LocalDateTime.now());
        return commentMapper.insertComment(comment);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteComment(int[] ids) {
        List<Map<String, Integer>> maps;
        maps = commentMapper.countCommentByIds(ids);
        articleMapper.decreaseCommentCount(maps);
        return commentMapper.deleteCommentByIds(ids);
    }

    @Override
    public PageInfo<Comment> listComments(int articleId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(commentMapper.selectCommentsByArticleId(articleId), 5);
    }
}
