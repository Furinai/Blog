package cn.linter.blog.controller;

import cn.linter.blog.entity.Response;
import cn.linter.blog.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/count")
    public Response<Map<String, Integer>> countArticleAndComment() {
        Map<String, Integer> map = adminService.countArticleAndComment();
        if (map.isEmpty()) {
            return Response.error("获取失败！");
        }
        return Response.success("获取成功！", map);
    }
}
