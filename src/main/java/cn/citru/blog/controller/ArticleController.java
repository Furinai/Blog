package cn.citru.blog.controller;

import cn.citru.blog.entity.Article;
import cn.citru.blog.entity.Response;
import cn.citru.blog.entity.User;
import cn.citru.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Response addArticle(@RequestBody Article article, @AuthenticationPrincipal User user) {
        if (user == null) return new Response("error", "请先登录");
        int result = articleService.addArticle(article, user);
        if (result == 0) return new Response("error", "发表失败");
        return new Response("success", "发表成功");
    }

    @GetMapping("/articles")
    public Response getArticles(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                                @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo pageInfo = articleService.getArticles(categoryId, pageNumber, pageSize);
        if (pageInfo.getList() == null) return new Response("error", "获取失败");
        return new Response("success", "获取成功", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/article/{id}")
    public Response getArticleById(@PathVariable("id") int ArticleId) {
        Article article = articleService.getArticleById(ArticleId);
        if (article.getId() == 0) return new Response("error", "获取失败");
        return new Response("success", "获取成功", article);
    }
}
