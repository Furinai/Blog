package cn.linter.blog.mapper;

import cn.linter.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int insertCategory(Category category);

    int updateCategory(Category category);

    int deleteCategoryById(@Param("id") int id);

    Category selectCategoryById(@Param("id") int id);

    List<Category> selectCategories();
}
