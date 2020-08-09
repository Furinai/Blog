package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, CommentMapper commentMapper) {
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public int addArticle(Article article) {
        article.setCreatedTime(LocalDateTime.now());
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteArticle(int[] ids) {
        commentMapper.deleteCommentByArticleId(ids);
        return articleMapper.deleteArticle(ids);
    }

    @Override
    public Article getArticleById(int articleId) {
        articleMapper.incrementViewCount(articleId);
        return articleMapper.selectArticleById(articleId);
    }

    @Override
    public PageInfo<?> listArticles(int categoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(articleMapper.selectArticles(categoryId), 5);
    }
}
