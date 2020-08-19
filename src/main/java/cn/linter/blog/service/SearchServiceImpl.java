package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.repository.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;
    private final ArticleMapper articleMapper;

    public SearchServiceImpl(SearchRepository searchRepository, ArticleMapper articleMapper) {
        this.searchRepository = searchRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public Page<Article> search(String keyword, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return searchRepository.findByTitleOrSynopsis(keyword, keyword, pageable);
    }

    @Override
    public void indexes() {
        searchRepository.deleteAll();
        searchRepository.saveAll(articleMapper.selectArticles(0));
    }
}
