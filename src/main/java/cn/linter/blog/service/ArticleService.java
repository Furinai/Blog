package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
    int addArticle(Article article);

    Article getArticleById(int articleId);

    PageInfo<?> listArticles(int categoryId, int pageNum, int pageSize);
}
