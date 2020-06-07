package cn.linter.blog.controller;

import cn.linter.blog.entity.Article;
import cn.linter.blog.entity.Response;
import cn.linter.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public Response addArticle(@RequestBody Article article) {
        int result = articleService.addArticle(article);
        if (result == 0) {
            return new Response("error", "发表失败！");
        }
        return new Response("success", "发表成功！");
    }

    @GetMapping("/articles")
    public Response getArticles(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                                @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = articleService.getArticles(categoryId, pageNumber, pageSize);
        return new Response("success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/article/{id}")
    public Response getArticleById(@PathVariable("id") int ArticleId) {
        Article article = articleService.getArticleById(ArticleId);
        if (article == null) {
            return new Response("error", "此文章不存在!");
        }
        return new Response("success", article);
    }
}
