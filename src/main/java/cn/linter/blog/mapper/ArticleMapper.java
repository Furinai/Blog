package cn.linter.blog.mapper;

import cn.linter.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    int insertArticle(Article article);

    int updateArticle(Article article);

    int deleteArticleByIds(@Param("ids") int[] ids);

    Article selectArticleById(@Param("id") int id);

    void deleteArticleByCategoryId(@Param("categoryId") int categoryId);

    void increaseViewCount(@Param("articleId") int articleId);

    void increaseCommentCount(@Param("articleId") int articleId);

    void decreaseCommentCount(@Param("maps") List<Map<String, Integer>> maps);

    List<Article> selectArticles(@Param("categoryId") int categoryId);
}
