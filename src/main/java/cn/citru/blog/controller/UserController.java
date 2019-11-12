package cn.citru.blog.controller;

import cn.citru.blog.entity.Response;
import cn.citru.blog.entity.User;
import cn.citru.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authentication")
    public Response getAuthentication(@AuthenticationPrincipal User user) {
        if (user == null) return new Response("error", "获取失败");
        user.setPassword(null);
        return new Response("success", "获取成功", user);
    }

    @PostMapping("/user")
    public Response Register(@RequestBody User user) {
        int result = userService.register(user);
        if (result == -1) return new Response("error", "用户名已存在！");
        return new Response("success", "注册成功！");
    }

    @GetMapping("/user/{id}")
    public Response getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user.getId() == 0) return new Response("error", "获取失败");
        return new Response("success", "获取成功", user);
    }
}
