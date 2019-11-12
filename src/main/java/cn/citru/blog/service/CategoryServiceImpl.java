package cn.citru.blog.service;

import cn.citru.blog.entity.Category;
import cn.citru.blog.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    
    @Override
    public PageInfo getCategories(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Category> categories = categoryMapper.selectCategories();
        if (categories == null) return new PageInfo();
        return new PageInfo<>(categories);
    }
}
