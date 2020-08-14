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

    int deleteComment(@Param("ids") int[] ids);

    int deleteCommentByUserId(@Param("ids") int[] ids);

    int deleteCommentByArticleId(@Param("ids") int[] ids);

    List<Comment> selectComments(@Param("articleId") int articleId);

    List<Map<String, Integer>> computeCommentCount(@Param("ids") int[] ids);
}
