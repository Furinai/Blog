package cn.linter.blog.mapper;

import cn.linter.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);

    int updateComment(Comment comment);

    int deleteCommentByIds(@Param("ids") int[] ids);

    void deleteCommentByUserIds(@Param("userIds") int[] userIds);

    void deleteCommentByArticleIds(@Param("articleIds") int[] articleIds);

    void deleteCommentByCategoryId(@Param("categoryId") int categoryId);

    List<Comment> selectComments(@Param("articleId") int articleId);

    List<Map<String, Integer>> countCommentByIds(@Param("ids") int[] ids);

    List<Map<String, Integer>> countCommentByUserIds(@Param("userIds") int[] userIds);
}
