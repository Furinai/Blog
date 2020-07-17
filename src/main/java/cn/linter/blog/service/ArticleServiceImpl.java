package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import cn.linter.blog.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public int addArticle(Article article) {
        LocalDateTime localDateTime = LocalDateTime.now();
        article.setCreatedTime(localDateTime);
        return articleMapper.insertArticle(article);
    }

    @Override
    public Article getArticleById(int articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    @Override
    public PageInfo<?> listArticles(int categoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(articleMapper.selectArticles(categoryId), 5);
    }
}
