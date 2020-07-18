package cn.linter.blog.mapper;

import cn.linter.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    Category selectCategoryById(@Param("categoryId") int categoryId);

    List<Category> selectCategories();
}
