package cn.linter.blog.controller;

import cn.linter.blog.entity.Category;
import cn.linter.blog.entity.Response;
import cn.linter.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/category/{id}")
    public Response<?> getCategoryById(@PathVariable("id") int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return Response.error("目标分类不存在!");
        }
        return Response.success("文章分类成功！", category);
    }

    @GetMapping("/categories")
    public Response<?> getCategories() {
        List<Category> categories = categoryService.listCategories();
        if (categories == null) {
            return Response.error("暂无文章分类！");
        }
        return Response.success("文章分类列表获取成功！", categories);
    }

}
