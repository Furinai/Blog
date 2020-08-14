package cn.linter.blog.service;

import cn.linter.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    int addCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(int id);

    Category getCategoryById(int categoryId);

    List<Category> listCategories();
}
