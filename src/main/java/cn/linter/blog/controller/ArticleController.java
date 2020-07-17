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
    public Response<?> addArticle(@RequestBody Article article) {
        int result = articleService.addArticle(article);
        if (result == 1) {
            return Response.success("发表成功！");
        }
        return Response.error("发表失败！");
    }

    @GetMapping("/articles")
    public Response<?> listArticles(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                                    @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = articleService.listArticles(categoryId, pageNumber, pageSize);
        if (pageInfo.getList() == null) {
            return Response.error("目标分类下暂无文章！");
        }
        return Response.success("文章列表获取成功！", pageInfo);
    }

    @GetMapping("/article/{id}")
    public Response<?> getArticleById(@PathVariable("id") int articleId) {
        Article article = articleService.getArticleById(articleId);
        if (article == null) {
            return Response.error("目标文章不存在!");
        }
        return Response.success("文章获取成功！", article);
    }
}
