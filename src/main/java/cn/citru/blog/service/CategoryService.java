package cn.citru.blog.service;

import com.github.pagehelper.PageInfo;

public interface CategoryService {
    PageInfo getCategories(int pageNumber, int pageSize);
}
