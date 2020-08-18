package cn.linter.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AdminMapper {
    Map<String, Integer> countArticleAndComment();
}
