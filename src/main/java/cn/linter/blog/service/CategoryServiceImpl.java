package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import cn.linter.blog.entity.Category;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CategoryMapper;
import cn.linter.blog.mapper.CommentMapper;
import cn.linter.blog.repository.SearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;
    private final SearchRepository searchRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, ArticleMapper articleMapper,
                               CommentMapper commentMapper, SearchRepository searchRepository) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.searchRepository = searchRepository;
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
        List<Article> articles = articleMapper.selectArticles(id);
        commentMapper.deleteCommentByCategoryId(id);
        articleMapper.deleteArticleByCategoryId(id);
        int result = categoryMapper.deleteCategory(id);
        searchRepository.deleteAll(articles);
        return result;
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
