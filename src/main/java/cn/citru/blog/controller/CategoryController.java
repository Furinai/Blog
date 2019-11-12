package cn.citru.blog.controller;

import cn.citru.blog.entity.Response;
import cn.citru.blog.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Response getCategories(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        PageInfo pageInfo = categoryService.getCategories(pageNumber, pageSize);
        if (pageInfo.getList() == null) return new Response("error", "获取失败");
        return new Response("success", "获取成功", pageInfo.getTotal(), pageInfo.getList());
    }

}
