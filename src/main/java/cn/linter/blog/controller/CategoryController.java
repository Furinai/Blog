package cn.linter.blog.controller;

import cn.linter.blog.entity.Category;
import cn.linter.blog.entity.Response;
import cn.linter.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Response getCategories() {
        List<Category> categories = categoryService.getCategories();
        if (categories == null) {
            return new Response("error", "暂无分类！");
        }
        return new Response("success", categories);
    }

}
