package cn.linter.blog.controller;

import cn.linter.blog.entity.Response;
import cn.linter.blog.entity.User;
import cn.linter.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Response<User> getAuthentication(@AuthenticationPrincipal User user) {
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
        return addUser(user);
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('admin')")
    public Response<?> addUser(@RequestBody User user) {
        int result = userService.addUser(user);
        switch (result) {
            case 0:
                return Response.error("注册失败！");
            case -1:
                return Response.error("邮箱已被注册！");
            case -2:
                return Response.error("用户名已存在！");
            default:
                return Response.success("注册成功！");
        }
    }

    @PutMapping("/user")
    @PreAuthorize("hasRole('admin')")
    public Response<?> updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        switch (result) {
            case 0:
                return Response.error("编辑失败！");
            case -1:
                return Response.error("邮箱已被注册！");
            case -2:
                return Response.error("用户名已存在！");
            default:
                return Response.success("编辑成功！");
        }
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasRole('admin')")
    public Response<?> deleteUser(@RequestBody int[] ids) {
        int result = userService.deleteUser(ids);
        if (result > 0) {
            return Response.success("删除成功！");
        }
        return Response.error("删除失败！");
    }

    @GetMapping("/user/{id}")
    public Response<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        if (user == null) {
            return Response.error("此用户不存在！");
        }
        user.setPassword(null);
        return Response.success("用户信息获取成功！", user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('admin')")
    public Response<PageInfo<User>> getUsers(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<User> pageInfo = userService.listUsers(pageNum, pageSize);
        if (pageInfo.getList() == null) {
            return Response.error("暂无用户！");
        }
        return Response.success("用户列表获取成功！", pageInfo);
    }

}