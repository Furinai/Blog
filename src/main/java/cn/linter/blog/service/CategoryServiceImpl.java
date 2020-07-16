package cn.linter.blog.service;

import cn.linter.blog.entity.Category;
import cn.linter.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> listCategories() {
        return categoryMapper.selectCategories();
    }
}
