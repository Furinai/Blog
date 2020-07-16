package cn.linter.blog.mapper;

import cn.linter.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int insertArticle(Article article);

    Article selectArticleById(@Param("articleId") int articleId);

    List<Article> selectArticles(@Param("categoryId") int categoryId);
}
