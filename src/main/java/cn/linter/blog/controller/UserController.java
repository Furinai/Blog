package cn.linter.blog.controller;

import cn.linter.blog.entity.Response;
import cn.linter.blog.entity.User;
import cn.linter.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Value("${blog.enableRegister}")
    private boolean enableRegister;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public Response getAuthentication(@AuthenticationPrincipal User user) {
        user.setPassword(null);
        return new Response("success", user);
    }

    @PostMapping("/register")
    public Response Register(@RequestBody User user) {
        if (!enableRegister) {
            return new Response("error", "暂不开放注册！");
        }
        int result = userService.register(user);
        switch (result) {
            case -1:
                return new Response("error", "邮箱已被注册！");
            case -2:
                return new Response("error", "用户名已存在！");
            default:
                return new Response("success", "注册成功！");
        }
    }

    @GetMapping("/user/{id}")
    public Response getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user.getId() == 0) {
            return new Response("error", "此用户不存在！");
        }
        return new Response("success", user);
    }
}
