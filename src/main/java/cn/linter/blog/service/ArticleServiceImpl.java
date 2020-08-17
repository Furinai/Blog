package cn.linter.blog.service;

import cn.linter.blog.entity.Article;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CommentMapper;
import cn.linter.blog.repository.SearchRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;
    private final SearchRepository searchRepository;

    public ArticleServiceImpl(ArticleMapper articleMapper, CommentMapper commentMapper, SearchRepository searchRepository) {
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.searchRepository = searchRepository;
    }

    @Override
    public int addArticle(Article article) {
        article.setCreatedTime(LocalDateTime.now());
        int result = articleMapper.insertArticle(article);
        searchRepository.save(article);
        return result;
    }

    @Override
    public int updateArticle(Article article) {
        int result = articleMapper.updateArticle(article);
        Long id = article.getId().longValue();
        searchRepository.deleteById(id);
        searchRepository.save(article);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteArticle(int[] ids) {
        commentMapper.deleteCommentByArticleIds(ids);
        int result = articleMapper.deleteArticleByIds(ids);
        for (long id : ids) {
            searchRepository.deleteById(id);
        }
        return result;
    }

    @Override
    public Article getArticle(int articleId) {
        articleMapper.increaseViewCount(articleId);
        return articleMapper.selectArticleById(articleId);
    }

    @Override
    public PageInfo<Article> listArticles(int categoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(articleMapper.selectArticles(categoryId), 5);
    }
}
