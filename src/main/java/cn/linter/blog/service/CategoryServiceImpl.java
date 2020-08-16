package cn.linter.blog.service;

import cn.linter.blog.entity.Category;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CategoryMapper;
import cn.linter.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper, ArticleMapper articleMapper, CommentMapper commentMapper) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
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
        commentMapper.deleteCommentByCategoryId(id);
        articleMapper.deleteArticleByCategoryId(id);
        return categoryMapper.deleteCategory(id);
    }

    @Override
    public Category getCategory(int categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }

    @Override
    public List<Category> listCategories() {
        return categoryMapper.selectCategories();
    }
}
