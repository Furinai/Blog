package cn.linter.blog.service;

import cn.linter.blog.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(int categoryId);

    List<Category> listCategories();
}
