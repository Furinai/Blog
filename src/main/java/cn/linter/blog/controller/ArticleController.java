package cn.linter.blog.controller;

import cn.linter.blog.entity.Article;
import cn.linter.blog.entity.Response;
import cn.linter.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    @PreAuthorize("hasRole('admin')")
    public Response<?> addArticle(@RequestBody Article article) {
        int result = articleService.addArticle(article);
        if (result == 1) {
            return Response.success("发表成功！");
        }
        return Response.error("发表失败！");
    }

    @PutMapping("/article")
    @PreAuthorize("hasRole('admin')")
    public Response<?> updateArticle(@RequestBody Article article) {
        int result = articleService.updateArticle(article);
        if (result == 1) {
            return Response.success("编辑成功！");
        }
        return Response.error("编辑失败！");
    }

    @DeleteMapping("/article")
    @PreAuthorize("hasRole('admin')")
    public Response<?> deleteArticle(@RequestBody int[] ids) {
        int result = articleService.deleteArticle(ids);
        if (result > 0) {
            return Response.success("删除成功！");
        }
        return Response.error("删除失败！");
    }

    @GetMapping("/article/{id}")
    public Response<?> getArticleById(@PathVariable("id") int articleId) {
        Article article = articleService.getArticle(articleId);
        if (article == null) {
            return Response.error("目标文章不存在!");
        }
        return Response.success("文章获取成功！", article);
    }

    @GetMapping("/articles")
    public Response<?> listArticles(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = articleService.listArticles(categoryId, pageNum, pageSize);
        if (pageInfo.getList() == null) {
            return Response.error("目标分类下暂无文章！");
        }
        return Response.success("文章列表获取成功！", pageInfo);
    }

}
