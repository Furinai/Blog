package cn.linter.blog.service;

import cn.linter.blog.entity.Category;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final ArticleMapper articleMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper, ArticleMapper articleMapper) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCategory(int id) {
        articleMapper.deleteArticleByCategoryId(id);
        return categoryMapper.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }

    @Override
    public List<Category> listCategories() {
        return categoryMapper.selectCategories();
    }
}
