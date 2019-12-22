package cn.linter.blog.mapper;

import cn.linter.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategories();
}
