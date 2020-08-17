package cn.linter.blog.repository;

import cn.linter.blog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends ElasticsearchRepository<Article, Long> {
    Page<Article> findByTitleOrSynopsis(String title, String synopsis, Pageable page);
}
