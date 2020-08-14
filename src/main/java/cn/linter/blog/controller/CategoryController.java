package cn.linter.blog.controller;

import cn.linter.blog.entity.Category;
import cn.linter.blog.entity.Response;
import cn.linter.blog.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('admin')")
    public Response<?> addCategory(@RequestBody Category category) {
        int result = categoryService.addCategory(category);
        if (result == 1) {
            return Response.success("添加成功！");
        }
        return Response.error("添加失败！");
    }

    @PutMapping("/category")
    @PreAuthorize("hasRole('admin')")
    public Response<?> updateCategory(@RequestBody Category category) {
        int result = categoryService.updateCategory(category);
        if (result == 1) {
            return Response.success("编辑成功！");
        }
        return Response.error("编辑失败！");
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('admin')")
    public Response<?> deleteCategory(@PathVariable("id") int id) {
        int result = categoryService.deleteCategory(id);
        if (result > 0) {
            return Response.success("删除成功！");
        }
        return Response.error("删除失败！");
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
