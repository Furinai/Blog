package cn.linter.blog.mapper;

import cn.linter.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int insertArticle(Article article);

    int updateArticle(Article article);

    int deleteArticle(@Param("ids") int[] ids);

    void deleteArticleByCategoryId(@Param("ids") int[] ids);

    void incrementViewCount(@Param("articleId") int articleId);

    void incrementCommentCount(@Param("articleId") int articleId);

    void decreaseCommentCount(@Param("ids") int[] ids);

    Article selectArticleById(@Param("articleId") int articleId);

    List<Article> selectArticles(@Param("categoryId") int categoryId);
}
