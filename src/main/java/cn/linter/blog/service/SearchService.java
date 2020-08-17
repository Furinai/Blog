package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<Article> search(String keyword, int pageNum, int pageSize);

    void indexes();
}
