package cn.linter.blog.controller;

import cn.linter.blog.entity.Response;
import cn.linter.blog.entity.User;
import cn.linter.blog.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Value("${blog.register.enable}")
    private boolean enableRegister;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public Response<?> getAuthentication(@AuthenticationPrincipal User user) {
        if (user != null) {
            user.setPassword(null);
        }
        return Response.success("用户信息获取成功！", user);
    }

    @PostMapping("/register")
    public Response<?> register(@RequestBody User user) {
        if (!enableRegister) {
            return Response.error("暂不开放注册！");
        }
        int result = userService.register(user);
        switch (result) {
            case -1:
                return Response.error("邮箱已被注册！");
            case -2:
                return Response.error("用户名已存在！");
            default:
                return Response.success("注册成功！");
        }
    }

    @GetMapping("/user/{id}")
    public Response<?> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.error("此用户不存在！");
        }
        user.setPassword(null);
        return Response.success("用户信息获取成功！", user);
    }
}
