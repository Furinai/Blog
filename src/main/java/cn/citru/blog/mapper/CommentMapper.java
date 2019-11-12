package cn.citru.blog.mapper;

import cn.citru.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);

    List<Comment> selectComments(@Param("articleId") int articleId);
}
