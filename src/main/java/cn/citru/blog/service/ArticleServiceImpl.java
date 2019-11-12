package cn.citru.blog.service;

import cn.citru.blog.entity.Article;
import cn.citru.blog.entity.User;
import cn.citru.blog.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public int addArticle(Article article, User user) {
        article.setUser(user);
        String content = Jsoup.parse(article.getContent()).text();
        article.setSummary(content.substring(0, Math.min(content.length(), 300)));
        article.setCreateTime(new Timestamp(System.currentTimeMillis()));
        article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return articleMapper.insertArticle(article);
    }

    @Override
    public Article getArticleById(int articleId) {
        Article article = articleMapper.selectArticleById(articleId);
        if (article == null) return new Article();
        return article;
    }

    @Override
    public PageInfo getArticles(int categoryId, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Article> articles = articleMapper.selectArticles(categoryId);
        if (articles == null) return new PageInfo();
        return new PageInfo<>(articles);
    }
}
