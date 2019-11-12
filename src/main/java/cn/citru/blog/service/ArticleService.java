package cn.citru.blog.service;

import cn.citru.blog.entity.Article;
import cn.citru.blog.entity.User;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
    int addArticle(Article article, User user);

    Article getArticleById(int articleId);

    PageInfo getArticles(int categoryId, int pageNumber, int pageSize);
}
