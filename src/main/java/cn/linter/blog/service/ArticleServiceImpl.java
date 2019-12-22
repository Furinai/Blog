package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import cn.linter.blog.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public int addArticle(Article article) {
        long time = System.currentTimeMillis();
        article.setCreateTime(new Timestamp(time));
        return articleMapper.insertArticle(article);
    }

    @Override
    public Article getArticleById(int articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    @Override
    public PageInfo<?> getArticles(int categoryId, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(articleMapper.selectArticles(categoryId));
    }
}
