package cn.linter.blog.controller;

import cn.linter.blog.entity.Article;
import cn.linter.blog.entity.Response;
import cn.linter.blog.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public Response<Page<Article>> searchArticle(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                 @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Article> page = searchService.search(keyword, pageNum, pageSize);
        return Response.success("搜索成功！", page);
    }

    @GetMapping("/indexes")
    @PreAuthorize("hasRole('admin')")
    public Response<?> updateIndexes() {
        searchService.indexes();
        return Response.success("更新成功！");
    }

}
