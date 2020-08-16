package cn.linter.blog.controller;

import cn.linter.blog.entity.Response;
import cn.linter.blog.entity.Role;
import cn.linter.blog.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public Response<?> listRoles() {
        List<Role> roles = roleService.listRoles();
        if (roles == null) {
            return Response.error("暂无角色！");
        }
        return Response.success("角色列表获取成功！", roles);
    }

}
